package stackOverflowStuff;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import utilities.Data;
import utilities.Files;
import utilities.WebUtils;

public class Main{
	public static void main(String[] args) throws FileNotFoundException {
		
		//WebUtils.fetchComic("http://imgs.xkcd.com/comics/reindeer", "C:/Users/Ryan Lapchynski/Desktop/test");
		ArrayList<String> failed = new ArrayList<>();
		
		ArrayList<String> names = Files.fileList("C:/Users/Ryan Lapchynski/Desktop/view-source_https___xkcd.com_archive_.html");
		
		String path = "C:/Users/Ryan Lapchynski/Pictures/Comics/XKCD/";
				
		File[] listOfFiles = new File(path).listFiles();
		
		for(int i = Integer.parseInt(listOfFiles[listOfFiles.length-1].getName().substring(0, 6)) + 1; i<names.size(); i++){
			try{
				System.out.println(names.get(i));
				WebUtils.fetchComic("http://imgs.xkcd.com/comics/" + names.get(i).toLowerCase(), path + Data.Utils.zFill(i, 6));
				System.out.print(i);
				System.out.println("  .");
			}catch(FileNotFoundException e){
				System.out.println("Image not Found: " + e.getMessage());
				failed.add(i + " " + names.get(i));
				//
			}
		}
		
		Files.writeFileFromList("C:/Users/Ryan Lapchynski/Desktop/failed.txt", failed);
	}
}
