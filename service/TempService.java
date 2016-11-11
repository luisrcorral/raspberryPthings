import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.*;



/*
 * sudo javac -cp "./javax.ws.rs-api-2.0.rev.A.jar" TempService.java
 * sudo java TempService
 * 
 */

public class TempService{

    public static void main(String[] args) throws FileNotFoundException {

        System.out.println("Start Reading Temperature...");
        BufferedReader br = new BufferedReader(new FileReader("/sys/bus/w1/devices/28-0000075aafc6/w1_slave"));
        String tempfile = "";
        
		try {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			tempfile = sb.toString();
			
			br.close();
		} 
		catch (IOException e){
			System.out.println("Exception reading temp file");
		}
		
		Pattern p = Pattern.compile("t=(.*)");
		Matcher m = p.matcher(tempfile);
		String message  = "";
		String tempstring = "";
		Float temperature = 0.0f;
		String temp4service = "";
		
		if(m.find()){
			tempstring = m.group(1);
			temperature = Float.parseFloat(tempstring);
			temperature = temperature/1000;
			message = "A #DS18B20 says that the #temperature in my place is " + temperature + " deg C. (Message automatically tweeted from my #RaspberryPi)";
		}	
        System.out.println("End reading temperature.");
      
        TempPutHelper myPutter = new TempPutHelper();
        temp4service = temperature + "";
        myPutter.tempPutter(temp4service);
        
        
     }
}

