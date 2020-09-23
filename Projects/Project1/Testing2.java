import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Testing2 {
    public static final String filepath = "./testing.txt";

    public static void main(String[] args) {

        int[] chars = readFile(); // Decided to put file reading in a seperate method to make main() less big, and to keep any FileIO exception handling stuff in a separate spot.

        if (chars == null) return; // chars will be null if readFile() couldn't find whatever filepath we gave it.
        
        // Since the array we get from readFile() is sorted alphabetically i can just iterate down it instead of having to have a key that tells me what letter each frequency corresponds to
        for (int i = 0; i < 26; i++) {
            char letter = (char)('A' + i);
            System.out.println(letter + ": " + chars[i]);
        }

        // Get the amount of times the letter with the highest frequency was used. This will be used to set scaling for the histogram.
        double maxChars = 0;
        for (int i : chars) {
            if (i > maxChars) maxChars = i;
        }
    }

    // Returns an array containing the amount of times each letter was found, sorted from # of a's to # of z's.
    // For example a file containing "aAAa, --BbB" would cause this method to return an array that looks like this:
    // {4, 3, 0, 0, 0... }
    public static int[] readFile() {

        try {
            File file = new File(filepath);
            Scanner reader = new Scanner(file, "UTF-8"); // This is how cool kids who want to do lots of unecessary extra work read files.
            // There was a pretty funny bug here where i guess the scanner assumes UTF-16 encoding or something and was reading an EOF in the middle of the file. Good times.

            int[] ret = new int[26];

            while (reader.hasNextLine()) {
                String line = reader.nextLine().toUpperCase(); // Make sure it's lowercase otherwise the capital letters don't count.
                for (int i = 0; i < line.length(); i++) {
                    int letter = (int)(line.charAt(i) - 'A'); // Gets the int code for whatever char we're looking at, and makes sure it aligns to the array's 0-25 indices by subtracting 'a'
                    if (letter > -1 && letter < ret.length) ret[letter]++; // We only want the chars from a-z, so disregard everything else. Otherwise, increase the count for whichever letter this is.
                }
            }
            reader.close(); // Save that sweet, sweet RAM by closing your file readers.
            return ret;

        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find specified file.");
            return null;
        }
    }
}