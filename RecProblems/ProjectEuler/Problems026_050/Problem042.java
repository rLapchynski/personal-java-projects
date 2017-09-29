package Problems026_050;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import utilities.Files;
import utilities.Problems;

public class Problem042 {

	public static ArrayList<String> words = Files
			.fileList("C:/Users/Ted/Desktop/ProjectEulerReferenceFiles/Problem042_Words.txt");
	public static List<String> alphabet = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m",
			"n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z");
	public static List<Integer> triangles = Arrays.asList(1, 3, 6, 10, 15, 21, 28, 36, 45, 55, 66, 78, 91, 105, 120,
			136, 153, 171, 190, 210, 231, 253, 276, 300, 325, 351, 378, 406, 435, 465, 496, 528, 561, 595, 630, 666,
			703, 741, 780, 820, 861, 903, 946, 990, 1035, 1081, 1128, 1176, 1225, 1275, 1326, 1378, 1431);

	public static void main(String[] args) {
		int wordSum;
		int totalWords = 0;
		for (String word : words) {
			wordSum = 0;
			for (int i = 0; i < word.length(); i++) {
				wordSum += alphabet.indexOf(word.substring(i, i + 1).toLowerCase()) + 1;
			}
			if (triangles.contains(wordSum))
				totalWords++;
		}
		Problems.returnVal(totalWords, args);
		return;
	}
}