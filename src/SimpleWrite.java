import java.io.*;
import java.util.*;
import gnu.io.*;

public class SimpleWrite 
{
	private static SerialPort serialPort;
	private static OutputStream   serialOut;	

	static String portName="COM6";
	static int speed=8000;

	public  void openDoor() throws Exception 
	{
	  
	  
	  CommPortIdentifier port = CommPortIdentifier.getPortIdentifier(portName); 
      CommPort commPort = port.open(new SimpleWrite().getClass().getName(),2000);
      serialPort = (SerialPort) commPort;
      serialPort.setSerialPortParams(speed, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
      serialOut=serialPort.getOutputStream();
      System.out.println("  ########### OPEN:");
	  
	  Thread.sleep(4000); 
	   
  		System.out.println("Enter Input:");
  	    String str="1";
  		 
  	    serialOut.write(str.getBytes());
	 	serialOut.flush();
	    System.out.println("  ########### SND: "+"1".toString());
	    
 	  
   }  
}
