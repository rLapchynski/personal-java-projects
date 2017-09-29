package MiscMain;

import java.io.IOException;

import utilities.*;

public class ExtractAllImages{
	public static void main(String[] args) throws IOException{
		for(int i = 405; i <= 1641; i++){
			WebUtils.downloadFile("http://xkcd.com/" + i + "/", "C:/Users/Ryan Lapchynski/Pictures/Downloaded/xkcd.html");
			//System.out.println(Files.find("C:/Users/Ryan Lapchynski/Pictures/Downloaded/xkcd.html", "Image URL \\(for hotlinking\\/embedding\\)\\: http\\:\\/\\/imgs\\.xkcd\\.com\\/comics\\/[0-9a-zA-Z\\-\\_]*\\.[a-zA-Z]{0,4}", false));
			Files.writeNewLine(i + " " + 
					Files.find("C:/Users/Ryan Lapchynski/Pictures/Downloaded/xkcd.html", "Image URL \\(for hotlinking\\/embedding\\)\\: http\\:\\/\\/imgs\\.xkcd\\.com\\/comics\\/[0-9a-zA-Z\\-\\_]*\\.[a-zA-Z]{0,4}", false),
							"C:/Users/Ryan Lapchynski/Pictures/Downloaded/URLs.txt");
			try{
				System.out.println(Files.fileList("C:/Users/Ryan Lapchynski/Pictures/Downloaded/URLs.txt").get(Files.fileList("C:/Users/Ryan Lapchynski/Pictures/Downloaded/URLs.txt").size()-1));
			}catch(Exception e){
				System.err.println("Unable to print: " + e.getMessage());
			}
			
		}
	}
}