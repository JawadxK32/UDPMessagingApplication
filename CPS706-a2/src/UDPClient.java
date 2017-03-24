import java.io.*;
import java.net.*;
import java.util.*;

/**
 * @authors Jawad Khudadad #500526749 Section 03
 * Farzad Vafaeinezhad #500583434 
 * Ievgeni Krupchenko #500567156
 * 
 * Client Class that constructs the client that connects to a server
 */
public class UDPClient{
	
	/**
	 * Main Class of the client that creates a new instance of ClientUI and executes it.  
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws IOException {
		
		UDPClient client = new UDPClient();
		client.run();
	}
	
	/**
	 * This method creates DatagramPacket, receives and send packets to the server
	 */
	public void run(){
		
		try{
			
			DatagramSocket clientSocket = new DatagramSocket();
			clientSocket.setSoTimeout(30000);
			InetAddress ipAddress = InetAddress.getByName("localhost");
			
			int messageCount=0;
			byte[] sendData = new byte[1024];
			byte[] receiveData = new byte[1024];
			
			String message = "Message of the day";
			sendData = message.getBytes();
			
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,ipAddress,9888);
			clientSocket.send(sendPacket); 
			
			DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);
			clientSocket.receive(receivePacket);
			
			String messageOfDay = new String(receivePacket.getData());
			String oldMessage="";
			
			while(!(messageOfDay.equals(oldMessage))){
			System.out.print("RECEIVED: "+(messageCount) + ") "+ messageOfDay);
			messageCount++;
			oldMessage = messageOfDay;
			}
			
			clientSocket.close();
			
		}
		catch(IOException e){
			System.out.println(e);
    }
  }
}
