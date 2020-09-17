/* @author Benzon Carlitos Salazar
*	CS455 - Project 1 (Monoalphabetic Cipher Project)
*	This program decrypts a ciphertext using the monoalphabetic cipher using 
*	a Ciphertext-Only attack (COA) with the provided text document.
*	Two tasks are to be done:
*		[1] Assist us in retrieving the key used to encrypt the original message
*		[2] Use the key to decipher the original message as plaintext
*/

public class MonoalphabeticCipher {
	// Decode

	// generate a mapping of frequencies, and sort ciphertext frequencies


	// calculate letter frequency
	public int[] getCiphertextFrequency() {
		return calcFrequency(this.cipherText);
	}

	private int[] calcFrequency(String text) {
		int charCount = 0;
		int[] newFrequency = new int[26];
		text = text.toUpperCase();
		for(int i = 0; i < text.length(); i++) {
			int currentChar = text.charAt(i);
			int letterIndex = currentChar - 0x41;
			if(letterIndex < 0 || letterIndex >= 26) {
				continue;
			}
			newFrequency[letterIndex]++;
			charCount++;
		}
		for(int i = 0; i < 26; i++) {
			newFrequency[i] = (newFrequency[i] * 1000) / charCount;
		}
		return newFrequency;
	}
}