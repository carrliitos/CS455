import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.awt.Point;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PlayfairCipher {
	public static final String ALPHABET[] = {"A","B","C","D","E","F","G","H",
											"I","K","L","M","N","O","P","Q","R",
											"S","T","U","V","W","X","Y","Z"};
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Choose (E)ncryption or (D)ecryption: ");
		String choice = input.next();
		boolean valid = false;
		while(!valid){
			if(choice.toUpperCase().startsWith("E")) {
				System.out.print("Enter your text: ");
				String plainText = input.next();

				System.out.print("Enter your key: ");
				String key = input.next();

				valid = true;

				plainText = plainText.toUpperCase();
				key = key.toUpperCase();
			}else if(choice.toUpperCase().startsWith("D")) {
				System.out.print("Enter your text: ");
				String cipherText = input.next();

				System.out.print("Enter your key: ");
				String key = input.next();

				valid = true;

				cipherText = cipherText.toUpperCase();
				key = key.toUpperCase();
			}else {
				System.out.println("Choose (E) or (D): ");
				choice = input.next();
			}
		}

		// populate tableau
		HashMap<String, Letter> alphabet = populateTableau();
		// Create tableau
		String tableau[][] = generateTableau(key, alphabet);
		// map each letter in the tableau
		map(tableau, alphabet);
		printTableau(tableau);

		String result = "";
		if(choice.equals("E")) {
			result = encrypt(tableau, plainText, alphabet);
			System.out.println("Encryption: " + result);
		}else {
			result = decrypt(tableau, cipherText, alphabet);
			System.out.println("Decryption: " + result);
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

			// Check 
		}
	}
}