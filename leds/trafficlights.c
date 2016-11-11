#include <stdio.h>
#include <wiringPi.h>

int x, y, redtime, greentime, yellowtime, blink;

int main(void){

 //Variables
 x = 0;
 y = 0;
 redtime = 3000;
 greentime = 4000;
 yellowtime = 1000;
 blink = 300;
 
 // Preparation
 printf ("RB Pi traffic light simulator\n");
 
 if (wiringPiSetup()== -1){
   return 1;
 }

 pinMode (0, OUTPUT);  //red
 pinMode (1, OUTPUT);  //yellow
 pinMode (2, OUTPUT);  //green


 //Traffic lights

 for (x=0; x<5; x++) {           // infinite loop

   printf ("Red light on... stop.\n");
   digitalWrite(0,1); //Switches on red light
   delay (redtime);     //Keeps it on certain secs
   digitalWrite(0,0); //Turns off red light

   printf ("Green light on... go.\n");
   digitalWrite(2,1);
   delay (greentime);
   digitalWrite(2,0);

   printf ("Attention!\n");
   for (y=0; y<3; y++) {           
      digitalWrite(2,1);
      delay (blink);
      digitalWrite(2,0);
      delay (blink);
   }
   
   printf ("Yellow light on... caution.\n");
   digitalWrite(1,1);
   delay (yellowtime);
   digitalWrite(1,0);
 
 }



 return 0;
}