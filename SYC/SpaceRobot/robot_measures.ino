#include <Wire.h>
#include <Adafruit_BMP085.h>
#include "DS3231.h"

Adafruit_BMP085 bmp;
RTClib RTC;

int isObstaclePin = 7; // This is our input pin
int isObstacle = HIGH; // HIGH MEANS NO OBSTACLE
int analogPin = 0;     // potentiometer wiper (middle terminal) connected to analog pin 3 outside leads to ground and +5V
int val = 0;           // variable to store the value read

void setup() {
    pinMode(10, OUTPUT);
    digitalWrite(10, LOW);
    pinMode(9, OUTPUT);
    digitalWrite(9, LOW);
    pinMode(11, OUTPUT);
    digitalWrite(11, LOW);
  
    pinMode(isObstaclePin, INPUT);
    pinMode(8, OUTPUT);
    digitalWrite(8, HIGH);
    Serial.begin(9600);
    if (!bmp.begin()) {
    Serial.println("Could not find a valid BMP085 sensor, check wiring!");
      while (1) {}
    }
    Wire.begin();
}

void loop() {
 
    DateTime now = RTC.now();
    Serial.print(now.year(), DEC);
    Serial.print('/');
    Serial.print(now.month(), DEC);
    Serial.print('/');
    Serial.print(now.day(), DEC);
    Serial.print(' ');
    Serial.print(now.hour(), DEC);
    Serial.print(':');
    Serial.print(now.minute(), DEC);
    Serial.print(':');
    Serial.print(now.second(), DEC);

    Serial.print('|');
    
    val = analogRead(analogPin); // read the input pin
    Serial.print(val);

    Serial.print('|');

    Serial.print(bmp.readTemperature());

    Serial.print('|');

    Serial.print(bmp.readPressure());
    
    Serial.print('|');
  
    isObstacle = digitalRead(isObstaclePin);
    if (isObstacle == LOW) {
      Serial.println("1");
    } else {
      Serial.println("0");
    }
    
    delay(1000);
}
