/* @author: Benzon Carlitos Salazar
*	CS455 Homework 1, part 1 -- Using Caesar Cipher to decrypt a message
*/

public class CaesarCipher {
	// variable representing the english alphabet
	private static final int ALPHA_SIZE = 26;
	private static final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static void main(String[] args) {
		String text1 = "TUZBKXEYKIAXK";
		String text2 = "TSJLCPYZJCRMZPSRCDMPACYRRYAIQ";

		System.out.printf("Text 1: %s\n", text1);
		System.out.printf("Possible combinations: \n");
		for(int key = 1; key < ALPHA_SIZE; key++) {
			System.out.printf("[%d]%s\n", key, decrypt(text1, key));
		}

		System.out.printf("Text 2: %s\n", text2);
		System.out.printf("Possible combinations: \n");
		for(int key = 1; key < ALPHA_SIZE; key++) {
			System.out.printf("[%d] %s\n", key, decrypt(text2, key));
		}
	}

	public static String decrypt(String cipherText, int key) {
		String recoveredText = "";
		for(int i = 0; i < cipherText.length(); i++) {
			int mapValue = alphabet.indexOf(cipherText.charAt(i));
			int decrypt = (mapValue - key) % 26;
			// Handle possible negative values
			if(decrypt < 0) {
				decrypt = alphabet.length() + decrypt;
			}
			char ch = alphabet.charAt(decrypt);
			recoveredText += ch;
		}
		return recoveredText;
	}
}

/* Text 1: "TUZBKXEYKIAXK"
*	Key = 6
*	Plaintext = "NOTVERYSECURE"
*
*  Text 2: "TSJLCPYZJCRMZPSRCDMPACYRRYAIQ"
*	Key = 24
*	Plaintext = "VULNERABLETOBRUTEFORCEATTACKS"
*/