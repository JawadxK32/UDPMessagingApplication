import java.io.*;
import java.net.*;
import java.util.*;

/**
 * @authors Jawad Khudadad #500526749 Section 03
 * Farzad Vafaeinezhad #500583434 
 * Ievgeni Krupchenko #500567156
 * 
 * UDP Server class that extends Thread
 */
public class UDPServer extends Thread{
	
	public DatagramSocket serverSocket = null;
	public int messageCount=0;
	
	public String messageOfDay1 = "If you don't mind, it doesn't matter. Have a nice day!";
	
	public String messageOfDay2 = "Declare yourself a CHAMPION in the morning for conquering evident, life battles you are going to face in life. Good Day!";
	
	public String messageOfDay3 = "You've got many big reasons to look up to God and say thanks.Great day!";
	
	public String messageOfDay4 = "Remember no one can make you feel inferior without your consent.Good Day!" ;
	
	public String messageOfDay5 = "To be the best, you must be able to handle the worst.Good Day!" ;
	
	public String messageOfDay6 = "Ever tried. Ever failed. No matter. Try Again. Fail again. Fail better";
	
	public String messageOfDay7 = "It does not matter how slowly you go as long as you do not stop." ;
						
    
	/**
	 * Main class that throws IOException
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		
		UDPServer server = new UDPServer();
		server.start();
	}
	
	/**
	 * Default constructor that creates the serverSocket and setSoTimeout
	 * @throws IOException
	 */
	public UDPServer() throws IOException {
		
		serverSocket = new DatagramSocket(9888);
		serverSocket.setSoTimeout(30000);
		System.out.println("Waiting for client to connect..");
	}
	
	/**
	 * This method creates DatagramPacket, receives and send packets to the client
	 */
	public void run(){
		
		while(true){
		
			try{
		
		byte[] recieveData = new byte[1024];
		byte[] sendData = new byte[16];
		ArrayList <byte[]> tmp = new ArrayList<byte[]>();
			
		    DatagramPacket receivePacket = new DatagramPacket(recieveData,recieveData.length);
		    serverSocket.receive(receivePacket);
		  
		    String messageRecieve = new String(receivePacket.getData());
		    System.out.println("From Client: "+(messageCount) + ") " + messageRecieve);
		    messageCount++;
		    
		    InetAddress ipAddress = receivePacket.getAddress();
		    int port = receivePacket.getPort();
		    
		    
		    		tmp = nextPacket(messageOfDay1);
		    		sendData = toByteArray(tmp);
		    		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,ipAddress,port);
		    		serverSocket.send(sendPacket);
		   
		 
	}
		catch(IOException e){
			e.printStackTrace();
			break;
		}
	 }
		serverSocket.close();	
  }
	
	public String nextPacket(){
		
		String message ="";
		
		message = (messageCount+1)+". "+ messageOfDay1 ;
		System.out.print("\nMessage Sent # "+(messageCount+1) +"!");
		messageCount++;
		
		return message;
	}
	
	
	
	/**
	 * This method splits a Text into 16 bits packets
	 * @param text
	 * @return
	 */
	public ArrayList<byte[]> nextPacket(String text){
	    
		ArrayList<byte[]> list = new ArrayList<byte[]>();
		byte[] array = new byte[16]; 
		byte[] data ;
		data = text.getBytes(); 
		
		   for(int i=0; i<data.length; i++){
			   for(int j=0; j<16; j++){
				   
				   if( j+16*i<data.length){
				   array[j] = data[j+16*i];
				   }
				   else
				   {break;}
			}
			   list.add(array);
		   }
		return list;
	}
	
	/**
	 * This method converts ArrayList<byte[]> to byte[] in order to be able to send the packet
	 * @param in
	 * @return
	 */
	public static byte[] toByteArray(ArrayList<byte[]> in) {
	    
		int n = in.size();
	    byte[] ret = new byte[n];
	    for (int i = 0; i < n; i++) {
	        ret = in.get(i);
	    }
	    return ret;
	}
	
}
