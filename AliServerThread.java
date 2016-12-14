

/**
 *This class run thread for every client .
 *And this class send the password limit and receive feedback from every client and print the message.   
 *   
 * @category Ali Saheb 
 * @author Ali Saheb 
 * @since 1.0.0
 * @see
 * @link  
 */

import java.awt.List;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class AliServerThread extends Thread{
	protected static Socket socket;
	protected static int clientNumber;
	protected static String serverPass;
	
	
/*	set the socket and client number*/
	public AliServerThread(Socket clientSocket,int clientIdenfy,String password ){
		AliServerThread.socket = clientSocket;
		AliServerThread.clientNumber = clientIdenfy;
		AliServerThread.serverPass = password;
	}
	
/*run thread for every client*/
	public void run(){
			   
		try {
			sendParam();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	  * This sendParam send the parameter of password limit to the client and receive client message
	  *
	  * @author Ali Saheb           	  		  
	  * @return List studentList 
	  * @since 1.0.0
	  * @see  
	  */
	
	public static void sendParam() throws IOException{
		AliSetPasswordLimit passLim = new AliSetPasswordLimit();
		ArrayList<AliPasswordSloat> passSloat = new ArrayList<AliPasswordSloat>();
		ArrayList<String> ali = new ArrayList<String>();
		
		passSloat = (ArrayList<AliPasswordSloat>) passLim.passwordsloat();
		ObjectOutputStream objectOutput;
		AliPasswordSloat name = passSloat.get(clientNumber);
		//System.out.println(name.getLP());
		
		/*setting client id*/
		
		name.setClient(Integer.toString(clientNumber));
		/*first try for breaking*/
		ali.add(name.getLP());
		ali.add(name.getUP());
		ali.add(serverPass);
		
		try{
			objectOutput = new ObjectOutputStream(socket.getOutputStream());
			objectOutput.writeObject(ali);
		}catch(IOException e){
			e.printStackTrace();
		}
		System.out.println("LP : "+name.getLP()+" UP: "+name.getUP()+" Client Number: "+name.getClient());
		try{
			Scanner sc = new Scanner(socket.getInputStream());
			String a = sc.nextLine();
			System.out.println(a);
		}catch(IOException e){
			System.out.println();
		}
				
	}

}
