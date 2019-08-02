
// A Java program for a Server 
import java.net.*; 
import java.io.*; 
import java.util.*;
import java.util.Random.*;
import java.time.LocalDateTime;
import java.time.*;
public class tcpserver
{  char[] buffer=new  char[5000];
    //initialize socket and input stream 
    private Socket          socket   = null; 
    private ServerSocket    server   = null; 
    private DataInputStream  input   = null;
	private DataOutputStream out     = null;
     
	 //creating Random number
	 public static int generateRandom()
	 {
		 Random r=new Random();
		 return r.nextInt((25-0)+1);
	 }
  
  
  
  
    // constructor with port 
    public tcpserver(int port) 
    { 
        // starts server and waits for a connection 
        try
        {
            server = new ServerSocket(port); 
            System.out.println("Server started"); 
            System.out.println("Waiting for a client ..."); 
            socket = server.accept(); 
            System.out.println("Client accepted"); 
			System.out.println(socket.getRemoteSocketAddress().toString().replace("/",""));
			System.out.println(port);
             
			 
			  input  = new DataInputStream(System.in); 
  
            // writes data through socket
            out    = new DataOutputStream(socket.getOutputStream()); 
            
  
            String line = ""; 
            int z=0;
			long sendtime;
			sendtime=5000;
            // reads message from client until "Over" is sent 
            while (z!=1) 
            { 
                try
                { line ="";
				long maintime=System.currentTimeMillis();
				System.out.println("the start time"+java.time.LocalTime.now());
				for(int i=1;i<=1500;i++)
				{
					int temp=generateRandom();
					char ch=(char)(temp+97);
					line=line+Character.toString(ch);
				}
                 				
					 
                    //line = input.readLine(); 
                    out.writeUTF(line);
                    long lasttime=System.currentTimeMillis();
					System.out.println("the end time"+java.time.LocalTime.now());
                     try
					 {					 
					if((lasttime-maintime)<sendtime)
					{System.out.println("server is waiting");
						Thread.sleep(sendtime-(lasttime-maintime));
					 }
					 }
					 catch(InterruptedException e)
					 {
						 System.out.println(e);
					 }
                } 
                catch(IOException i) 
                { 
                    System.out.println(i); 
                }
            System.out.println("if want to end communication press 1 else 0");
		      z=Integer.parseInt(input.readLine());				
            }
             line="Over";			
			out.writeUTF(line);
            System.out.println("Closing connection"); 
            // close connection 
            socket.close(); 
            input.close();
			out.close();
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
    } 
  
    public static void main(String args[]) 
    { 
        tcpserver server = new tcpserver(5000); 
    } 
} 
