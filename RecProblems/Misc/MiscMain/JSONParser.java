package MiscMain;

public class JSONParser {

	public static void main(String[] args) {
		String JSON = utilities.Files.fileList("C:/Users/Ryan Lapchynski/Documents/JSONOut.txt").get(0);
		
		for(int i = 0; i < JSON.length(); i++){
			if(JSON.substring(i, i+1).equals(",")){
				JSON = JSON.substring(0, i+1) + "\n" + JSON.substring(i+1, JSON.length());
				i++;
			}
		}
		System.out.println(JSON);
	}

}
