# MortgageCalcPro
This Mortgage-Calculator-Pro app is similar to the one that I have uploaded previously in my repositories. There are only two key differences which you will find out in this one; 1) A professional look-and-feel on the outside. 2). Full API delegation on the inside.

I used the MPro API from i2c.jar from Professor Roumani book website. http://book.roumani.ca/ which has already all the necessary methods(mutators), computePayment and outstandinAfter implemented for us. I implemented two listeners in the activity;
1). TextToSpeech.OnInitListener (Reads out to us the text)
2). SensorEventListener (when we shake the device all the fields get reset(blank). )
