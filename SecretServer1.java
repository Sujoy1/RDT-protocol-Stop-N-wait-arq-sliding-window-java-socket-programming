import java.net.*;
import java.io.*;
import java.util.*;
public class SecretServer1
{

	public static void main(String[] args)
{

		String[] secret = {"ZERO", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN"};


		DatagramSocket ss = null;
		DatagramPacket rp, sp;
		byte[] rd, sd;
		String a;
		InetAddress ip;
		int port;

		try {
			ss = new DatagramSocket(Integer.parseInt(args[0]));
			System.out.println("Server is up....");
			System.out.println("Waiting for RDT initiation and first ping request from Client...");

			int consignment=0;
			String strGreeting;
			int result = 0; // number of bytes read

			while(true){

				rd=new byte[100];
				sd=new byte[512];

				rp = new DatagramPacket(rd,rd.length);

				ss.receive(rp);

				// get client's consignment request from DatagramPacket
				ip = rp.getAddress();
				port =rp.getPort();
				System.out.println("Client IP Address = " + ip);
				System.out.println("Client port = " + port);

				strGreeting = new String(rp.getData());
				System.out.println("Client says = " + strGreeting);
 				//System.out.println("ACK"+consignment+"\t"+"\n");
				// prepare data
				if (consignment == (secret.length))
                                {                                                // last consignment


 					sd = new String("END").getBytes();
				}


				else
				{

						sd = secret[consignment].getBytes();

				}

				sp=new DatagramPacket(sd,sd.length,ip,port);

				// send data
				ss.send(sp);



				System.out.println(" RDT->"+"Message with Sequence-number:  "+consignment+"  and payload :  "+ secret[consignment]+"   successfully sent!"+"\t"+"\n");



				rp=null;
				sp = null;

				if (consignment == secret.length)
				{

					consignment = 0;

				}
				else
				{
				if(consignment == ((secret.length)-1)){
					System.out.println("The RDT Protocol has ended . No more Datagrams available ! Process finished successfully ....Server is down"+"\t"+"\n");
				}


				else
				{
					consignment++;


				}
				}


			} // while true

		} //try
                catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
		catch (ArrayIndexOutOfBoundsException e) {
			System.out.println(" ");
		}

	}
}
