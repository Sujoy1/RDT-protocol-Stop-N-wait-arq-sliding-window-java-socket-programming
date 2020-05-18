import java.net.*;
import java.io.*;
import java.util.*;

public class SecretClient1 {

	public static void main(String[] args) {

	    DatagramSocket cs = null;
      System.out.println("Client is ready ! Sending first initiation request.");
		try {
			cs = new DatagramSocket();

			byte[] rd, sd;

			String GREETING = "HELLO";
			String reply;
			DatagramPacket sp,rp;
			boolean end = false;
            int counter=1;
			while(!end)
			{
				// send Greeting
			    sd=GREETING.getBytes();
			    sp=new DatagramPacket(sd,sd.length,
									InetAddress.getByName(args[0]),
  									Integer.parseInt(args[1]));
				cs.send(sp);
				System.out.println("REQUEST  "+GREETING+"\t\n");

				// get next consignment
				rd=new byte[512];
				rp=new DatagramPacket(rd,rd.length);
			    cs.receive(rp);

				// print SECRET
				reply=new String(rp.getData());
				System.out.println(" Acknowledgement ACK with sequence number:   "+counter+"  succesfully sent !"+"\t\n");
                counter++;
				if (counter==9) // last consignment
					end = true;



			}
		    System.out.println("\nClient has finished the process !");
			cs.close();

		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}

}
