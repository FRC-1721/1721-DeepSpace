void rainbowmode()
{
  while(newData != true) //when a new command has not been issued
  {
    serialEvent();
    fill_solid( leds, NUM_LEDS, CRGB::Purple); //Make everything purple
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
