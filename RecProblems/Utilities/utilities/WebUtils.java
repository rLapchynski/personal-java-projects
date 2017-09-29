package utilities;

import java.awt.*;
import java.io.*;
import java.net.*;

import javax.imageio.ImageIO;

public class WebUtils {
	public static void downloadFile(String URL, String savePath) throws IOException{
		URL url = new URL(URL);
		URLConnection  conn = (HttpURLConnection) url.openConnection ();
		conn.setRequestProperty("User-Agent", "Mozilla/5.0");
		InputStream in = conn.getInputStream();
		//this.is.the.
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buf = new byte[1024];
		int n = 0;
		while (-1!=(n=in.read(buf)))
		{
			out.write(buf, 0, n);
		}
		out.close();
		in.close();
		byte[] response = out.toByteArray();

		FileOutputStream fos = new FileOutputStream(savePath);
		fos.write(response);
		fos.close();
	}
	public static Image fetchImage(String imageURL) throws IOException{
		Image image = null;
		URL url = new URL(imageURL);
		image = ImageIO.read(url);
		return image;
	}
	public static String fetchComic(String URL /*Without file type*/, String savePath /*Without file type*/) throws FileNotFoundException{
		String[] fileTypes = {".png", ".jpg", ".gif", ".jpeg", ".bmp"};
		
		for(String type : fileTypes){
			try{
				//System.out.println("Try:" + URL + type);
				downloadFile(URL + type, savePath + type);
				return type;
			}catch(FileNotFoundException e){
			}catch (IOException e) {
			}
		}
		throw new FileNotFoundException(URL);
	}
}
