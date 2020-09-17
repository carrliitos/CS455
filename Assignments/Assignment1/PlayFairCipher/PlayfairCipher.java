/* @author Benzon Carlitos Salazar
*	CS455 - Assignment 1, Part 2: Playfair Cipher
*	This program will either encrypt or decrypt plain/ciphertexts, for a given
*	key.
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.awt.Point;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PlayfairCipher {
	public static final String ALPHABET[] = {"A","B","C","D","E","F","G","H",
											"J","K","L","M","N","O","P","Q","R",
											"S","T","U","V","W","X","Y","Z"};
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter your key: ");
		String key = input.next().toUpperCase();

		// populate tableau
		HashMap<String, Letter> alphabet = populateTableau();
		// Create tableau
		String tableau[][] = generateTableau(key, alphabet);
		// map each letter in the tableau
		map(tableau, alphabet);
		printTableau(tableau);

		System.out.print("Choose (E)ncryption or (D)ecryption: ");
		String choice = input.next();
		String result = "";
		boolean valid = false;
		while(!valid) {
			if(choice.toUpperCase().startsWith("E")) {
				System.out.println("Enter your plaintext for encryption: ");
				String plainText = input.next().toUpperCase();
				valid = true;
				result = encrypt(tableau, plainText, alphabet);
				System.out.println("\nEncryption Result: " + result);
				printTableau(tableau);
			}else if(choice.toUpperCase().startsWith("D")) {
				System.out.println("Enter your ciphertext for decryption: ");
				String cipherText = input.next().toUpperCase();
				valid = true;
				result = decrypt(tableau, cipherText, alphabet);
				System.out.println("\nDecryption Result: " + result);
				printTableau(tableau);
			}else {
				System.out.println("Enter your choice, (E) or (D): ");
				choice = input.next();
			}
		}
	}

	public static HashMap<String, Letter> populateTableau() {
		HashMap<String, Letter> letters = new HashMap<>();
		for(int i = 0; i < ALPHABET.length; i++) {
			letters.put(ALPHABET[i], new Letter(ALPHABET[i]));
		}
		return letters;
	}

	public static String[][] generateTableau(String key, HashMap<String, Letter> letters) {
		// replace I with J
		key = key.replace("I", "J");
		// create temp for removing any duplicates
		ArrayList<String> temp = new ArrayList<>();

		for(int i = 0; i < key.length(); i++) {
			// checking if the letter in the key has been used already
			for(int j = 0; j < letters.size(); j++) {
				String current = key.substring(i, i + 1);
				if(!letters.get(current).getUsed()) {
					temp.add(current);
					letters.get(current).setUsed(true);
				}
			}
		}

		// add the rest of the alphabet to the key
		for(int i = 0; i < letters.size(); i++) {
			if(!letters.get(ALPHABET[i]).getUsed()) {
				temp.add(letters.get(ALPHABET[i]).getLetter());
			}
		}

		// Add to the tableau
		String matrix[][] = new String[5][5];
		int count = 0;
		for(int k = 0; k < 5; k++) {
			for(int l = 0; l < 5; l++) {
				matrix[k][l] = temp.get(count);
				count++;
			}
		}
		return matrix;
	}

	private static void map(String[][] tableau, HashMap<String, Letter> alphabet) {
		for(int i = 0; i < tableau[0].length; i++) {
			for(int j = 0; j < tableau.length; j++) {
				Point point = new Point(i, j);
				alphabet.get(tableau[i][j]).setPoint(point);
			}
		}
	}

	public static void printTableau(String matrix[][]) {
		System.out.println("**PLAYFAIR TABLEAU**");
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[0].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static String pair(String text) {
		text = text.replace("I", "J");
		int textLength = text.length();
		StringBuilder pairedText = new StringBuilder(text);
		for(int i = 0; i < textLength - 1; i += 2) {
			if(pairedText.charAt(i) == pairedText.charAt(i + 1)) {
				pairedText.insert(i + 1, "X");
				textLength++;
			}
		}

		// add X at the end if textLength is odd
		if(pairedText.length() % 2 != 0) pairedText.append("X");
		text = pairedText.toString();
		return text;
	}

	private static String encrypt(String[][] tableau, String plainText, HashMap<String, Letter> letters) {
		StringBuilder cipherText = new StringBuilder();

		for(int i = 0; i < plainText.length(); i += 2) { // Remember: pairs
			String letter1 = plainText.substring(i, i + 1);
			String letter2 = plainText.substring(i + 1, i + 2);

			// Identify x and y coords for the pair
			int x1 = (int) ((letters.get(letter1).getPoint().getX()));
			int x2 = (int) ((letters.get(letter2).getPoint().getX()));
			int y1 = (int) ((letters.get(letter1).getPoint().getY()));
			int y2 = (int) ((letters.get(letter2).getPoint().getY()));

			// Check if letters are on the same row, if so, shift
			if(x1 == x2) {
				y1 = (y1 + 1) % 5;
				y2 = (y2 + 1) % 5;
				cipherText.append(tableau[x1][y1]);
				cipherText.append(tableau[x2][y2]);
			}else if(y1 == y2) { // Check if letters are on the same COLUMN, if so, shift
				x1 = (x1 + 1) % 5;
				x2 = (x2 + 1) % 5;
				cipherText.append(tableau[x1][y1]);
				cipherText.append(tableau[x2][y2]);
			}else { // swap the y values of the two letters and return the new letter
				cipherText.append(tableau[x1][y2]);
				cipherText.append(tableau[x2][y1]);
			}
		}
		return cipherText.toString();
	}

	private static String decrypt(String[][] tableau, String cipherText, HashMap<String, Letter> letters) {
		StringBuilder plainText = new StringBuilder();

		for(int i = 0; i < cipherText.length(); i += 2) {
			String letter1 = cipherText.substring(i, i + 1);
			String letter2 = cipherText.substring(i + 1, i + 2);

			// identify the x and y coords for the pair
			int x1 = (int) ((letters.get(letter1).getPoint().getX()));
			int x2 = (int) ((letters.get(letter2).getPoint().getX()));
			int y1 = (int) ((letters.get(letter1).getPoint().getY()));
			int y2 = (int) ((letters.get(letter2).getPoint().getY()));

			// if letters are on the same row, shift
			if(x1 == x2) {
				y1 = (y1 + 4) % 5;
				y2 = (y2 + 4) % 5;
				plainText.append(tableau[x1][y1]);
				plainText.append(tableau[x2][y2]);
			}else if(y1 == y2) { // if letters are on the same column, shift
				x1 = (x1 + 4) % 5;
				x2 = (x2 + 4) % 5;
				plainText.append(tableau[x1][y1]);
				plainText.append(tableau[x2][y2]);
			}else { // otherwise, swap the y values of the two letters
				plainText.append(tableau[x1][y2]);
				plainText.append(tableau[x2][y1]);
			}
		}
		return plainText.toString();
	}
}