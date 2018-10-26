package primeNumberFinder;

import java.util.Scanner;

public class PrimeNumberRunner {

	public static void main(String[] args){
		long maxNumber = -1;
		String number = " ";
		Scanner sc = new Scanner(System.in);	
		do {
			System.out.println("What is the maximum number?");
			try {
				number = sc.nextLine();
				maxNumber = Integer.parseInt(number);
			}catch(NumberFormatException e) {
				System.out.println("Must be an int value."); 
			} 
		} while(maxNumber < 0);
		
		Eratosthenes pnf = new Eratosthenes();
		pnf.findPrime(maxNumber);
		
		//If wanted to run seperately
//		Sundaram sun = new Sundaram();
//		sun.findPrime(maxNumber);
		
		sc.close();
	}
}

