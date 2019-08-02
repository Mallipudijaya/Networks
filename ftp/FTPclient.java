import java.net.*;
import java.io.*;
import java.util.*;

public class FTPclient {
	DataInputStream in;
	DataOutputStream out;
	FileInputStream f;
	String Username;
	String Pass;
	Scanner sc=new Scanner(System.in);
	
	public FTPclient() {
		try {
			//System.out.println("Server started waiting for client");
			
			Socket s=new Socket("localhost",21);
			
			System.out.println("client connected");
			in=new DataInputStream(s.getInputStream());
			out=new DataOutputStream(s.getOutputStream());
			Username=sc.next();
			Pass=sc.next();
			out.writeUTF(Pass);
			
			String ack=in.readUTF();
			System.out.println(ack);
			if(ack.equals("Success")) {
				s.close();
			System.out.println("Client is verified from server");
			Socket s1=new Socket("localhost",23);	
			System.out.println("client connected to other port");
			in=new DataInputStream(s1.getInputStream());
			out=new DataOutputStream(s1.getOutputStream());
			while(true){
			int option=in.read();
			
			if(option==1) {
				recievefile(s1);
			}
			else if(option==2) {
				sendfile(s1);
			}
			
			else
			{
				break;
			}
			}
			}
			else 
			{
				System.out.println("wrong credentials");
				//System.exit(0);
			}
			
			System.out.println("Closing Connection");
			//s1.close();
		}
			catch (Exception e) {
				
			e.printStackTrace();
		}
		
	}

	private void recievefile(Socket s1) {
		//Socket ss=s;
		 
		 //System.out.println("Enter filename:");
		 //String fn=sc.next();
		 String st;
		 try {
			 //st=in.readUTF();
		//BufferedReader br=new BufferedReader(new FileReader(fn));
			
			 while(true) 
			{
			try
			    {
				st=in.readUTF();
				System.out.println(st);
			      }
				catch(EOFException e)
					{break;}
			}
			System.out.println("file received ");
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		
	}

	private void sendfile(Socket s1) {
		
	 //Socket ss=s;
	 
	 System.out.println("Enter filename:");
	 String fn=sc.next();

	 File f=new File(fn);
	 String st,st1;
	 st1="";
	 try {
		BufferedReader br=new BufferedReader(new FileReader(f));
		while((st=br.readLine())!=null) {
			if(st1.length()<1500)
				st1=st1+st;
			if(st1.length()==1500)
			{
			System.out.println(st1);
			out.writeUTF(st1);st1="";}
		}
		System.out.println(st1);
		out.writeUTF(st1);
		System.out.println("file sent succesfully");
		out.close();
		br.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
		
	}
	public static void main(String args[]) {
		FTPclient fc=new FTPclient();
	}

}

