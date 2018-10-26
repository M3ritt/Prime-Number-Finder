package primeNumberFinder;

import java.util.ArrayList;
import java.util.BitSet;

public class Sundaram {
	private ArrayList<String> prime;
	//Info from Erato
	private ArrayList<String> primeNum;
	private String Erato;
	private long EratoTime;

	public Sundaram(ArrayList<String> primeNum, long maxNum, String Erato, long EratoTime) {
		prime = new ArrayList<String>();
		this.primeNum = primeNum;
		this.Erato = Erato;
		this.EratoTime = EratoTime;
	}

	public void findPrime(long maxNum) {
		long startTime = System.currentTimeMillis();
		long num = maxNum/2;

		BitSet pNumbers = BitSet.valueOf(new long[] {num+1});
		pNumbers.clear();

		for(int i = 1; i<num; i++) {
			for(int j = i; (num-i)/(2*i+1) >= j; j++) {
				if((i+j+2*i*j) <= maxNum && ((i+j+2*i*j) >= 0)) {
					pNumbers.set((i + j + 2*i*j), true);
				}
			}
		}

		prime.add("2");
		for(int i=1; i < num; i++) {
			if(pNumbers.get(i) == false) {
				int primes = 2*i+1;
				prime.add(primes+ " ");
			}
		}

		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		
		int numOfPrimes = prime.size();

		System.out.println("MAX NUM: "+maxNum);
		System.out.println("SUN: "+numOfPrimes+ " with a time of: "+ totalTime);
		System.out.println("ERATO: "+primeNum.size()+ " with a time of: "+this.EratoTime);

		WriteToExcel primeNumbers = new WriteToExcel();
		primeNumbers.createExcel(prime, maxNum, numOfPrimes, "sun", totalTime, this.primeNum, this.Erato, this.EratoTime);
	}
}
