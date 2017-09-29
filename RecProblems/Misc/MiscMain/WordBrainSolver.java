package MiscMain;

import java.awt.*;
import java.util.*;
import java.util.List;

import utilities.*;

@SuppressWarnings("unused")
public class WordBrainSolver {

	public static void main(String[] args) {
		ArrayList<String> words = Files.fileList("C:/Users/Ryan Lapchynski/Documents/FlashDrive_Dumps/GSNYP16GB/Me/_Misc/words.txt");
		
		boolean[] unused = adjacentUnused(new Dimension(4,4), new boolean[][] {	{true, true, true, false}, 
																				{true, false, true, false}, 
																				{false, false, true, false}, 
																				{false, false, false, false}}, new int[] {1, 1});
		
		System.out.println(unused[0] + " " + unused[1] + " " + unused[2]);
		System.out.println(unused[3] + " " + unused[4] + " " + unused[5]);
		System.out.println(unused[6] + " " + unused[7] + " " + unused[8]);	
	}
	
	public static ArrayList<String> permuteGrid(String[][] array, int startX, int startY, int len){
		ArrayList<String> retArr = new ArrayList<>();
		boolean[][] unusedArr = new boolean[array.length][array[0].length];
		Dimension gridSize = new Dimension(array.length, array[0].length);
		
		for(int i = 0; i < array.length; i++) 
			for(int a = 0; a < array[0].length; a++) 
				unusedArr[i][a] = true;
		
		unusedArr[startY][startX] = false;
		
		
		
		return retArr;
	}
	
	public static boolean[] adjacentUnused(Dimension arraySize, boolean[][] unusedArr, int[] square){
		boolean[] retArr = new boolean[9];
		/*	0 1 2
		 *	3 4 5
		 *	6 7 8
		 */
		
		return retArr;
	}
	
	public static class Array2D{
		
		public int 	width,
					height;
		public coordRoot coordMode;
		public Class<?>[][] dataArr;
		
		public Array2D(int w, int h, coordRoot c, Class<?>[][] d){
			width = w;
			height = h;
			coordMode = c;
			dataArr = d;
		}
		
		public Class<?> getValAt(int x, int y){
			
			switch(coordMode){
			case BottomLeft:
				return dataArr[dataArr.length - 1 - y][x];
			case BottomRight:
				return dataArr[dataArr.length - 1 - y][dataArr[0].length - 1 - x];
			case TopLeft:
				return dataArr[y][x];
			case TopRight:
				return dataArr[dataArr.length - y][dataArr[0].length - 1 - x];
			case Center:
				return null;
			default:
				return dataArr[0][0];
			}
		}
		public Class<?>[][] subArr(int startX, int endX, int startY, int endY){
			
			
			return dataArr;
		}
		public enum coordRoot{
			BottomLeft,
			BottomRight,
			TopLeft,
			TopRight,
			Center;
		}
	}
}
