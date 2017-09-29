public class exampleClass {
	
	public exampleClass(){
		
	}
	
	public exampleClass(int num){
		exampleNonstatic = num;
	}
	
	public static void printHi(){
		System.out.println("Hi");
	}
	
	public static int numberThree = 3;
	private static String wordHelloWorld = "wordHelloWorld";
	public int exampleNonstatic;
	
	public void printNonStatic(){
		System.out.println(exampleNonstatic);
		exampleNonstatic = 7;
	}
	
}
