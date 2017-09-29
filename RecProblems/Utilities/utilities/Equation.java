package utilities;


public class Equation {
	public String stringValue;

	public Equation(String equ) {
		stringValue = equ;
	}

	public static int numOfVars(String equ) {
		int retVar = 0;
		for (int i = 0; i < equ.length(); i++) {
			if (equ.substring(i, i + 1).matches("[a-z]*"))
				retVar++;
		}
		return retVar;
	}
}
/*
 * 
 */