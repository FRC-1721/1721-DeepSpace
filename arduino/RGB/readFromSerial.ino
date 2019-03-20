void serialEvent() 
{
  while (Serial.available()) //As long as serial is available
  {
    char inChar = (char)Serial.read(); // get the new byte
    input += inChar;// add it to the imput
    delay(10);
    
    newData = true; //Set newdata flag to true
    Serial.println("Scanning");
  }
}
