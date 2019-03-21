#include <FastLED.h>
#define LED_PIN 7 //The control wire for the light strip
#define NUM_LEDS 180 //The number of LEDs in the strip

CRGB leds[NUM_LEDS]; //Set the number of LEDs in the strip

boolean newData = false; //Becomes true only as long as there is unindexed information
int teamColor = 0; //0 no team purple, 1 red team red, 2 blue team blue
String input = ""; //Populated with the current command not yet run
int lineLength = 2;
int fillSpeed = 60;
int flashSpeed = 50;

int rValue = 0;
int gValue = 0;
int bValue = 0;

/*
   To use:
   Send any string upon startup, this will toggle the controler on
   Send any string containing "blue", "red" or "bread" to signify team color
   Send any string containing "fire", "flash" or "rainbow" to run the corrosponding mode
*/

void setup()
{
  randomSeed(analogRead(0)); //Init randomSeed
  Serial.begin(9600); //Start the Serial
  delay(10);
  input.reserve(200); // reserve 200 bytes for the imput

  FastLED.addLeds<NEOPIXEL, LED_PIN>(leds, NUM_LEDS); //Configure for model and baud
  /*
     Moral support provided by Audrey C
  */

  while (Serial.available() == 0) //While no commands have been sent
  {
      fill_solid( leds, NUM_LEDS, CRGB::Purple); //Make everything purple
      FastLED.show(); //Draw
      delay(100); //Pause
      for (int i = 0; i < NUM_LEDS; i++) //For as long as every LED has not been addressed
      {
        leds[i] = CRGB::Blue; //Change LED to blue, counting up from 1
        FastLED.show(); //Draw

        leds[NUM_LEDS - i] = CRGB::Red; //Change LED to red, counting down from NUM_LEDS
        FastLED.show(); //Draw
        delay(fillSpeed); //Pause

        if(Serial.available() != 0)
        {
          break;
        }
      }
  }
  input = ""; //Imput empty
  delay(10); //Pause
}

void loop()
{
  serialEvent(); //Read from serial when available

  if (newData == true) //If there is newdata
  {
    newData = false; //Turn off newData flag
    toDo(); //Index
  }
  delay(1); //Pause
  Serial.println(input); //Print the input for debugging
}
