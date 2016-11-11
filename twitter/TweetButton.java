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
import java.io.*;
import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

/*
 * sudo javac -classpath .:classes:/opt/pi4j/lib/'*':/home/pi/Documents/Projects/twitter/lib/'*' TweetButton.java
 * sudo java -classpath .:classes:/opt/pi4j/lib/'*':/home/pi/Documents/Projects/twitter/lib/'*' TweetButton
 * 
 */

public class TweetButton{

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Starting Tweeting Exercise.");
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setOAuthConsumerKey("");
        cb.setOAuthConsumerSecret("");
        cb.setOAuthAccessToken("");
        cb.setOAuthAccessTokenSecret("");
        
        // create gpio controller
        final GpioController gpio = GpioFactory.getInstance();
        final GpioPinDigitalInput myButton = gpio.provisionDigitalInputPin(RaspiPin.GPIO_02, PinPullResistance.PULL_DOWN);
       
        myButton.setShutdownOptions(true);


        myButton.addListener(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
				String pin = event.getPin()  + "";
				String action = event.getState() + "";
                //System.out.println("Pin:(" + pin + ") - Action:(" + action + ")");
                action = action.trim();
                
                if (action.equals("HIGH")){
                   try{
					    System.out.println("Tweeting here...");
						TwitterFactory tf = new TwitterFactory(cb.build());
						Twitter t = tf.getInstance();
						User u = t.showUser("luis_corral");
						Status s = t.updateStatus("Somebody pressed a #pushbutton in my #RaspberryPi, so I am sending this nice tweet");
					}
				    catch(Exception exception){ 
						System.out.println("Unable to tweet. Exception happened.");
				    }
				 }   
				
           } 

        });
        
        System.out.println("Waiting for pushbutton.");

        // keep program running until user aborts (CTRL-C)
        while(true) {
            Thread.sleep(500);
        }
   

    }
}

