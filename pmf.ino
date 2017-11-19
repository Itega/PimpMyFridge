#include <math.h>

int pinFrigo = 1;
int pinTemperature = 2;
int pinHumidite = 3;
int pinPeltier = 4;
int pinLED = 5;

String data;
int temperatureActuelle=0;
int temperatureConsigne=0;

void sendData(String data) {
  String str = "#" + data + "#\n";
  Serial.write(str.c_str());
  Serial.flush();
}

double readHumidite(){
  return 0;
}

double readTemperature(){
  int analog = analogRead(pinTemperature);
  double A=0.00104,B=0.00024,C=1.50069E-7,LR,vOut,vIn = 5, RT, R2 =10000, T;

  vOut = (analog*vIn)/1023;
  RT = ((R2*vIn)/vOut)-R2;
  LR = log(RT);
  T = (1/(A+B*LR+C*pow(LR,3)))-273.15;
  return T;
}

void setup() {
  Serial.setTimeout(500);
  Serial.begin(115200);
}

void loop() {
  while(Serial.available() > 0)
  {
    data = Serial.readString();
    data.trim();
    if(data.startsWith("consigne:"))
    {
      data.replace("consigne:", "");
      temperatureConsigne = data.toFloat();
    }
    if(data.startsWith("frigo:"))
    {
      data.replace("frigo:", "");
      if(data.toInt() == 1)
        digitalWrite(pinFrigo, 1);
      else
        digitalWrite(pinFrigo, 0);
    }
    if(data.startsWith("led:"))
    {
      data.replace("led:", "");
      if(data.toInt() == 1)
        digitalWrite(pinLED, 1);
      else
        digitalWrite(pinLED, 0);
    }
  }

  sendData("t"+String(readTemperature()));
  sendData("h"+String(readHumidite()));

  delay(1000);
}
