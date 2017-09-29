package utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Files {
	//UTILITIES-------------------------------
	public static int reportProgress = 0;
	
	public static String find(String fileName, String regex, boolean searchForSubstrings){
		for(String line : fileList(fileName)){
			if(searchForSubstrings){
				for(int i = 0; i < line.length(); i++){
					for(int a = i+1; a < line.length(); a++){
						if(line.substring(i, a).matches(regex)){
							return line.substring(i, a);
						}
					}
				}
			}else{
				if(line.matches(regex)){
					return line;
				}
			}
		}
		return "";
	}
	public static void rename(String oldPath, String newPath) throws IOException{
		File file = new File(oldPath);
		File file2 = new File(newPath);
		if (file2.exists())
		   throw new java.io.IOException("File already exists: " + newPath);
		if(!file.renameTo(file2))
			throw new java.io.IOException("Unable to rename");
	}
	public static void replace(String fileName, String findString, String replaceString) {
		ArrayList<String> fileLines = fileList(fileName);

		for (int i = 0; i < fileLines.size(); i++) {
			String tmp = fileLines.get(i);
			fileLines.remove(i);
			String[] splitTmp = tmp.split(findString);
			for (int a = 0; a < splitTmp.length; a++) {
				if (a != splitTmp.length - 1)
					splitTmp[a] = splitTmp[a] + replaceString;
			}
			tmp = "";
			for (String a : splitTmp) {
				tmp += a;
			}
			fileLines.add(i, tmp);
			if (reportProgress != 0 && i % reportProgress == 0)
				System.out.println(i + "/" + fileLines.size());
		}
		// System.out.println(fileLines);
		writeFileFromList(fileName, fileLines);
	}
	public static void newLineAt(String fileName, String findString) {
		ArrayList<String> fileLines = fileList(fileName);
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(fileName, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (int i = 0; i < fileLines.size(); i++) {
			String tmp = fileLines.get(i);
			fileLines.remove(i);
			String[] splitTmp = tmp.split(findString);
			for (int a = 0; a < splitTmp.length; a++) {
				fileLines.add(i + a, splitTmp[a]);
				writer.println(splitTmp[a]);
			}
			if (reportProgress != 0 && i % reportProgress == 0)
				System.out.println(i + "/" + fileLines.size());
		}

		writeFileFromList(fileName, fileLines);
	}
	public static void trimLines(String fileName) {
		ArrayList<String> fileLines = fileList(fileName);
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(fileName, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (int i = 0; i < fileLines.size(); i++) {
			String tmp = fileLines.get(i);
			fileLines.remove(i);
			fileLines.add(i, tmp.trim());
			writer.println(fileLines.get(i));
			if (reportProgress != 0 && i % reportProgress == 0)
				System.out.println(i + "/" + fileLines.size());
		}
		writer.close();
		writeFileFromList(fileName, fileLines);
	}
	public static void removeEmptyLines(String fileName) {
		ArrayList<String> fileLines = fileList(fileName);
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(fileName, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (int i = 0; i < fileLines.size(); i++) {
			if (!fileLines.get(i).equals("") && i != fileLines.size() - 1)
				writer.println(fileLines.get(i));
			if (!fileLines.get(i).equals("") && i == fileLines.size() - 1)
				writer.print(fileLines.get(i));
			if (reportProgress != 0 && i % reportProgress == 0)
				System.out.println(i + "/" + fileLines.size());
		}
		writer.close();
	}
	public static void deleteLine(int lineNum, String fileName) {
		ArrayList<String> fileLines = fileList(fileName);
		if (lineNum <= fileLines.size())
			fileLines.remove(lineNum - 1);
		else
			throw new IndexOutOfBoundsException("No such line");

		writeFileFromList(fileName, fileLines);
	}
	public static void writeLine(int lineNum, String content,
			String fileName/* Actually the file path */, boolean...addToEnd) {
		ArrayList<String> fileLines = fileList(fileName);
		if (fileLines.size() < lineNum) {
			ArrayList<String> tmpLst = new ArrayList<String>();
			for (int i = 0; i < lineNum - fileLines.size(); i++) {
				tmpLst.add("");
			}
			fileLines.addAll(tmpLst);
		}
		if(lineNum == fileLines.size() || addToEnd[0]) fileLines.add(content);
		else fileLines.set(lineNum - 1, content);

		writeFileFromList(fileName, fileLines);
	}
	public static void writeNewLine(String fileName, String content){
		writeLine(0, content, fileName, true);
	}
	public static void writeFileFromList(String fileName, ArrayList<String> contents) {
		try {

			File fOut = new File(fileName);
			FileOutputStream fos = new FileOutputStream(fOut);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

			for (int i = 0; i < contents.size(); i++) {
				bw.write(contents.get(i));
				if (i != contents.size() - 1)
					bw.newLine();
			}
			bw.close();
		} catch (Exception e) {
			System.out.println("exception e");
			e.printStackTrace();
		}
	}
	public static ArrayList<String> ProblemClasses = new ArrayList<String>();// fileList(System.getProperty("user.dir")
	public static ArrayList<String> fileList(
			String fileName/* Actually the file path */) {
		/*
		 * Usage: String[] str = fileList("path"); *do stuff with str*
		 */
		ArrayList<String> fileLines = new ArrayList<String>();
		String line = null;
		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				fileLines.add(line);
			}

			bufferedReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return fileLines;
	}
}