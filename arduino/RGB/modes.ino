void rainmode() //Rainmode
{
  while(newData != true) //when a new command has not been issued
  {
    Serial.println("In Rain Mode"); //Debug
    serialEvent(); //Check to make sure no new commands have been issued
    int ledLocation = random(0, NUM_LEDS); //Choose a random location in the string
    int rainVolume = 40; //Choose a random value for the rain pattern to occur
    
    delay(10); //Pause
    for (int i = 0; i < rainVolume; i++)
    {
      fill_solid( leds, NUM_LEDS, CRGB( rValue, gValue, bValue));
      //leds[i].setRGB( rValue, gValue, bValue);
      FastLED.show();
    }
    leds[ledLocation] = CRGB::Black;
    FastLED.show();
    
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
    fill_solid( leds, NUM_LEDS, CRGB( rValue, gValue, bValue));
    //leds[i].setRGB( rValue, gValue, bValue);
    FastLED.show();
    for (int i = 0; i < NUM_LEDS; i = i + 2)
    {
      leds[i] = CRGB::Black;
      FastLED.show(); //Do the roar
    }
    delay(flashSpeed);
    for (int i = 1; i < NUM_LEDS; i = i + 2)
    {
      leds[i] = CRGB::Black;
      FastLED.show(); //Do the roar
    }
  }
}

void solidmode()
{
  while(newData != true) //when a new command has not been issued
  {
    serialEvent();
    fill_solid( leds, NUM_LEDS, CRGB( rValue, gValue, bValue));
      //leds[i].setRGB( rValue, gValue, bValue);
    FastLED.show();
    Serial.print("I set the color to ");
    Serial.print(teamColor);
    Serial.println("!");
    delay(10);
  }
}
