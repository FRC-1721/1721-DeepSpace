void rainmode()
{
  while(newData != true) //when a new command has not been issued
  {
    Serial.println("In Rain Mode");
    serialEvent();
    int ledLocation = random(0, NUM_LEDS);
    int rainVolume = 40;
      
    if(teamColor == 2)
    {
      delay(10);
      for (int i = 0; i < rainVolume; i++) 
      {
        fill_solid( leds, NUM_LEDS, CRGB::Blue);
        FastLED.show();
      }
      leds[ledLocation] = CRGB::Black;
      FastLED.show();
    }

    if(teamColor == 1)
    {
      delay(10);
      fill_solid( leds, NUM_LEDS, CRGB::Red);
      FastLED.show();
      for (int i = 0; i < rainVolume; i++) 
      {
        leds[ledLocation] = CRGB::Black;
        FastLED.show();
      }
    }

    if(teamColor == 0)
    {      
      delay(10);
      fill_solid( leds, NUM_LEDS, CRGB::Purple);
      FastLED.show();
      for (int i = 0; i < rainVolume; i++) 
      {
        leds[ledLocation] = CRGB::Black;
        FastLED.show();
      }
    }
    delay(1);
    Serial.print("RainColor ");
    Serial.println(teamColor);
  }
}

void flashmode()
{
  while(newData != true) //when a new command has not been issued
  {
    serialEvent();
    if(teamColor == 2)
    {
      delay(flashSpeed);
      fill_solid( leds, NUM_LEDS, CRGB::Blue);
      FastLED.show(); //Do the roar

      delay(flashSpeed);
      fill_solid( leds, NUM_LEDS, CRGB::Black);
      FastLED.show(); //Do the roar
    }

    if(teamColor == 1)
    {
      delay(flashSpeed);
      fill_solid( leds, NUM_LEDS, CRGB::Red);
      FastLED.show(); //Do the roar

      delay(flashSpeed);
      fill_solid( leds, NUM_LEDS, CRGB::Black);
      FastLED.show(); //Do the roar
    }

    if(teamColor == 0)
    {
      delay(flashSpeed);
      fill_solid( leds, NUM_LEDS, CRGB::Purple);
      FastLED.show(); //Do the roar

      delay(flashSpeed);
      fill_solid( leds, NUM_LEDS, CRGB::Black);
      FastLED.show(); //Do the roar
    }
    Serial.print("I set the color to ");
    Serial.print(teamColor);
    Serial.println("!");
    delay(10);
  }
}

void solidmode()
{
  while(newData != true) //when a new command has not been issued
  {
    serialEvent();
    if(teamColor == 2)
    {
      delay(100);
      fill_solid( leds, NUM_LEDS, CRGB::Blue);
      FastLED.show(); //Do the roar
    }

    if(teamColor == 1)
    {
      delay(100);
      fill_solid( leds, NUM_LEDS, CRGB::Red);
      FastLED.show(); //Do the roar
    }

    if(teamColor == 0)
    {
      delay(100);
      fill_solid( leds, NUM_LEDS, CRGB::Purple);
      FastLED.show(); //Do the roar
    }
    Serial.print("I set the color to ");
    Serial.print(teamColor);
    Serial.println("!");
    delay(10);
  }
}
