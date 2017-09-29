package Problems001_025;

import utilities.Problems;

public class Problem018 {
	public static int[][] triangle = { { 75 }, { 95, 64 }, { 17, 47, 82 }, { 18, 35, 87, 10 }, { 20, 04, 82, 47, 65 },
			{ 19, 01, 23, 75, 03, 34 }, { 88, 02, 77, 73, 07, 63, 67 }, { 99, 65, 04, 28, 06, 16, 70, 92 },
			{ 41, 41, 26, 56, 83, 40, 80, 70, 33 }, { 41, 48, 72, 33, 47, 32, 37, 16, 94, 29 },
			{ 53, 71, 44, 65, 25, 43, 91, 52, 97, 51, 14 }, { 70, 11, 33, 28, 77, 73, 17, 78, 39, 68, 17, 57 },
			{ 91, 71, 52, 38, 17, 14, 91, 43, 58, 50, 27, 29, 48 },
			{ 63, 66, 04, 68, 89, 53, 67, 30, 73, 16, 69, 87, 40, 31 },
			{ 04, 62, 98, 27, 23, 9, 70, 98, 73, 93, 38, 53, 60, 04, 23 } };

	public static void main(String args[]) {
		int depth = 15;
		int[] previous = null;
		for (int i = 1; i < depth; i++) {
			int[] last = triangle[depth - i];
			previous = calculateRowMaximal(triangle[(depth - i) - 1], last);
		}
		Problems.returnVal(previous[0], args);
		return;
	}

	private static int[] calculateRowMaximal(int[] previous, int[] last) {
		for (int i = 0; i < previous.length; i++) {
			previous[i] = previous[i] + (Math.max(last[i], last[i + 1]));
		}
		return previous;
	}

}