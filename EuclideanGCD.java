import java.util.Scanner;

public class EuclideanGCD {
	public static int Euclid(int a, int b) {
		System.out.printf("a = %d, b = %d\n", a, b);
		if(a == 0) return b;
		return Euclid(b % a, a);
	}

	public static void main(String [] args) {
		Scanner input = new Scanner(System.in);
		System.out.printf("Enter integer 1: \n");
		int a = input.nextInt();
		System.out.printf("Enter integer 2: \n");
		int b = input.nextInt();

		System.out.printf("\nFinding GCD(%d, %d)\n", a, b);
		System.out.printf("GCD = %d\n", Euclid(a, b));
	}
}
