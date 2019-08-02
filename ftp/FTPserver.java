import java.net.*;
import java.io.*;
import java.util.*;

public class FTPserver {
	DataInputStream in;
	DataOutputStream out;
	FileInputStream f;
	String CU;
	String CP;
	Scanner sc=new Scanner(System.in);

	 FTPserver() {
		try {
			System.out.println("Server started waiting for client");
			ServerSocket ss=new ServerSocket(21);
			Socket s=new Socket();
			s=ss.accept();
			System.out.println("client connected");
			
			in=new DataInputStream(s.getInputStream());
			out=new DataOutputStream(s.getOutputStream());
			
			//out.writeUTF("enter credentials");
			//CU=in.readUTF();
			CP=in.readUTF();
			
			//System.out.println(CU);
			System.out.println(CP);
			
			String ack="Success";
			String nack="Failure";
			CU="nit";
			
			if(CP.equals(CU)) {
				System.out.println("Server-Client Connected");
				out.writeUTF(ack);
				s.close();ss.close();
				ServerSocket ss1=new ServerSocket(23);
				Socket s1=new Socket();
				s1=ss1.accept();
				in=new DataInputStream(s1.getInputStream());
				out=new DataOutputStream(s1.getOutputStream());
			while(true)	
			{
			System.out.println("Enter your choice: 1.SEND FILE    2.RECEIVE FILE");
			int option=sc.nextInt();
			
			out.write(option);
			
			if(option==1) {
				sendfile(s1);
			}
			else if(option==2) {
				recievefile(s1);
			}
			else
			{break;
			}
			}
			}
			else
			{
				
				out.writeUTF(nack);
			}
			System.out.println("Closing Connection");
	//		s1.close();
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
			while(true) {
				try{st=in.readUTF();
				System.out.println(st);}
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
		FTPserver f=new FTPserver();
	}	

}
