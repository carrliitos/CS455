import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.io.FileReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.ArrayIndexOutOfBoundsException;

public class Testing {
	public static final String ALPHABET[] = {"A","B","C","D","E","F","G","H", "I",
											"J","K","L","M","N","O","P","Q","R",
											"S","T","U","V","W","X","Y","Z"};

	public static void main(String[] args) {
		try{
			// Note to self: '\n' are counted as a character
			FileReader fr = new FileReader("testing.txt");
			int i;
			int count = 0;
			char x = ' ';
			ArrayList<Character> chars = new ArrayList<>();
			while((i = fr.read()) != -1) {
				if(Character.isLetter(i)){
					// System.out.printf("%c", (char) i);
					count++;
					x = (char) i;
					chars.add(x);
					// System.out.printf("%c", chars);
				}
				// System.out.printf("%c:%d,", (char) i, i);
			}
			// access all elements of chars
			System.out.printf("Accessing all element:\n");
			for(i = 0; i < chars.size(); i++) {
				System.out.printf("%c", chars.get(i));
			}
			
			System.out.printf("\nTotal Letters = %d\n", count);
			fr.close();
		}catch(FileNotFoundException e) {
			System.out.println(e);
		}catch(IOException i) {
			System.out.println(i);
		}catch(ArrayIndexOutOfBoundsException a) {
			System.out.println(a);
		}
	}
}