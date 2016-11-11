import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class LedControl {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("<--Pi4J--> GPIO Blink Example ... started.");

        // create gpio controller
        final GpioController gpio = GpioFactory.getInstance();

        // provision gpio pin #00 & #01 as an output pins and blink
        final GpioPinDigitalOutput led0 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00);
        final GpioPinDigitalOutput led1 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01);

	//leds down
	System.out.println("Leds down");
	led0.low();
	led1.low();
	Thread.sleep(1000);
	
	//Leds up
	System.out.println("Leds up");
	led0.high();
	led1.high();

        // continuously blink the led every 1/2 second for 15 seconds
        led0.blink(500, 15000);
        led1.blink(1000);

        System.out.println(" ... LED1 will continue blinking until the program is terminated.");
        System.out.println(" ... PRESS <CTRL-C> TO STOP THE PROGRAM.");

        // keep program running until user aborts (CTRL-C)
        while(true) {
            Thread.sleep(500);
        }

       
    }
}

