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
import java.util.ArrayList;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Monoalphabetic {
	public static void main(String[] args) {
		try{
			FileReader fr = new FileReader("Benzon S.txt");
			int i;
			int count = 0;
			char x = ' ';
			ArrayList<Character> chars = new ArrayList<>();
			while((i = fr.read()) != -1) {
				if(Character.isLetter(i)){
					x = (char) i;
					count++;
					chars.add(x);
				}
			}
			System.out.printf("Accessing all element:\n");
			for(i = 0; i < chars.size(); i++) {
				System.out.printf("%c", chars.get(i));
			}

			System.out.printf("\nTotal Letters = %d\n", count);
			fr.close();
		}catch(FileNotFoundException e) {
			System.out.printf("File not found\n");
		}catch(IOException i) {
			System.out.printf("IO Exception error\n");
		}
	}
}