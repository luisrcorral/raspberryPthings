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

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class TweetPi{

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Starting Tweeting Exercise.");
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setOAuthConsumerKey("");
        cb.setOAuthConsumerSecret("");
        cb.setOAuthAccessToken("");
        cb.setOAuthAccessTokenSecret("");
        
        // create gpio controller
        final GpioController gpio = GpioFactory.getInstance();

        // provision gpio pin #00 & #01 as an output pins and blink
        final GpioPinDigitalOutput led0 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00);
        final GpioPinDigitalOutput led1 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01);
        
        
        try{
			System.out.println("Attempting to tweet...");
			TwitterFactory tf = new TwitterFactory(cb.build());
			Twitter t = tf.getInstance();
			User u = t.showUser("luis_corral");
			Status s = t.updateStatus("And now, tweeting from a Raspberry Pi");
        }
        
        catch(Exception exception){
			System.out.println("Something bad happened.");
        }

    }
}

