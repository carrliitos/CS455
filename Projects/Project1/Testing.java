import java.util.Scanner;
import java.util.Arrays;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Testing {
	public static void main(String[] args) {
		try{
			// Note to self: '\n' are counted as a character
			FileReader fr = new FileReader("testing.txt");
			int i;
			int count = 0;
			char x = ' ';
			while((i = fr.read()) != -1) {
				if(Character.isLetter(i)){
					// System.out.printf("%c", (char) i);
					x = (char) i;
					System.out.printf("%c", x);
					count++;
				}
				// System.out.printf("%c:%d,", (char) i, i);
			}
			System.out.printf("\nTotal Letters = %d\n", count);
		}catch(FileNotFoundException e) {
			System.out.println("File not found");
		}catch(IOException i) {
			System.out.println("IO Exception error");
		}
	}
}