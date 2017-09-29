package Problems051_075;

import utilities.Data.Utils;

public class Problem052 {

	public static void main(String[] args) throws InterruptedException {
		
		for(int i = 1; true; i++){
			if(Integer.parseInt(Utils.charAt(i+"", 0)) >= 2) continue;
			if(Utils.isPermutationOf(i+"", ""+i*2) && Utils.isPermutationOf(i+"", i*4+"") && Utils.isPermutationOf(i+"", i*3+"") && Utils.isPermutationOf(i+"", i*6+"") && Utils.isPermutationOf(i+"", i*5+"")){
				System.out.println(i + " " + 2*i + " " + 3*i + " " + 4*i + " " + 5*i + " " + 6*i);
				break;
			}
		}
		
	}

}