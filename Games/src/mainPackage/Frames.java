package mainPackage;
import javax.swing.*;
import java.awt.*;
import java.math.*;

@SuppressWarnings("unused")
public class Frames {
	public static boolean currentlyFullScreen = true;
	public static Dimension stdDim = new Dimension(620, 350);
	public static int winHeight = scale(stdDim).height;
	public static int winWidth = scale(stdDim).width;
	public static int titleFontSize = 25;
	public static final int jeapardyFrameID = 1;
	public static final int mainFrameFrameID = 2;
	public static final int screenHorizontalResolution = Toolkit.getDefaultToolkit().getScreenSize().width;
	public static final int screenVerticalResolution = Toolkit.getDefaultToolkit().getScreenSize().height;
	
	public static void setFull(JFrame frame){
		//System.out.println(new Dimension(620, 350).width + "   " + scale(new Dimension(620, 350)).width);
		if(currentlyFullScreen){
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			titleFontSize = 50;
			//frame.setSize(winWidth, winHeight);
		}
		else{
			frame.setSize(scale(stdDim));
			titleFontSize = 25;
		}
		
		winHeight = frame.getBounds().height;
		winWidth = frame.getBounds().width;
		
		currentlyFullScreen = !currentlyFullScreen;
	}
	
	public static Dimension scale(Dimension currentDim){
		Dimension tmpDim = new Dimension();
		tmpDim.height = (int) Math.ceil((currentDim.height * (screenVerticalResolution/1080.0)));
		tmpDim.width = (int) Math.ceil((currentDim.width * (screenHorizontalResolution/1920.0)));
		return tmpDim;		
	}
}
