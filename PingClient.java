import java.io.*;
import java.util.*;
import java.net.*;
public class PingClient
{
        
	public static void main(String[] args) throws Exception
	{
		
		System.out.println("PingClient is now ready.....");
		DatagramSocket cs = new DatagramSocket();
		cs.setSoTimeout(3000);
                try{
		InetAddress ip=InetAddress.getByName(args[0]);
		byte[] rd,sd;
		String reply;
		DatagramPacket sp,rp;
		int count=0;   
                
		while(count<=5)
		{
			count++;
			Date d=new Date();
			String time= d + "";
			String data="PING"+count+" "+time+"\r\n";
			rd= new byte[100];
			sd=data.getBytes();
			sp=new DatagramPacket(sd,sd.length,ip,Integer.parseInt(args[1]));
			rp=new DatagramPacket(rd,rd.length);
			System.out.println(ip);
			cs.send(sp);
			try
			{
				cs.receive(rp);
				reply =new String(rp.getData());
				System.out.println(reply);
			}
			catch(SocketTimeoutException ex)
			{
				System.out.println("timeout");
			}
			
                }
                }
               
catch(ArrayIndexOutOfBoundsException e)
{
	System.out.println(" ");
}
}
}	
		


