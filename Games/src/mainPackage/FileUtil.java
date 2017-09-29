package mainPackage;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class FileUtil {
	public static ImageIcon fullscreenIcon = 		scaleToIcon(System.getProperty("user.dir") + "/bin/images/fullScreen2.png", 20, 20);
    public static ImageIcon exitFullscreenIcon = 	scaleToIcon(System.getProperty("user.dir") + "/bin/images/fullScreenExit.png", 20, 20);
    
    public static ImageIcon nTilde = 				scaleToIcon(System.getProperty("user.dir") + "/bin/images/nTilde.png", 108,65);
    
    public static ImageIcon arcReactor = 			scaleToIcon(System.getProperty("user.dir") + "/bin/images/arcReactor.png", 600, 800);
    public static ImageIcon colorfulBackground = 	scaleToIcon(System.getProperty("user.dir") + "/bin/images/colorfulBackground.png", 600, 800);
    public static ImageIcon fractal = 				scaleToIcon(System.getProperty("user.dir") + "/bin/images/fractal.png", 600, 800);
    public static ImageIcon blankImg = 				scaleToIcon(System.getProperty("user.dir") + "/bin/images/blankImg.png", 600, 800);
    public static ImageIcon background = 			scaleToIcon(System.getProperty("user.dir") + "/bin/images/background.jpg", 600, 800);
    public static ImageIcon penroseTiling= 			scaleToIcon(System.getProperty("user.dir") + "/bin/images/penroseTiling.gif", 600, 800);
    public static ImageIcon greyBackground = 		scaleToIcon(System.getProperty("user.dir") + "/bin/images/greyBackground.png", 600, 800);
    public static ImageIcon closeButton = 			scaleToIcon(System.getProperty("user.dir") + "/bin/images/closeButton.png", 25, 70);
    
	public static ImageIcon scaleToIcon(String imgDir, int height, int width){
		ImageIcon icon = new ImageIcon(imgDir);
        Image tmpImg = icon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
        icon = new ImageIcon(tmpImg);
		return icon;
	}
	public static Image getImageFromDir(String imgDir){
		ImageIcon img = new ImageIcon(imgDir);
        Image tmpImg = img.getImage();
		return tmpImg;
	}
	public static String[] fileList(String fileName/*Actually the file path*/){
		/*
		 * Usage:
		 * 		String[] str = fileList("path");
		 * 		*do stuff with str*
		 */
		List<String> fileLines = new ArrayList<String>();
		String line = null;
		try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            while((line = bufferedReader.readLine()) != null) {
            	fileLines.add(line);
            }   
            
            bufferedReader.close();         
        }
        catch(Exception e){
        	e.printStackTrace();
        }
		String[] tmpStr = new String[fileLines.size()];
		for(int i = 0; i <fileLines.size(); i++){
			tmpStr[i] = fileLines.get(i);
		}
		return tmpStr;
	}
}