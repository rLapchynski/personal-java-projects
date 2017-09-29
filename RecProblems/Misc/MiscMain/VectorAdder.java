package MiscMain;

import java.io.*;

public class VectorAdder {

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = null;
		while (!"".equals(input)) {
			input = "";
			System.out.print("Enter Vector: ");
			try {
				input = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}

			System.out.println("input : " + input);
			System.out.println("-----------\n");
		}

	}

}
