#include <stdio.h>
#include <wiringPi.h>

const int butPin = 17; // Pin wired to the pushbutton (physic pin 11)
const int pwmPin = 18; // Pin wired to the first led (physic 12)
const int ledPin = 23; // Pin wired to the second led (physic 16)


const int pwmValue = 75;

int main (void) {
   //setup
   wiringPiSetupGpio();

   pinMode(pwmPin, PWM_OUTPUT); // Setting first led as pulse output
   pinMode(ledPin, OUTPUT);     // Setting second led as regular output
   pinMode(butPin, INPUT);      // Setting pushbutton as input
   pullUpDnControl(butPin, PUD_UP); //Enable pull-up resistor on button

   printf("Blinker is running");

   while(1) {
 
      if(digitalRead(butPin)){  //If TRUE, pushbutton is released
         pwmWrite(pwmPin, pwmValue); //pwm led lit
         digitalWrite(ledPin, 0);    //led off
      }
      else {                   // Pushbutton is pressed
         pwmWrite(pwmPin, 1024 - pwmValue); //pwm led dim
         digitalWrite(ledPin, 1);           //regular led on
         delay(75);
         digitalWrite(ledPin, 0);           //regular led off
         delay(75);
      }
   }


   return 0;
}