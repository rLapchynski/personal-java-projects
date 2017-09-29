package Problems001_025;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import utilities.Files;
import utilities.Problems;

public class Problem022 {
	public static List<String> names = new ArrayList<String>(
			Files.fileList(System.getProperty("user.dir") + "/bin/Problem022_Names.txt"));
	public static String alphabet = "abcdefghijklmnopqrstuvwxyz";

	public static void main(String[] args) {
		Collections.sort(names);
		int nameScore;
		int total = 0;
		for (int i = 0; i < names.size(); i++) {
			nameScore = 0;
			for (int a = 0; a < names.get(i).length(); a++) {
				nameScore += (alphabet.indexOf(String.valueOf(names.get(i).charAt(a)).toLowerCase()) + 1);
			}
			nameScore *= i + 1;
			total += nameScore;
		}
		Problems.returnVal(total, args);
		return;
	}
}
