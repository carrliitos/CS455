/* @author Benzon Carlitos Salazar
*	CS455 - Project 1 (Monoalphabetic Cipher Project)
*	This program decrypts a ciphertext using the monoalphabetic cipher using 
*	a Ciphertext-Only attack (COA) with the provided text document.
*	Two tasks are to be done:
*		[1] Assist us in retrieving the key used to encrypt the original message
*		[2] Use the key to decipher the original message as plaintext
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Monoalphabetic {
	public static final String filepath = "./Benzon S.txt";

	public static void main(String[] args) {
		int chars[] = readFile();
		if(chars == null) return;

		for(int i = 0; i < 26; i++) {
			char letter = (char)('A' + i);
			System.out.println(letter + ": " + chars[i]);
		}

		// double maxChars = 0;
		// for(int i : chars) {
		// 	if(i > maxChars) maxChars = i;
		// }
	}

	public static int[] readFile() {
		try {
			File file = new File(filepath);
			Scanner reader = new Scanner(file, "UTF-8");

			int ret[] = new int[26];

			while(reader.hasNextLine()) {
				String line = reader.nextLine().toUpperCase();
				for(int i = 0; i < line.length(); i++) {
					int letter = (int)(line.charAt(i) - 'A');
					if(letter > -1 && letter < ret.length) ret[letter]++;
				}
			}
			reader.close();
			return ret;
		}catch(FileNotFoundException e) {
			System.out.println("Cannot find file.");
			return null;
		}
	}
}