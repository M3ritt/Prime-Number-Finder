package primeNumberFinder;

import java.util.ArrayList;
import java.util.BitSet;

public class Eratosthenes {
	private ArrayList<String> primeNum;

	public Eratosthenes() {
		primeNum = new ArrayList<String>();
	}

	public void findPrime(long maxNum){
		long startTime = System.currentTimeMillis();
		BitSet numbers = BitSet.valueOf(new long[] {maxNum+1});

		//setting all numbers to true
		//PRIME = TRUE
		for(int i=0; i<maxNum; i++) {
			numbers.set(i);
		}

		//2 is first prime number, so start at 2
		for(int i=2; i*i <= maxNum; i++) {
			// checks is number is prime
			if(numbers.get(i)) {
				//updates all multiple of current number
				for(int x = i*2; x <= maxNum; x += i){
					numbers.set(x, false);
				}
			}
		}

		for(int i=2; i <= maxNum; i++) {
			if(numbers.get(i)) {
				String num = i + "";
				primeNum.add(num);
			}
		}
		
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;

		Sundaram comparing = new Sundaram(primeNum, maxNum, "Erato", totalTime);
		comparing.findPrime(maxNum);
		//If wanted to run seperately from Sundaram 
		//		WriteToExcel output = new WriteToExcel();
		//		output.createExcel(primeNum, maxNum, "Erato", totalTime);
	}
}

