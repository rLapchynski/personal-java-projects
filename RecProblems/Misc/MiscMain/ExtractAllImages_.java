package MiscMain;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.text.AttributeSet;
import javax.swing.text.html.HTMLDocument;

public class ExtractAllImages_ {
	public static String folderName = "facets";
	public static ArrayList<String> urls = new ArrayList<>();
    public static void main(String args[]) throws Exception {
    	urls.add("http://www.facets.la/offset/315/");
    	
    	while(urls.size() > 0){
    		try{
    			System.out.println("URL: " + urls.get(0));
    			getImages(urls.get(0));
    		} catch(Exception e){
    			System.err.println("URL: " + urls.get(0));
    		}
    		urls.remove(0);
    	}
    	
    }
    
    public static void getImages(String webUrl) throws Exception{
    	//String webUrl = "http://www.hdwallpapers.in/";
        URL url = new URL(webUrl);
        URLConnection connection = url.openConnection();
        InputStream is = connection.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        HTMLEditorKit htmlKit = new HTMLEditorKit();
        HTMLDocument htmlDoc = (HTMLDocument) htmlKit.createDefaultDocument();
        HTMLEditorKit.Parser parser = new ParserDelegator();
        HTMLEditorKit.ParserCallback callback = htmlDoc.getReader(0);
        parser.parse(br, callback, true);

        for (HTMLDocument.Iterator iterator = htmlDoc.getIterator(HTML.Tag.IMG); iterator.isValid(); iterator.next()) {
            AttributeSet attributes = iterator.getAttributes();
            String imgSrc = (String) attributes.getAttribute(HTML.Attribute.SRC); 

            if (imgSrc != null && (imgSrc.endsWith(".jpg") || (imgSrc.endsWith(".png")) || (imgSrc.endsWith(".jpeg")) || (imgSrc.endsWith(".bmp")) || (imgSrc.endsWith(".ico")))) {
                try {
                    downloadImage(webUrl, imgSrc);
                    System.out.println("\tDownload Image: " + imgSrc);
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
        for(HTMLDocument.Iterator iterator = htmlDoc.getIterator(HTML.Tag.A); iterator.isValid(); iterator.next()){
        	AttributeSet attributes = iterator.getAttributes();
        	String src = (String) attributes.getAttribute(HTML.Attribute.HREF);
        	if(!urls.contains(webUrl + src)){
        		if(src.endsWith(".html"))
        			src = src.substring(0, src.length() - 5);
        		urls.add((src.startsWith("http") ? "" : webUrl) + src);
        	}
        	//System.out.println("A: " + webUrl + src);
        }
    }
    
    private static void downloadImage(String url, String imgSrc) throws IOException {
        BufferedImage image = null;
        try {
            if (!(imgSrc.startsWith("http"))) {
                url = url + imgSrc;
            } else {
                url = imgSrc;
            }
            imgSrc = imgSrc.substring(imgSrc.lastIndexOf("/") + 1);
            String imageFormat = null;
            imageFormat = imgSrc.substring(imgSrc.lastIndexOf(".") + 1);
            String imgPath = null;
            imgPath = "C:/Users/Ryan Lapchynski/Desktop/Crawler/" + folderName + "/" + imgSrc + "";
            URL imageUrl = new URL(url);
            image = ImageIO.read(imageUrl);
            if (image != null) {
                File file = new File(imgPath);
                ImageIO.write(image, imageFormat, file);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}