/** @author Benzon Carlitos Salazar
*	This program is my implementation of Fermat's Little Theorem for CS455
*/

import java.math.BigInteger;
import java.util.*;

public class FermatLittle {
	// rng = random number generator
	private final static Random rng = new Random();

	/** This method applies the test a^(n - 1) = 1 (mod n) 
	*	@param n is used for the above test
	*	@param numberOfIters can easily vary between decrease run time or
	*	 increase in certainty of results
	*	@return boolean true if n is (likely) prime 
	*/
	private static boolean fermatMethod(BigInteger n, int numberOfIters){
		// CHECK -- if n = 1, n is not prime
		if(n.equals(BigInteger.ONE)) return false;

		// Fermat's Primality test
		for(int i = 0; i < numberOfIters; i++) {
			// construct a coprime, a, for testing the primality of n
			BigInteger a = constructRandomCoprime(n);
			/** perform modular exponentiation on a
			 *  Fermat's Theorem states that if a^(n - 1) = 1(mod n), 
			 *  BigInteger n is (likely) prime
			 */
			a = a.modPow(n.subtract(BigInteger.ONE), n);

			// if a^(n - 1) != 1(mod n), BigInteger n is composite
			if(!a.equals(BigInteger.ONE)) return false;
		}

		// if a^(n - 1) = 1(mod n) for all iterations of numberOfIters,
		// and BigInteger n is returned as a prime
		return true;
	}

	/** This method asks for a random integer, and rejects it if it is not in
	*	the acceptable set.
	*	@param n the random integer for checking
	*	@return BigInteger, a randomly constructed BigInteger of the same bit
	*		length as BigInteger n, such that 1 <= a < n
	*/
	private static BigInteger constructRandomCoprime(BigInteger n) {
		while(true) {
			final BigInteger a = new BigInteger(n.bitLength(), rng);
			/** the compareTo method of the BigInteger class returns -1, 0, or 
            * 	1 depending on whether the calling BigInteger is less than, 
            * 	equal to of greater than the parameter BigInteger, respectively
            * 	- here we are determining a value for a which is greater than or
            * 	equal to 0 AND less than BigInteger n - though the Fermat Base
            * 	a *could* be any number in the set of integers, most 
            * 	implementations rely on a | 0 <= a < n, where n is the 
            * 	number to be tested for primality
            */
            if(BigInteger.ONE.compareTo(a) <= 0 && a.compareTo(n) < 0) {
            	return a;
            }
		}
	}

	public static void main(String[] args) {
		// variable to be tested for primality
		long testNumber = 1;
		long startTime, endTime;
		// ask user input
		System.out.println("\nEnter the number to be tested for primality: ");
		
		Scanner userScan = new Scanner(System.in);
		if(userScan.hasNextLong()) testNumber = userScan.nextLong();

		// cast testNumber as long and set it equal to BigInteger n
		BigInteger n = BigInteger.valueOf(testNumber);

		startTime = System.currentTimeMillis();
		System.out.println(fermatMethod(n, 20) ? 
			("\n" + testNumber + " is prime.\n") :
			("\n" + testNumber + " is composite.\n"));
		endTime = System.currentTimeMillis();
		System.out.println("\nTotal run time for " + testNumber + " was " 
				+ (endTime - startTime) + " milliseconds.\n");
	}
}