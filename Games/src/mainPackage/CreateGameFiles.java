package mainPackage;
import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import java.util.*;
import java.util.List;
import java.util.stream.Stream;
import java.math.*;
import javax.swing.*;

import java.io.*;
import java.nio.file.*;
import java.nio.charset.*;

@SuppressWarnings("unused")
public class CreateGameFiles {
	public JFrame crtFrame = new JFrame("crtFrame");
	public JPanel pagesView = new JPanel(new BorderLayout());
	
	public void main(String args[]){
		crtFrame.setSize(Frames.stdDim);
		crtFrame.setLayout(null);
		pagesView.setSize(50,50);
		pagesView.setLocation(10,10);
		pagesView.setName("pagesView");
		crtFrame.add(pagesView);
		crtFrame.setVisible(true);
		
	}
}
