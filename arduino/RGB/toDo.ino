void toDo()
{
  newData = false; //No longer new data
  delay(100);

  //Team color
  if(input.indexOf("blue") > 0) //If input contains "blue"
  {
    teamColor = 2; //Set color  
  }

  if(input.indexOf("red") > 0) //If input contains "red"
  {
    teamColor = 1; //Set color  
  }

  if(input.indexOf("bread") > 0) //If input contains "bread"
  {
    teamColor = 0; //Set color  
  }

  //Flash mode
  if(input.indexOf("rain") > 0) //If input contains "fire"
  {
    rainmode();
    Serial.println("Understood Rain");
  }

  if(input.indexOf("flash") > 0) //If input contains "flash"
  {
    flashmode();
    Serial.println("Understood Flash");
  }
  newData = false;
}

