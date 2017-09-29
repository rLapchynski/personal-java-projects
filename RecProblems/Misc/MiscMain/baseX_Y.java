package MiscMain;

import utilities.Data.Utils;

public class baseX_Y {
	
	public static int from = 2, to = 1;
	public static void main(String[] args) {
		for(int i = 0; i < 50; i++){
			double b = 0, a, place;
			int[] d = convert(i);
			for(int c = d.length-1; c >= 0; c--){
				a = d[c];
				place = d.length - c - 1;
				b += a * Math.pow(((double)from/(double)to), place);
			}
			
			System.out.print(b + "   \n\n");
		}
	}
	public static int[] convert(int in){
		int[] retArr = new int[10];
		
		retArr[9] = in;
		
		for(int i = 8; i >= 0; i--){
			if(retArr[i+1] >= from){
				System.out.println(Utils.arrayAsString(retArr));
				retArr[i] = (retArr[i+1] / from) * to;
				retArr[i+1] %= from;
			}
			
		}
		System.out.println(Utils.arrayAsString(retArr));
		return retArr;
	}
}
