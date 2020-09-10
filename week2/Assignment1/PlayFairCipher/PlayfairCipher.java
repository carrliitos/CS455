/* @author: Benzon Carlitos Salazar
*	CS455 Homework 1, part 2 -- Using Playfair Cipher to encrypt a message
*/

import java.awt.Point;
import java.util.HashMap;
import java.util.ArrayList;

public class PlayfairCipher {
	// I is excluded and combined with J
	public static final String ALPHABET[] = {"A","B","C","D","E","F","G","H","I","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

	public static void main(String[] args) {
		HashMap<String, Letter> alphabet = generateLetters();

		String plainText = "HELLOWORLD";
		String key = "PASSWORD";

		// generate tableau
		String tableau[][] = generateTableau(key, alphabet);
		// map each letter to their location
		mappingInd(tableau, alphabet);
		// print our tableau
		printTableau(tableau);
		// Start encryption
		encrypt(tableau, plainText, alphabet);
	}

	// Generate our letters
	public static HashMap<String, Letter>generateLetters() {
		HashMap<String, Letter> letters = new HashMap<>();
		for(int i = 0; i < ALPHABET.length; i++) {
			letters.put(ALPHABET[i], new Letter(ALPHABET[i]));
		}
		return letters;
	}

	private static void mappingInd(String[][] matrix, HashMap<String, Letter> alphabet) {
		for(int i = 0; i < matrix[0].length; i++) {
			for(int j = 0; j < matrix.length; j++) {
				Point point = new Point(i, j);
				alphabet.get(matrix[i][j]).setPoint(point);
			}
		}
	}

	// Generate 5x5 tableau
	public static String[][] generateTableau(String key, HashMap<String, Letter> letters){
		// Make sure our key is in uppercase, removing anything that isn't a letter, and replace I with J
		key = key.toUpperCase();
		key = key.replaceAll("[^A-Z]", "");
		key = key.replace("I", "J");

		// Create ArrayList for removing duplicates for the key
		ArrayList<String> temp = new ArrayList<>();

		// Walk through the length of the key word
		for(int i = 0; i < key.length(); i++) {
			// check each letter if it has been used already in the key, if not, add to the key and set it as used
			for(int j = 0; j < letters.size(); j++) {
				String currentLetter = key.substring(i, i + 1);
				if(!letters.get(currentLetter).getUsed()) {
					temp.add(currentLetter);
					letters.get(currentLetter.setUsed(true));
				}
			}
		}

		// Add remaining letters of the alphabet
		for(int i = 0; i < letters.size(); i++) {
			if(!letters.get(ALPHABET[i].getUsed())) {
				temp.add(letters.get(ALPHABET[i]).getLetter());
			}
		}

		// Add to tableau
		String tableau[][] = new String[5][5];
		int count = 0;
		for(int k = 0; k < 5; k++) {
			for(int l = 0; l < 5; l++) {
				tableau[k][l] = temp.get(count);
				count++;
			}
		}

		return tableau;
	}

	// print the tableau
	public static void printTableau(String tableau[][]) {
		System.out.println("**TABLEAU**");
		for(int i = 0; i < tableau.length; i++) {
			for(int j = 0; 0 < tableau[0].length; j++) {
				System.out.print(tableau[i][j] + " ");
			}
			System.out.println();
		}
	}

	// encryption starts here
	private static String encrypt(String[][] tableau, String plainText, HashMap<String, Letter> letters) {
		StringBuilder cipherText = new StringBuilder();

		// walk through the plaintext
		for(int i = 0; i < plainText.length(); i += 2) {
			String one = plainText.substring(i, i + 1);
			String two = plainText.substring(i + 1, i + 2);

			// find the x, y coordinates of letter one and two
			int x1 = (int)((letters.get(one).getPoint().getX()));
			int x2 = (int)((letters.get(two).getPoint().getX()));
			int y1 = (int)((letters.get(one).getPoint().getY()));
			int y2 = (int)((letters.get(two).getPoint().getY()));

			// If letters are on the same row, shift -- accounting for the wrapping
			if(x1 == x2) {
				y1 = (y1 + 1) % 5;
				y2 = (y2 + 1) % 5;
				cipherText.append(tableau[x1][y1]);
				cipherText.append(tableau[x2][y2]);
			}else if(y1 == y2){ // If letters are on the same col, shift -- accounting for the wrapping
				x1 = (x1 + 1) % 5;
				x2 = (x2 + 1) % 5;
				cipherText.append(tableau[x1][y1]);
				cipherText.append(tableau[x2][y2]);
			}else { // otherwise, swap the values of both letters
				cipherText.append(tableau[x1][y2]);
				cipherText.append(tableau[x2][y1]);
			}
		}
		return cipherText.toString();
	}
}