#include <stdio.h>
#include <wiringPi.h>

int x;

int main(void){

 x = 0;

 printf ("Raspberry Pi Blinking 5l times\n");
 
 if (wiringPiSetup()== -1){
   return 1;
 }

 pinMode (0, OUTPUT);

 for (x=0; x<5; x++){
   digitalWrite(0,1);
   delay (1000);
   digitalWrite(0,0);
   delay (250);
 }

 return 0;
}