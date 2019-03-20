void toDo()
{
  Serial.println("Indexing");
  newData = false; //No longer new data
  delay(100);

  //Team color
  if(input.indexOf("blue") > 0) //If input contains "blue"
  {
    teamColor = 2; //Set color  
    Serial.println("Blue");
  }

  if(input.indexOf("red") > 0) //If input contains "red"
  {
    teamColor = 1; //Set color  
    Serial.println("Red");
  }

  if(input.indexOf("purple") > 0) //If input contains "purple"
  {
    teamColor = 0; //Set color  
    Serial.println("Purple");
  }

  //Modes
  if(input.indexOf("rain") > 0) //If input contains "fire"
  {
    input = "";
    Serial.print("Understood Rain with color ");
    Serial.println(teamColor);
    rainmode();
  }

  if(input.indexOf("flash") > 0) //If input contains "flash"
  {
    input = "";
    Serial.print("Understood Flash with color ");
    Serial.println(teamColor);
    flashmode();
  }

  if(input.indexOf("solid") > 0) //If input contains "solid"
  {
    input = "";
    Serial.print("Understood Solid with color ");
    Serial.println(teamColor);
    solidmode();
  }

  if(input.indexOf("stop") > 0) //If input contains "stop"
  {
    input = "";
    Serial.print("Halting");
    while(newData != true) //when a new command has not been issued
    {
      serialEvent();
      delay(100);
      fill_solid( leds, NUM_LEDS, CRGB::Black);
      FastLED.show(); //Do the roar
    }
  }
}

