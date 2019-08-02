import java.io.*;
import java.net.*;
import java.util.*;
class UDP3
{
	public static DatagramSocket serversocket;
	public static DatagramPacket dp;
	public static BufferedReader br;
	public static InetAddress ia;
	public static byte buf[] = new byte[1024];
	public static int cport = 222,sport=333;
	public static void main(String[] args) throws IOException
       {long b2=System.currentTimeMillis();
byte[] data = new byte[4];byte[] data1 = new byte[4];byte[] data2 = new byte[4];
         serversocket = new DatagramSocket(sport);
         dp = new DatagramPacket(buf,buf.length);
         br = new BufferedReader (new InputStreamReader(System.in));
         ia = InetAddress.getLocalHost();
 
         System.out.println("C is Running...");
Random rand = new Random();
    int n = rand.nextInt(3);
    data[0]=(byte)(n);data[1]=9;data[2]=0;data[3]=3;
         while(true)
         {
          serversocket.receive(dp);
          String str2 = new String(dp.getData(), 0, dp.getLength());
          if(str2.equals("exit"))
          {
           System.out.println("Terminated...");
           break;
          }
          System.out.println("B said : " );
  buf = str2.getBytes();
for(int i=0;i<3;i++)
System.out.println((int)buf[i]+" ");
   if(((int)buf[0])==0)
{
   int a= (int)(buf[1]);
  a--;
  buf[1]=(byte)a;
    data1[0]=2;data1[1]=buf[1];data1[2]=buf[2];data1[3]=buf[3];
      data2[0]=2;data2[1]=buf[1];data2[2]=buf[2];data2[3]=buf[3];
   String str5 = new String(data1);
   buf = str5.getBytes();
    serversocket.send(new DatagramPacket(buf,str5.length(),ia,sport));
   String str6 = new String(data2);
   buf = str6.getBytes();
   serversocket.send(new DatagramPacket(buf,str6.length(),ia,555));
}
Random rand1 = new Random();
    int n1 = rand1.nextInt(2);
if((int)buf[0]==2)
{
if(n1==0){
   int b= (int)(buf[1]);
  b--;
  buf[1]=(byte)b;}
if((int)buf[1]>0)
    {data1[0]=2;data1[1]=buf[1];data1[2]=buf[2];data1[3]=buf[3];
   String str5 = new String(data1);
   buf = str5.getBytes();
    serversocket.send(new DatagramPacket(buf,str5.length(),ia,sport));
   }
if((int)buf[1]==0)
    {data1[0]=1;data1[1]=buf[1];data1[2]=buf[2];data1[3]=buf[3];
   String str5 = new String(data1);
   buf = str5.getBytes();
    serversocket.send(new DatagramPacket(buf,str5.length(),ia,sport));
   }
}
if((int)buf[0]==1)
{
System.out.println((int)buf[3]+" "+(int)buf[2]+" "+ia);
}long b1=System.currentTimeMillis();
if((b1-b2)==5)
{
data1[0]=1;data1[1]=8;data1[2]=buf[2];data1[3]=buf[3];
   String str5 = new String(data1);
   buf = str5.getBytes();
    serversocket.send(new DatagramPacket(buf,str5.length(),ia,sport));
}
        /*  String str3 = new String(br.readLine());
          buf = str3.getBytes();*/
          serversocket.send(new DatagramPacket(buf,str2.length(), ia, cport));
         }
       }
    }