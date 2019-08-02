
// A Java program for a Client 
import java.net.*; 
import java.io.*; 
  
public class tcpclient 
{ 
    // initialize socket and input output streams 
    private Socket socket            = null; 
     
	private DataInputStream in       =  null; 
     
  
    // constructor to put ip address and port 
    public tcpclient(String address, int port) 
    { 
        // establish a connection 
        try
        { 
            socket = new Socket(address, port); 
            System.out.println("Connected"); 
  
            // takes input from socket
			
			
            in = new DataInputStream( 
                new BufferedInputStream(socket.getInputStream())); 
        } 
        catch(UnknownHostException u) 
        { 
            System.out.println(u); 
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
  
        // string to read message from input 
        String line = "";
        long receive;
         receive=5000;		
        // keep reading until "Over" is input 
        while (!line.equals("Over")) 
        { 
            try
            {    
			     long maintime=System.currentTimeMillis();
				 System.out.println("the start time"+maintime/1000);
			     line = in.readUTF();
				 long lasttime=System.currentTimeMillis();
				 System.out.println("the end time"+lasttime/1000);
				 if(!line.equals("Over"))
				 {
				 System.out.println(line);
				 try
					 {					 
					if((lasttime-maintime)<receive)
					{System.out.println();
				     System.out.println();
						System.out.println("client is waiting");
						Thread.sleep(receive-(lasttime-maintime));
					 }
					 }
					 catch(InterruptedException e)
					 {
						 System.out.println(e);
					 }
                			
			    System.out.println();
				System.out.println();
				 }
            } 
            catch(IOException i) 
            { 
                System.out.println(i); 
            } 
        } 
  
        // close the connection 
        try
        { 
            in.close(); 
            socket.close(); 
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
    } 
  
    public static void main(String args[]) 
    { String add=args[0];
        tcpclient client= new tcpclient(add, 5000); 
    } 
} 

