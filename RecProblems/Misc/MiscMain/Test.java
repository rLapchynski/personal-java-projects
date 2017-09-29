package MiscMain;

import java.io.File;

public class Test
{
    public static void main(String[] args)
    {

		File oldfile =new File("C:/Users/Ryan Lapchynski/Desktop/New Text Document.txt");
		File newfile =new File("C:/Users/Ryan Lapchynski/Desktop/Renamed Text Document.txt");

		if(oldfile.renameTo(newfile)){
			System.out.println("Rename succesful");
		}else{
			System.out.println("Rename failed");
		}

    }
}
