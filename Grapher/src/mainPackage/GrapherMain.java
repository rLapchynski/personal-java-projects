package mainPackage;

import utilities.*;
import utilities.Graph.*;

import java.util.ArrayList;

import javax.swing.*;

public class GrapherMain {
	public static JFrame mainFrame = new JFrame("Grapher");
	public static JPanel contentPanel = new JPanel();
	
	public static void main(String[] args) {
		Graph g = new Graph();
		
		ArrayList<String> file = Files.fileList("C:/Users/Ryan Lapchynski/Documents/STEM/STEM/Math/xc2.txt");
		
		ArrayList<Ellipse> el = new ArrayList<>();
		
		for(int i = 4; i <= 17; i++){
			String[] strs = file.get(i).split(":");
			double[] line = new double[4];
			for(int a = 0; a < 4; a++)
				line[a] = Double.parseDouble(strs[a]) % 10000 == 0 ? Math.sqrt(Double.parseDouble(strs[a])) : Double.parseDouble(strs[a]);
			
			el.add(g.new Ellipse(line[1], line[3], line[0], line[2]));
			
			System.out.println(el.get(el.size()-1).foci);
		}
		
		ArrayList<String> output = new ArrayList<>();
		
		for(Ellipse e : el){
			output.add((e.foci.get(0).equals(e.foci.get(1)) ? "Circle: " : "Ellipse: " )+ e + "\n     Center: " + e.center + 
						   "\n     Vertices: " + e.vertices + 
						   "\n     Foci: " + e.foci + "\n");
		}
		//Files.writeFileFromList("C:/Users/Ryan Lapchynski/Documents/STEM/STEM/Math/Ellipses.txt", output);;lu
		//"C:\Users\Ryan Lapchynski\Documents\STEM\STEM\Math\Ellipses.txt"
		
		//System.out.println(e.foci);
	}

}
