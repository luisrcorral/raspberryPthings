import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStreamFactory;
import twitter4j.User;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.*;


/*
 * sudo javac -classpath .:classes:/home/pi/Documents/Projects/twitter/lib/'*' TweetTemp.java
 * sudo java -classpath .:classes:/home/pi/Documents/Projects/twitter/lib/'*' TweetTemp
 * 
 */

public class TweetTemp{

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
		if(m.find()){
			String tempstring = m.group(1);
			Float temperature = Float.parseFloat(tempstring);
			temperature = temperature/1000;
			message = "A #DS18B20 says that the #temperature in my place is " + temperature + " deg C. (Message automatically tweeted from my #RaspberryPi)";
		}	
        System.out.println("End reading temperature.");
        
        if (!message.equals("")){
        
			System.out.println("Start tweeting results...");
			System.out.println(message);
        
			ConfigurationBuilder cb = new ConfigurationBuilder();
			cb.setOAuthConsumerKey("");
			cb.setOAuthConsumerSecret("");
			cb.setOAuthAccessToken("");
			cb.setOAuthAccessTokenSecret("");
        
			try{
				TwitterFactory tf = new TwitterFactory(cb.build());
				Twitter t = tf.getInstance();
				User u = t.showUser("luis_corral");
				Status s = t.updateStatus(message);
			}
			catch(Exception exception){ 
				System.out.println("Unable to tweet. Exception happened.");
			}
		    
		    System.out.println("End tweeting");
		}	
		else { 
			System.out.println("Unable to tweet. Could not read temperature.");
		} 
     }
}

