
public class practice {

	public static void main(String[] args) {
		System.out.println(function(2*4));
		
		exampleClass instance0 = function2();
		instance0.exampleNonstatic = 4;
		instance0.numberThree = 4;
		
		exampleClass instance1 = new exampleClass();
		instance1.exampleNonstatic = 5;
		instance1.numberThree = 6;
		
		boolean thing = true;
		if(thing){
			
		}else if(thing){
			
		}else{
			
		}
		
		//while(booleanVar){
			
		//}
		
		for(int i=0; i <10; i++){
			System.out.println(i);
		}
		
		System.out.println(instance0.exampleNonstatic);
		System.out.println(instance1.exampleNonstatic);
		System.out.println(instance0.numberThree);

	}
	
	public static int function(int num){
		
		return num*2;
		
	}
	
	public static exampleClass function2(){
		return new exampleClass();
	}

}
