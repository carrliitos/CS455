import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Testing {
	public static final Character ALPHABET[] = {'A', 'B', 'C', 'D', 'E', 'F', 'G',
												'H', 'I', 'J', 'K', 'L', 'M', 'N',
												'O', 'P', 'Q', 'R', 'S', 'T', 'U',
												'V', 'W', 'X', 'Y', 'Z'};

	public static void main(String[] args) {
		try {
			String filePath = "testing.txt";
			FileReader reader = new FileReader(filePath);
			int c = 0;
			char character = ' ';
			ArrayList<Character> chars = new ArrayList<>();

			while((c = reader.read()) != -1) {
				if(Character.isLetter(c)) {
					character = (char) c;
					chars.add(character);
				}
			}
			System.out.println(chars);
			reader.close();
		}catch(FileNotFoundException f) {
			System.out.printf("File not found\n");
			return;
		}catch(IOException e) {
			System.out.println("IO error\n");
			return;
		}
	}
}