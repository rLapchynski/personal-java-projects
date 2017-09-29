package Problems051_075;

import java.math.BigInteger;

import utilities.Data.Utils;

public class Problem053 {
	
	public static void main(String[] args) throws Exception{
		int t = 0;
		for(int n=1; n<=100; n++){
			for(int r=1; r<=n; r++){
				if(Utils.bigBinomialCoefficient(n, r).compareTo(BigInteger.valueOf(1000000)) > 0){
					t++;
				}
			}
		}
		System.out.println(t);
	}

}