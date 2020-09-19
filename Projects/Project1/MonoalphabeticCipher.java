/* @author Benzon Carlitos Salazar
*	CS455 - Project 1 (Monoalphabetic Cipher Project)
*	This program decrypts a ciphertext using the monoalphabetic cipher using 
*	a Ciphertext-Only attack (COA) with the provided text document.
*	Two tasks are to be done:
*		[1] Assist us in retrieving the key used to encrypt the original message
*		[2] Use the key to decipher the original message as plaintext
*/

import java.util.Scanner;
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;

public class MonoalphabeticCipher {
	public static void main(String[] args) {
		try{
			int characters[] = {};
			File file = new File("sample.txt");
			Scanner scan = new Scanner(file);
			// String fileContent = scan.nextLine();
			scan.useDelimiter("[;\r\n]+");
			while(scan.hasNext()) {
				// print(countCharacters(fileContent));
				// print(countCharacters(scan.next()));
				// System.out.printf("%s%n", scan.next());
				characters = countCharacters(scan.next());
			}
			System.out.println(Arrays.toString(characters));
		}catch(FileNotFoundException e) {
			System.out.println("File not found");
		}
	}

	public static int[] countCharacters(String text) {
		// Store count of each character
		int[] alphabet = new int[26];
		text = text.toUpperCase();
		// traverse each character in the string
		for(int i = 0; i < text.length(); i++) {
			// if the character is a letter at the current position
			if(Character.isLetter(text.charAt(i))) {
				// add to the corresponding place in the array
				alphabet[Character.getNumericValue(text.charAt(i)) - 10]++;
			}
		}
		return alphabet;
	}

	// print the array in a nice "A: ##" format
	public static void print(int[] data) {
		for(int i = 0; i < data.length; i++) {
			System.out.println(((char)(i + 65)) + ": " + data[i]);
		}
	}
}