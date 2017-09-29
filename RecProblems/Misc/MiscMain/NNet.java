package MiscMain;

import java.awt.*;
import java.awt.datatransfer.*;
import java.io.IOException;
import java.util.ArrayList;
import utilities.*;

public class NNet {

	public static void main(String[] args) throws IOException, HeadlessException, UnsupportedFlavorException, InterruptedException {
		//ujE4ecHqynQ
		//ujE4ecHqynQsjz7O3P0Jl6
		/*
		System.out.println(PowerShellCommand.run("powershell.exe Remove-item alias:curl ; curl -k --header 'Access-Token: o.U8zpyQ0WfMIRYrl6cVj344UcFWXvxY4X' --header"
				+ " 'Content-Type: application/json' --data-binary '{\\\"push\\\": {\\\"conversation_iden\\\": \\\"+1 927 825 8535\\\",\\\"message\\\": \\\"Hello!\\\",\\\"package_name\\\": "
				+ "\\\"com.pushbullet.android\\\",\\\"source_user_iden\\\": \\\"ujE4ecHqynQ\\\",\\\"target_device_iden\\\": \\\"ujE4ecHqynQsjz7O3P0Jl6\\\",\\\"type\\\": \\\"messaging_extension_reply\\\"},"
				+ "\\\"type\\\": \\\"push\\\"}' --request POST https://api.pushbullet.com/v2/ephemerals"));
				*/
		//System.out.println(PowerShellCommand.run("powershell.exe Remove-item alias:curl ; curl -k --header 'Access-Token: o.U8zpyQ0WfMIRYrl6cVj344UcFWXvxY4X' --header 'Content-Type: application/json' --data-binary '{\"push\":{\"cat\":\"meow\"},\"type\":\"push\"}' --request POST https://api.pushbullet.com/v2/ephemerals"));
		//System.out.println(PowerShellCommand.run("powershell.exe Remove-item alias:curl ; curl -k --header 'Access-Token: o.U8zpyQ0WfMIRYrl6cVj344UcFWXvxY4X' https://api.pushbullet.com/v2/devices"));
		
		String text, prevText = "";
		
		while(true){
			text = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor); 
			if(!text.equals(prevText)){
				prevText = text;
				//System.out.println(text);
				try{
					Files.writeNewLine("C:/Users/Ryan Lapchynski/Documents/ClipboardLog.txt", text + "\n");
				} catch(Exception e){
					throw e;					
				}
			}
			Thread.sleep(100);
		}
	}

}
/**
 * Assumes that every neuron in each layer connects to every neuron in the following layer.
 */
class Network{
	ArrayList<ArrayList<ArrayList<Double>>> weights = new ArrayList<>();
	ArrayList<ArrayList<Double>> biases = new ArrayList<>();
	//
	Network(int... layerSizes){
		
	}
}