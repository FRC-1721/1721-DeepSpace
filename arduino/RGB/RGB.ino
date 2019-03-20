#include <FastLED.h>
#define LED_PIN 7 //The control wire for the light strip
#define NUM_LEDS 60 //The number of LEDs in the strip

CRGB leds[NUM_LEDS]; //Set the number of LEDs in the strip

boolean newData = false; //Becomes true only as long as there is unindexed information
int teamColor = 0; //0 no team purple, 1 red team red, 2 blue team blue
String input = ""; //Populated with the current command not yet run
int lineLength = 2;

/*
   To use:
   Send any string upon startup, this will toggle the controler on
   Send any string containing "blue", "red" or "bread" to signify team color
   Send any string containing "fire", "flash" or "rainbow" to run the corrosponding mode
*/

void setup()
{
  randomSeed(analogRead(0));
  Serial.begin(57600); //Start the Serial
  delay(10);
  // reserve 200 bytes for the imput
  input.reserve(200);

  FastLED.addLeds<NEOPIXEL, LED_PIN>(leds, NUM_LEDS); //Configure for model and baud
  /*
     Moral support provided by Audrey C
  */

  while (Serial.available() == 0) //While no commands have been sent
  {
      fill_solid( leds, NUM_LEDS, CRGB::Purple);
      FastLED.show();
  }
  input = ""; //Imput empty
  delay(10);
}

void loop()
{
  serialEvent(); //Read from serial when available

  if (newData == true)
  {
    toDo();
  }
  delay(1);
  Serial.println(input);
}
