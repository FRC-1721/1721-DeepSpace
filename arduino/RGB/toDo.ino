void toDo()
{
  Serial.println("Indexing"); //For debug
  newData = false; //No longer new data
  delay(100); //Pause

  //Team color
  if(input.indexOf("blue") > 0) //If input contains "blue"
  {
    rValue = 0; //Set color  
    gValue = 0;
    bValue = 255;
    Serial.println("Blue"); //For debug
  }

  if(input.indexOf("red") > 0) //If input contains "red"
  {
    rValue = 255; //Set color  
    gValue = 0;
    bValue = 0;
    Serial.println("Red"); //For debug
  }

  if(input.indexOf("yellow") > 0) //If input contains "red"
  {
    rValue = 255; //Set color  
    gValue = 255;
    bValue = 0;
    Serial.println("Yellow"); //For debug
  }

  if(input.indexOf("purple") > 0) //If input contains "purple"
  {
    rValue = 255; //Set color  
    gValue = 0;
    bValue = 255;
    Serial.println("Purple"); //For debug
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

