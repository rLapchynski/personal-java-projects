package MiscMain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PowerShellCommand {

 public static String run(String command) throws IOException {
	 String retStr = "";
  //String command = "powershell.exe  your command";
  // Executing the command
  Process powerShellProcess = Runtime.getRuntime().exec(command);
  // Getting the results
  powerShellProcess.getOutputStream().close();
  String line;
  BufferedReader stdout = new BufferedReader(new InputStreamReader(
    powerShellProcess.getInputStream()));
  while ((line = stdout.readLine()) != null) {
   retStr += line + "\n";
  }
  stdout.close();
  BufferedReader stderr = new BufferedReader(new InputStreamReader(
    powerShellProcess.getErrorStream()));
  while ((line = stderr.readLine()) != null) {
	  retStr += line + "\n";
  }
  stderr.close();
  
  return retStr;
 }

}