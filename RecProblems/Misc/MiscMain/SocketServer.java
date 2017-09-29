package MiscMain;

import java.io.*;
import java.net.*;

public class SocketServer {

	public static void main(String[] args) throws IOException {
		//ServerSocket sock = new ServerSocket(8081);
		Socket client = new Socket("127.0.0.1", 8081);
		InputStreamReader in = new InputStreamReader(client.getInputStream());
		String line;
		while(true){
			line = "" + in.read();
			if(!line.equals("")){
				System.out.println(line);
			} else if(line.equals("exit")){
				break;
			} else{
				System.out.println("Read");
			}
		}
		client.close();
	}
	

	public void listenSocket(int socket){
		ServerSocket server = null;
		Socket client = null;
		BufferedReader in = null;
		PrintWriter out = null;
		
	  try{
	    server = new ServerSocket(socket); 
	  } catch (IOException e) {
	    System.out.println("Could not listen on port 4321");
	    System.exit(-1);
	  }
	  
	  try{
	    client = server.accept();
	  } catch (IOException e) {
	    System.out.println("Accept failed: 4321");
	    System.exit(-1);
	  }
	
	  try{
	   in = new BufferedReader(new InputStreamReader(
	                           client.getInputStream()));
	   out = new PrintWriter(client.getOutputStream(), 
	                         true);
	  } catch (IOException e) {
	    System.out.println("Read failed");
	    System.exit(-1);
	  }
	  
	  while(true){
		  String line;
	      try{
	        line = in.readLine();
	        
	        out.println(line);
	      } catch (IOException e) {
	        System.out.println("Read failed");
	        try {
				server.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
	        System.exit(-1);
	      }
	    }
	}
	
	    

}
