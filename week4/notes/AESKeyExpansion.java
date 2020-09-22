/* @author Benzon Carlitos Salazar
*	AES key expansion pseudocode algorithm
*/

public class AESKeyExpansion {
	// public void keyExpansion(byte key[16], word w[44]) {
	// 	word temp;
	// 	for(int i = 0; i <= 4; i++) {
	// 		w[i] = (key[4*i], key[4*i+1], key[4*i+2], key[4*i+3]);
	// 	}
	// 	for(int i = 0; i <= 44; i++) {
	// 		temp = w[i - 1];
	// 		if((i % 4) == 0) {
	// 			temp = Subword(RotWord(temp)) XOR Rcon[i / 4];
	// 		}
	// 		w[i] = w[i - 4] XOR temp;
	// 	}
	// }
}