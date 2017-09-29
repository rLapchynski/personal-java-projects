package main;

import java.awt.*;
import java.io.*;
import java.math.*;
import java.net.*;
import java.util.*;
import java.lang.reflect.*;

public class Data<T> {	
	
	private T t;
	
	public Data(){
		
	}
	public Data(T data){
		t = data;
	}
	
	public void set(T data){
		this.t = data;
	}
	public T get(){
		if(this.t == null) throw new NullPointerException("t has not been initilized");	
		return this.t;
	}
	
	public String toString(){
		return t.toString();
	}
	
	public 	static	void				downloadFile(String URL, String savePath) throws IOException {
		URL url = new URL(URL);
		URLConnection  conn = (HttpURLConnection) url.openConnection ();
		conn.setRequestProperty("User-Agent", "Mozilla/5.0");
		InputStream in = conn.getInputStream();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buf = new byte[1024];
		int n = 0;
		while (-1!=(n=in.read(buf)))
		{
			out.write(buf, 0, n);
		}
		out.close();
		in.close();
		byte[] response = out.toByteArray();

		FileOutputStream fos = new FileOutputStream(savePath);
		fos.write(response);
		fos.close();
	}
	
	public			Boolean				hasNumberStored(){
		return t instanceof Integer || t instanceof Long || t instanceof Double;
	}
	
	@SuppressWarnings("unchecked")
	public <A> A invokeMethod(String c, Object... args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		
		Class<?>[] classArgs = new Class<?>[args.length];
		for(int i = 0; i < classArgs.length; i++) classArgs[i] = args[i].getClass();

		return (A) this.getClass().getMethod(c, classArgs).invoke(this, args);
		
	}
	
	public static class Utils{

		public 	static 	enum 				Forms {
			SUM_OF_CONSECUTIVE_PRIMES,
			SUM_OF_PRIME_AND_2SQUARE;
		}
		public 	static 	ArrayList<Long>		fibonacciNumsToMax(long maxValue){
			double previousValue = 0;
			double currentValue = 1;
			
			ArrayList<Long> fibList = new ArrayList<Long>();
			
			while(currentValue <= maxValue){
				fibList.add((long)currentValue);
				currentValue += previousValue;
				previousValue = currentValue - previousValue;
			}
			
			return fibList;
		}
		public 	static 	ArrayList<Long>		fibonacciNumsToIndex(long maxIndex){
			double previousValue = 0;
			double currentValue = 1;
			int index = 0;
			ArrayList<Long> fibList = new ArrayList<Long>();
			
			while(index <= maxIndex){
				fibList.add((long)currentValue);
				currentValue += previousValue;
				previousValue = currentValue - previousValue;
				index++;
			}
			
			return fibList;
		}
		public 	static	Long				fibonacciNumPhi(int index) throws Exception {
			if(index > 71) throw new Exception("Value overflows long: > (2^63)-1");
			return (long)((Math.pow( ((1+Math.sqrt(5))/2), index) - Math.pow( ((1-Math.sqrt(5))/2), index)) / Math.sqrt(5));
		}
		public 	static	BigInteger			bigFibonacciNumLoop(int index) {
			BigInteger currVal = new BigInteger("1"), prevVal = new BigInteger("0");
			
			for(int i = 1; i < index; i++) {
				currVal = currVal.add(prevVal);
				prevVal = currVal.subtract(prevVal);
			}
			
			return currVal;
		}
		public 	static 	Long				ulamCorner(long index){
			long returnVal = 0;
			for(int i = 1; i<=index; i++){
				returnVal += 2*Math.floor((i+2)/4.0);
			}
			
			return returnVal+1;
		}
		public	static	ArrayList<String>	pythagoreans(int length){
			ArrayList<String> retArr = new ArrayList<>();
			for(int i = 1; true; i++){
				for(int a = 1; a < 2*i; a++){
					for(int b = (i-a)+1; b < i+a; b++){
						if(a*a + b*b == i*i){
							retArr.add(i + ", " + a + ", " + b);
							if(retArr.size() >= length) return retArr;
						}
					}
				}
			}
		}
		public 	static	Long				triangularNum(long index) {
			if(index <= 0) return -1L;
			return (index*(index+1))/2;
		}
		public 	static	Boolean				isTriangular(Long num) {
			long currNum = 0;
			for(long i = 0; currNum < num; i++) {
				currNum = Utils.triangularNum(i);
				if(currNum == num) return true;
			}
			return false;
		}
		public 	static	Long				squareNum(long index) {
			return (long) Math.pow(index, 2);
		}
		public 	static	Boolean				isSquare(long num) {
			return Math.sqrt(num) % 1 == 0;
		}
		public 	static	Long				pentagonalNum(long index) {
			if(index <= 0) return -1L;
			return (index*((index*3)-1))/2;
		}
		public 	static	Boolean				isPentagonal(long num) {
			long currNum = 0;
			for(long i = 0; currNum < num; i++) {
				currNum = Utils.pentagonalNum(i);
				if(currNum == num) return true;
			}
			return false;
		}
		public 	static	Long				hexagonalNum(long index) {
			return (index*((2*index)-1));
		}
		public 	static	Boolean				isHexagonal(long num) {
			long currNum = 0;
			for(long i = 0; currNum < num; i++) {
				currNum = Utils.hexagonalNum(i);
				if(currNum == num) return true;
			}
			return false;
		}
		public 	static 	ArrayList<Long>		listDivisors(long num) {
			ArrayList<Long> Divisors = new ArrayList<>();
		    for (long div=1; div<Math.ceil(num/2.0)+1; div++)
		    {
		        if (num%div == 0) Divisors.add(div);
		    }
			return Divisors;
		}
		public 	static 	Integer				countDivisors(long num) {
			return Utils.listDivisors(num).size();
		}
		public 	static	ArrayList<Long>		listPrimeDivisors(long num){
			ArrayList<Long> retList = new ArrayList<Long>();
			if(num < 0) return null;
			if(num <= 5){
				retList.add(num);
				return retList;
			}
			for(long i = 2; num != 1; i = Utils.nextPrime(i)) {
				if(num % i == 0) {
					retList.add(i);
					num /= i;
					i = 0;
				}
			}
			return retList;
		}
		public 	static	ArrayList<Long>		listPowerDivisors(long num){
			ArrayList<Long> retList = new ArrayList<Long>(), factorList = Utils.listPrimeDivisors(num);
			if(num < 0) return null;
			if(num <= 5){
				retList.add(num);
				return retList;
			}
			long occurances, currNum;
			while(factorList.size() > 0) {
				occurances = Utils.countOccurance(factorList, factorList.get(0));
				currNum = factorList.get(0);
				
				//System.out.println("in: " + factorList + " occurances: " + occurances + " currNum: " + currNum);
				factorList = Utils.removeAll(factorList, factorList.get(0));
				retList.add((long)Math.pow(currNum, occurances));
			}
			
			return retList;
		}
		public 	static	Boolean 			isPrime(long num) {
		    if(num < 2) return false;
		    else if(num == 2 || num == 3) return true;
		    else if(num%2 == 0 || num%3 == 0) return false;
		    long sqrtN = (long)Math.sqrt(num)+1;
		    for(long i = 6L; i <= sqrtN; i += 6) {
		        if(num%(i-1) == 0 || num%(i+1) == 0) return false;
		    }
		    return true;
		}
		public 	static 	Long[]				isPrime(long num, boolean reportTime) {
			long prm;
			long startTime = System.nanoTime();
			if(Utils.isPrime(num)) prm = 1;
			else prm = 0;
			long runTime = System.nanoTime() - startTime;
			Long[] returnL = {prm, runTime};
			return returnL;
		}
		public 	static	Long				nextPrime(long num) {
			long nextNum = 12;
			if(num < 0) nextNum = 6;
			else if(num < 8) 
				for(int i = (int)num+1; i < 8; i++) 
					if(Utils.isPrime(i)) 
						return (long)i;
			else if((num + 1) % 6 == 0) nextNum = num + 1;
			else if((num - 1) % 6 == 0) nextNum = num - 1;
		
			while(true) {
				if(Utils.isPrime(nextNum - 1) && nextNum - 1 > num) return nextNum - 1;
				if(Utils.isPrime(nextNum + 1) && nextNum + 1 > num) return nextNum + 1;
				nextNum += 6;
			}
			
		}
		public 	static	Long				prevPrime(long num) {
			long currNum = num + (6-num%6);
			
			if(num < 2) return -1L;
			else if(num <= 4) return num-1;
			else if(num <= 7) return 5L;
			
			while(true) {
				if(Utils.isPrime(currNum + 1) && currNum + 1 < num) return currNum + 1;
				if(Utils.isPrime(currNum - 1) && currNum - 1 < num) return currNum - 1;
				currNum -= 6;
			}
			
		}
		public 	static	Long				primeN(long index) {
			long currPrime = 0;
			for(int i = 0; i <= index; i++) {
				currPrime = Utils.nextPrime(currPrime);
			}
			return currPrime;
		}
		public 	static	ArrayList<Long>		listPrimesBetween(long startNum, long endNum){
			ArrayList<Long> retList = new ArrayList<Long>();
			
			for(long i = startNum; i <= endNum; i = Utils.nextPrime(i)) retList.add(i);
			
			return retList;
		}
		public 	static 	Long 				randomLong(long min, long max){
			if(min > max) {
				long tmpMin = min;
				min = Math.min(min, max);
				max = Math.max(tmpMin, max);
			}
			Random rand = new Random();
			return (rand.nextLong() + min) % max;
		}
		public	static	BigInteger			bigFactorial(long num){
			BigInteger retBI = new BigInteger("1");
			
			for(int i = 2; i <= num; i++){
				retBI.multiply(new BigInteger(""+i));
			}
			
			return retBI;
		}
		public 	static 	Long				factorial(long num){
			long factorialValue = 1L;
			for(int i = 1; i <= num; i++){
				factorialValue *= i;
			}
			return factorialValue;
		}
		public 	static	Long				sumOfDigitFactorial(long num){
			long sum = 0;
			for(int i = 1; i < String.valueOf(num).length()-1; i++){
				sum += Utils.factorial((long)((num%Math.pow(10, i) - num%Math.pow(10, i-1))/Math.pow(10, i-1)));
			}
			return sum;
		}
		public	static	Long 				binomialCoefficient(int n, int k) {
			return (long) ( Utils.factorial(n) / (Utils.factorial(k) * Utils.factorial(n - k)) );
		}
		public 	static	ArrayList<Long>		cycleDigits(long num){
			int length = (int) Math.ceil(Math.log10(num));
			ArrayList<Long> returnList = new ArrayList<Long>();
			String tmpNum;
			
			for(int i = 0; i < length; i++){
				tmpNum = "";
				for(int a = i; a < length + i; a++){
					tmpNum += "" + Utils.intAt(num, a%length);
				}
				returnList.add(Long.parseLong(tmpNum));
			}
			return returnList;
		}
		public 	static	Integer				intAt(long num, int index){
			if(Utils.length(num)  <= index) throw new IndexOutOfBoundsException("num: " + num + " index: " + index );
			index = (int)Math.ceil(Math.log10(num)) - 1 - index;
			num = Math.abs(num);
			return (int)((num % Math.pow(10, index+1) - num % Math.pow(10, index))/Math.pow(10, index));
		}
		public 	static	Integer				length(long num){
			return ("" + num).length();
		}
		public 	static	Long				subNum(long num, int startIndex, int endIndex){
			if(startIndex < 0  || endIndex > Utils.length(num)) throw new IndexOutOfBoundsException("\nstartIndex: " + startIndex + "\nendIndex: " + endIndex + "\nnum: " + num);
			if(startIndex >= endIndex) throw new IndexOutOfBoundsException("startIndex must be less endIndex \nstartIndex: " + startIndex + "\nendIndex: " + endIndex);
			return Long.parseLong(((Long)num).toString().substring(startIndex, endIndex));
			//(long)((num%Math.pow(10, endIndex+3) - num%Math.pow(10, startIndex+3))/Math.pow(10, startIndex+3));
		}
		public 	static	Boolean				contains(long num, int chr) {
			for(int i = 0; i < Utils.length(num); i++) {
				if(Utils.intAt(num, i) == chr) return true;
			}
			return false;
		}
		public 	static	Long[]				asPrimePartitions(long num){
			Long[] retList = new Long[2];
			if(num < 2) return retList;
			for(long a = 2; a < num; a = Utils.nextPrime(a)) {
				long currSum = 0;
				long i;
				for(i = a; currSum < num; i = Utils.nextPrime(i)) {
					currSum += i;
				}
				if(currSum == num) {
					retList[0] = a;
					retList[1] = Utils.prevPrime(i);
					break;
				}
			}
			return retList;
		}
		public 	static	ArrayList<Long>		replaceStars(String str){
			ArrayList<Long> retList = new ArrayList<Long>();
			
			if(!str.contains("*")) {
				retList.add(Long.parseLong(str));
				return retList;
			}
			
			for(int i = 0; i <= 9; i++)
				retList.add(Long.parseLong(str.replace("*".charAt(0), String.valueOf(i).charAt(0))));
			
			return retList;
		}
		public 	static	ArrayList<Long>		permutation(long num){
			ArrayList<String> permutations = permutation(num+"");
			ArrayList<Long> retList = new ArrayList<Long>();
			
			for(String i : permutations) {
				retList.add(Long.parseLong(i));
			}
			
			return retList;
		}
		public 	static	ArrayList<String>	permutation(String str){
			permutation("", str);
			return Utils.permutations;
		}
		public	static	Boolean				isPermutationOf(String str1, String str2) {
			if(str1.length() != str2.length()) return false;
			String[] a = Utils.asCharStrArray(str1), 
					 b = Utils.asCharStrArray(str2);
			Arrays.sort(a);
			Arrays.sort(b);
			return Utils.arraysEqual(a, b);
			
		}
		public 	static 	String[]			asCharStrArray(String str) {
			String[] retArr = new String[str.length()];
			
			for(int i = 0; i < str.length(); i++) {
				retArr[i] = str.substring(i, i+1);
			}
			
			return retArr;
		}
		private static	void 				permutation(String prefix, String str) {
			int n = str.length();
			if (n == 0) Utils.permutations.add(prefix);
			else {
				for (int i = 0; i < n; i++)
					permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n));
			}
		}
		private static	ArrayList<String>	permutations = new ArrayList<String>();
		public	static	ArrayList<String>	replaceEachCharacter(String str){
			ArrayList<String> 	tmpList = new ArrayList<String>(), 
								retList = new ArrayList<String>();
			retList.add(str);
			int b;
			for(int c = 0; c < str.length(); c++) {
				b = retList.size();
				for(int a = 0; a < b; a++) {
					String strn = retList.get(a);
					tmpList.clear();
					for(int a1 = 0; a1 < strn.length(); a1++) {
						tmpList.add(strn.substring(0, a1) + "*" + strn.substring(a1+1));
					}
					retList.addAll(tmpList);
				}
				
			}
			return Utils.removeDuplicates(retList);
		}
		public 	static	String				zFill(int in, int len){
			String retStr = in + "";
			
			for(int i = 0; i < len - Utils.length(in); i++){
				retStr = "0" + retStr;
			}
			
			return retStr;
		}
		public 	static	Boolean				isPalindrome(BigInteger num){
			return new StringBuilder(num.toString()).reverse().toString().equals(num.toString());
		}
		public 	static	Boolean				isPalindrome(long num){
			return Long.parseLong(new StringBuilder((num) + "").reverse().toString()) == num;
		}
		public 	static	Boolean				isPalindrome(String str){
			return new StringBuilder(str).reverse().toString().equals(str);
		}
		public 	static	Boolean				isTruncatablePrime(long num){
			assert isPrime(num) : "Num is not prime";
			for( int i = 0; i < Utils.length(num); i++){
				if(!Utils.isPrime(Utils.subNum(num, 0, i+1))) return false;
				if(!Utils.isPrime(Utils.subNum(num, i, Utils.length(num)))) return false;
			}
			return true;
		}
		public 	static	Boolean				isPandigital(long num) {
			if((num+"").length() != 9) return false;
			int sum = 0;
			for(int i = 0; i < 9; i++) {
				sum += Utils.intAt(num, i);
				if(!Utils.contains(num, i+1)) return false;
			}
			return sum == 45;
		}
		public 	static	Boolean				isNdPandigital(long num) {
			return Utils.isNdPandigital0(num, false, false);
		}
		public 	static	Boolean				isNdPandigital0(long num, boolean include0, boolean testLength) {
			int startNum = 1;
			int lengthDiff = 0;
			if(include0) startNum = 0;
			if(include0 && !testLength) lengthDiff = 1;
			if(Utils.length(num) > 10) return false;
			for(int i = startNum; i <= Utils.length(num) - lengthDiff; i++) {
				if(!Utils.contains(num, i)) return false;
			}
			return true;
		}
		public 	static 	Boolean				isPythagoreanTriple(long a, long b, long c) {
			return ((a*a) + (b*b) == (c*c)) || ((a*a) + (c*c) == (b*b)) || ((b*b) + (c*c) == (a*a));
		}
		public 	static	Boolean				canBeWrittenAs(long num, Utils.Forms form) throws ArgumentOutOfRangeException {
			
			switch(form) {
				case SUM_OF_CONSECUTIVE_PRIMES:
					int b = 0;
					for(long a = 2; a < num; a = Utils.nextPrime(a)) {
						if(b%100==0)System.out.println(":" + b);
						b++;
						long currSum = 0;
						long i;
						for(i = a; currSum < num; i = Utils.nextPrime(i)) {
							currSum += i;
						}
						if(currSum == num) {
							return true;
						}
					}
					return false;
				case SUM_OF_PRIME_AND_2SQUARE:
					for(int i = 0; 2*(i*i) <= num; i++) {
						if(Utils.isPrime(num - (2*(i*i)))) {
							return true;
						}
					}
					return false;
				default:
					throw new ArgumentOutOfRangeException(form + " is not a valid form.");
			}
		}
		public 	static	String				charAt(String str, int index) {
			if(index >= str.length()) throw new StringIndexOutOfBoundsException("Length: " + str.length() + " Index: " + index);
			return str.substring(index, index+1);
		}
		public 	static	String				repeat(String str, int repetitions){
			String retStr = "";
			for(int i = 0; i < repetitions; i++) retStr += str;
			return retStr;
		}
		public	static	String				forPrintCol(int[] colWidths, String... strs){
			String retStr = "";
			
			for(int i = 0; i < strs.length; i++){
				retStr += strs[i] + Utils.repeat(" ", colWidths[i] - strs[i].length());
			}
			
			return retStr;
		}
		public	static	void				printlnCol(int[] colWidths, String... strs){
			System.out.println(Utils.forPrintCol(colWidths, strs));
		}
		public	static	void				printCol(int[] colWidths, String... strs){
			System.out.print(Utils.forPrintCol(colWidths, strs));
		}
		public 	static	Long				max(ArrayList<Long> numList) {
			long max = 0;
			for(Long num : numList) {
				if(num > max) max = num;
			}
			return max;
		}
		public 	static	Long				min(ArrayList<Long> numList) {
			long min = 0;
			for(Long num : numList) {
				if(num > min) min = num;
			}
			return min;
		}
		public 	static	Long				sum(ArrayList<Long> numList) {
			Long sum = 0L;
			for(long i : numList) sum += i;
			return sum;
		}
		public 	static	Integer				countOccurance(ArrayList<?> argList, Object o) {
			Integer retInt = 0;
			for( Object i : argList ) {
				if(o.equals(i)) retInt++;
			}
			return retInt;
		}
		public 	static	ArrayList<Long>		removeAll(ArrayList<Long> numList, long num){
			
			for(int i = 0; i < numList.size(); i++) {
				//System.out.println(numList + " " + i);
				if(numList.get(i) == num) {
					numList.remove(i);
					i--;
				}
			}
			return numList;
		}
		public 	static	ArrayList<String>	removeDuplicates(ArrayList<String> strList){
			ArrayList<String> retList = new ArrayList<String>();
			
			for(int i = 0; i < strList.size(); i++) {
				if(!retList.contains(strList.get(i))) {
					retList.add(strList.get(i));
				}
			}
			
			return retList;
		}
		public 	static	String				arrayAsString(String[] arr) {
			String retStr = "[";
			for(int i = 0; i < arr.length; i++) {
				retStr += "\"" + arr[i] + "\"" + (i+1!=arr.length ? ", " : "");
			}
			return retStr + "]";
		}
		public 	static	String				arrayAsString(int[] arr) {
			String retStr = "[ ";
			for(int i = 0; i < arr.length; i++) {
				retStr += arr[i] + "" + (i+1!=arr.length ? ", " : "");
			}
			return retStr + " ]";
		}
		public	static	Boolean				arraysEqual(String[] arr1, String[] arr2) {
			if(arr1.length != arr2.length) return false;
			for(int i = 0; i < arr1.length; i++) {
				if(!arr1[i].equals(arr2[i])) return false;
			}
			return true;
		}
		public	static	Double				regularPolygonArea(Double sideLength, int numOfSides){
			return (0.5) * (sideLength * numOfSides) * (sideLength / (2 * Math.tan(180.0 / numOfSides)));
		}
		public	static	Double				irregularPolygonArea(ArrayList<Coordinate> vertexList){
			return null;
		}
	}
	
	public static class Coordinate{
		public Double X, Y;
		public Coordinate(Double x, Double y){
			X = x;
			Y = y;
		}
		public Coordinate(Integer x, Integer y){
			X = x.doubleValue();
			Y = y.doubleValue();
		}
		public Double distanceTo(Coordinate c){
			return Math.sqrt( Math.pow(Math.abs(c.X) + Math.abs(X), 2) + Math.pow(Math.abs(c.Y) + Math.abs(Y), 2));
		}
	}

	public static class ArgumentOutOfRangeException extends Exception {
		private static final long serialVersionUID = -30495759424090633L;
		public ArgumentOutOfRangeException() {
			super();
		}
		public ArgumentOutOfRangeException(String message) {
			super(message);
		}
	}
	public static class UncheckedArgumentOutOfRangeException extends RuntimeException {
		private static final long serialVersionUID = 4058729010305319122L;
		public UncheckedArgumentOutOfRangeException() {
			super();
		}
		public UncheckedArgumentOutOfRangeException(String message) {
			super(message);
		}
	}
	public static class InvalidArgumentException extends Exception {
		private static final long serialVersionUID = -6409583102350124236L;
		public InvalidArgumentException() {
			super();
		}
		public InvalidArgumentException(String message) {
			super(message);
		}
	}
	public static class UncheckedInvalidArgumentException extends RuntimeException {
		private static final long serialVersionUID = -3306961069986886291L;
		public UncheckedInvalidArgumentException() {
			super();
		}
		public UncheckedInvalidArgumentException(String message) {
			super(message);
		}
	}
	public static class InvalidMethodForStoredTypeException extends Exception{
		private static final long serialVersionUID = -7092370537705942976L;
		public InvalidMethodForStoredTypeException() {
			super();
		}
		public InvalidMethodForStoredTypeException(String message) {
			super(message);
		}
	}
	public static class UncheckedInvalidMethodForStoredTypeException extends RuntimeException{
		private static final long serialVersionUID = -7092370537705942976L;
		public UncheckedInvalidMethodForStoredTypeException() {
			super();
		}
		public UncheckedInvalidMethodForStoredTypeException(String message) {
			super(message);
		}
	}
	
}
