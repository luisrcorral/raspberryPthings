#include <stdio.h>
#include <wiringPi.h>

const int butPin = 17; // Pin wired to the pushbutton (physic pin 11)
const int led1Pin = 18; // Pin wired to the first led (physic 12)
const int led2Pin = 23; // Pin wired to the second led (physic 16)


const int pwmValue = 75;

int main (void) {
   //setup
   wiringPiSetupGpio();

   pinMode(led1Pin, OUTPUT);        // Setting first led as regular output
   pinMode(led2Pin, OUTPUT);        // Setting second led as regular output
   pinMode(butPin, INPUT);          // Setting pushbutton as input
   pullUpDnControl(butPin, PUD_UP); // Enable pull-up resistor on button

   printf("Blinker is running");

   while(1) {
 
      if(digitalRead(butPin)){            //If TRUE, pushbutton is released
         digitalWrite(led1Pin, 1);        //Led1 lit
         digitalWrite(led2Pin, 0);        //Led2 off
      }
      else {                              // Pushbutton is pressed
         digitalWrite(led1Pin, 0);         // Led1 off
         digitalWrite(led2Pin, 1);         // Led2 lit
         delay(75);
         digitalWrite(led2Pin, 0);         // Led2blinks
         delay(75);
      }
   }

   return 0;
}