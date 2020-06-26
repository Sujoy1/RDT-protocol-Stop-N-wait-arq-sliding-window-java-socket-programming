
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * This class reads bytes from a stream, creates consignments and 
 * puts each consignment into a message
 * 
 * Message Format (without in-between space)
 * RDT sequence_number payload CRLF, or
 * RDT sequence_number payload END_CRLF, or
 * 
 * @author eekian
 */
public class MessageFactory {
    
    public static int CONSIGNMENT = 10;
    public static int HEX_PER_LINE = 10;
    public static byte[] RDT = new byte[] { 0x52, 0x44, 0x54 };
    public static byte[] SEQ_0 = new byte[] { 0x30 };
    public static byte[] END = new byte[] { 0x45, 0x4e, 0x44 };
    public static byte[] CRLF = new byte[] { 0x0a, 0x0d };
    
    
    public static void main(String[] args) {
        
        FileInputStream myFIS = null;
        byte[] myData = new byte[CONSIGNMENT];
        byte[] myLastData;
        byte[] myMsg;
        int bytesRead = 0;
        int i; // counter for copying bytes in array
        
        try {
            myFIS = new FileInputStream(args[0]);
            while (bytesRead != -1) {
                bytesRead = myFIS.read(myData);
                             
                if (bytesRead > -1) {
                    printBytesAsHex(myData);
                    System.out.println("data consignment has " + bytesRead + " bytes");  
               
                    if (bytesRead < CONSIGNMENT) {
                        // last consignment
			// make a special byte array that exactly fits the number of bytes read 
			// otherwise, the consignment may be padded with junk data
                        myLastData = new byte[bytesRead];
                        for (i=0; i<bytesRead; i++) {
                            myLastData[i] = myData[i];
                        }

                        myMsg = concatenateByteArrays(RDT, SEQ_0, myLastData, END, CRLF);

                    } else {

                        myMsg = concatenateByteArrays(RDT, SEQ_0, myData, CRLF);
                    }
                
                    // prepare to send, concat bytes
                    printBytesAsHex(myMsg);
                    System.out.println("message has " + myMsg.length + " bytes");
                 
                }
            }
                      
        } catch (FileNotFoundException ex1) {
            System.out.println(ex1.getMessage());
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            
        }finally {
		
		try {
			myFIS.close();
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}	
        }    
    }
    
    public static String byteToHex(byte b) {
        int i = b & 0xFF;
        return Integer.toHexString(i);
    }
    
    public static void printBytesAsHex(byte[] bytes) {
        
        int i=0;
        int j=0;
        while (i<bytes.length) {
            while (i<bytes.length && j<HEX_PER_LINE) {
                System.out.print("0x" + byteToHex(bytes[i++]) + " ");
                j++;
            }
            System.out.println(" ");
            j = 0;
        }
        
    }
    
    public static byte[] concatenateByteArrays(byte[] a, byte[] b, byte[] c, byte[] d) {
        byte[] result = new byte[a.length + b.length + c.length + d.length]; 
        System.arraycopy(a, 0, result, 0, a.length); 
        System.arraycopy(b, 0, result, a.length, b.length);
        System.arraycopy(c, 0, result, a.length+b.length, c.length);
        System.arraycopy(d, 0, result, a.length+b.length+c.length, d.length);
        return result;
    }
    
    public static byte[] concatenateByteArrays(byte[] a, byte[] b, byte[] c, byte[] d, byte[] e) {
        byte[] result = new byte[a.length + b.length + c.length + d.length + e.length]; 
        System.arraycopy(a, 0, result, 0, a.length); 
        System.arraycopy(b, 0, result, a.length, b.length);
        System.arraycopy(c, 0, result, a.length+b.length, c.length);
        System.arraycopy(d, 0, result, a.length+b.length+c.length, d.length);
        System.arraycopy(e, 0, result, a.length+b.length+c.length+d.length, e.length);
        return result;
    }
}
    

