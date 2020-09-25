import java.util.*;

public class DecryptionTesting {
    public static void main(String [] args) {
        String ciphertext = "EHK NDGBKOE XCEKJIKDX KIGGS GL EHK POUDFKE FKEEKD, IT JUEHUJVKF HUMEHGDJK";
        String key = "xzpsacitwnehgfmlqjvyrodbuk";
        String KEY = "XZPSACITWNEHGFMLQJVYRODBUK";
        
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        
        String plaintext = "";
        
        int i, j;
        for (i = 0; i < ciphertext.length(); i++) {
            for (j = 0; j < key.length(); j++) {
                if (ciphertext.charAt(i) == key.charAt(j)) {
                    plaintext += alphabet.charAt(j);
                    break;
                }
                if (ciphertext.charAt(i) == KEY.charAt(j)) {
                    plaintext += ALPHABET.charAt(j);
                    break;
                }
            }
            if (j == KEY.length())
                plaintext += ciphertext.charAt(i);
        }
        
        System.out.println("Monoalphabetic Cipher [Decryption]");
        System.out.println("Cipher Text : " + ciphertext);
        System.out.println("key         : " + key);
        System.out.println("KEY         : " + KEY);
        System.out.println("Plain Text  : " + plaintext);
    }
}
