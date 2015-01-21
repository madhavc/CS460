/**
 * CS 460: Secure Communication
 * Professor: Ting Ting Chen 
 *
 * Programming Assignment #1
 *
 * <Vigenere_Cipher_Encryption>
 *
 * @author madhavchhura
 */
package edu.csupomona.cs460.prog_assigmnt_1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Vigenere_Cipher
{
	/*
	 * Driver Program which calls the Vigenere method. 
	 */
	public static void main(String args[])
	{
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		int temp = 0;

		//Author Information
		System.out.println("Madhav Chhura "
				+ "\nBronco ID: 008949291");

		//Prompt the user to obtain message to be encrypted and key.
		System.out.println("Enter a message to be encrypted.");
		String userMessage = input.nextLine();
		System.out.println("Enter a unique key phrase.");
		String userKeyPhrase = input.nextLine();

		/*Call the vigenere method which takes in message that needs 
		 * to be encrypted or decrypted, key used to encrypt the message
		 * and a boolean which you set to true to encrypt otherwise 
		 * false to decrypt.
		 */
		String text = vigenere(userMessage, userKeyPhrase, true);

		//Print out the encrypted message.
		System.out.println("Your message after encryption is: " + text);

		//Ask user if they want to decrypt the same message.
		System.out.println("If you want to decrypt your previous message enter: 1, to end enter: 0");
		try{
			temp = input.nextInt();
		}catch (InputMismatchException e){
			System.out.print("Invalid input! Goodbye");
		}

		/*If user enters 1 then we call the same vignere method 
		 * with boolean set to false to decrypt the message.
		 */

		if(1 == temp) 
			System.out.print("Your message after decryption is: " + vigenere(text, userKeyPhrase, false));
		else 
			System.out.println("Goodbye");
	}

	/**
	 * 
	 * @param plaintext - message that needs to be encrypted or decrypted.
	 * @param key - user entered key that performs the encryption with.
	 * @param encrypt - true if you want to encrypt, false to decrypt the message.
	 * @return string of the encrypted or decrypted message.
	 */
	public static String vigenere(String plaintext, String key, boolean encrypt) {

		final int textLength = plaintext.length();
		final int keySize = key.length();

		final int upperCase = 'Z' - 'A' + 1; 
		final int lowerCase = 'z' - 'a' + 1;
		final int alphabets = upperCase + lowerCase;
		
		// Build same size string which will store the encrypted message
		final StringBuilder encryptedText = new StringBuilder(textLength); 
		
		//Go through the loop of the string size to change each character. 
		for (int i = 0; i < textLength; i++) {
			final char plainChar = plaintext.charAt(i);


			final int plainGroupNumber; 
			if (plainChar >= 'A' && plainChar <= 'Z') {
				plainGroupNumber = plainChar - 'A';
			} else if (plainChar >= 'a' && plainChar <= 'z') {
				plainGroupNumber = upperCase + plainChar - 'a';
			} else {
				// simply leave spaces and other characters
				encryptedText.append(plainChar);
				continue;
			}

			final char keyChar = key.charAt(i % keySize);
			final int keyGroupNumber; 
			if (keyChar >= 'A' && keyChar <= 'Z') {
				keyGroupNumber = keyChar - 'A';
			} else if (keyChar >= 'a' && keyChar <= 'z') {
				keyGroupNumber = upperCase + keyChar - 'a';
			} else {
				throw new IllegalStateException("Invalid character in key");
			}


			final int cipherGroupNumber;
			if (encrypt) {
				cipherGroupNumber = (plainGroupNumber + keyGroupNumber) % alphabets;
			} else {

				final int someCipherGroupNumber = plainGroupNumber - keyGroupNumber;
				if (someCipherGroupNumber < 0) {
					cipherGroupNumber = (someCipherGroupNumber + alphabets);
				} else {
					cipherGroupNumber = someCipherGroupNumber;
				}
			}

			final char cipherChar;
			if (cipherGroupNumber < upperCase) {
				cipherChar = (char) ('A' + cipherGroupNumber);
			} else {
				cipherChar = (char) ('a' + cipherGroupNumber - upperCase);
			}
			encryptedText.append(cipherChar);
		}

		return encryptedText.toString();
	}
}