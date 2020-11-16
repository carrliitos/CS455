/** @author Benzon Carlitos Salazar
*	This program finds the multiplicative inverse of e under modulo m
*/

import java.util.Scanner;

public class MultiplicativeInverse {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("To find e mod m, enter e: ");
		int e = input.nextInt();
		System.out.println("Enter m: ");
		int m = input.nextInt();

		System.out.println("e mod m = " + modInverse(e, m));
	}

	public static int modInverse(int e, int m) {
		e = e % m;
		for(int x = 1; x < m; x++) {
			if((e * x) % m == 1) return x;
		}
		return 1;
	}
}