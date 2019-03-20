void rainmode()
{
  while(newData != true) //when a new command has not been issued
  {
    serialEvent();
    int ledLocation = random(0, NUM_LEDS);
    int rainVolume = 8;
      
    if(teamColor == 2)
    {
      delay(20);
      for (int i = 0; i < rainVolume; i++) 
      {
        leds[ledLocation] = CRGB::Purple;
        FastLED.show();
      }
      leds[ledLocation] = CRGB::Black;
    }

    if(teamColor == 1)
    {
      delay(20);
      leds[ledLocation] = CRGB::Purple;
      FastLED.show();
      leds[ledLocation] = CRGB::Black;
    }

    if(teamColor == 0)
    {      
      delay(20);
      leds[ledLocation] = CRGB::Purple;
      FastLED.show();
      leds[ledLocation] = CRGB::Black;
    }
    delay(50);
  }
}

void flashmode()
{
  while(newData != true) //when a new command has not been issued
  {
    serialEvent();
    if(teamColor == 2)
    {
      delay(100);
      fill_solid( leds, NUM_LEDS, CRGB::Blue);
      FastLED.show(); //Do the roar

      delay(100);
      fill_solid( leds, NUM_LEDS, CRGB::Black);
      FastLED.show(); //Do the roar
    }

    if(teamColor == 1)
    {
      delay(100);
      fill_solid( leds, NUM_LEDS, CRGB::Red);
      FastLED.show(); //Do the roar

      delay(100);
      fill_solid( leds, NUM_LEDS, CRGB::Black);
      FastLED.show(); //Do the roar
    }

    if(teamColor == 0)
    {
      delay(100);
      fill_solid( leds, NUM_LEDS, CRGB::Purple);
      FastLED.show(); //Do the roar

      delay(100);
      fill_solid( leds, NUM_LEDS, CRGB::Black);
      FastLED.show(); //Do the roar
    }
    Serial.print("I set the color to ");
    Serial.print(teamColor);
    Serial.println("!");
    delay(50);
  }
}
