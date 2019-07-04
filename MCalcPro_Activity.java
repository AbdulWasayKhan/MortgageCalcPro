package ca.yorku.eecs.mcalcpro;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Locale;
import ca.roumani.i2c.MPro;

public class MCalcPro_Activity extends AppCompatActivity implements TextToSpeech.OnInitListener, SensorEventListener
{
    private static final String TAG = "DEBUG: MCalcPro";
    //added an attribute tts to hold the TextToSpeech instance
    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mcalcpro_layout);

        Log.d(TAG, "onCreate");
        //initializing the attribute of texttospeech
        this.tts = new TextToSpeech(this,this);
        SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }
    /*
    * This is where enginecommunicates with us. The constructor starts the engine and during it's
    * initialization phase it invokes onInit method and passes intStatus to tell us if it managed to start
    * successfully(if its value is 0) or not(value -1). We can do checking in debugging. In addition
    * onInit method enables us to query the available languages to choose
    * */
    public void onInit(int initStatus)
    {
        this.tts.setLanguage(Locale.US);
    }

    public void analyzeClicked(View v)
    {
        Log.d(TAG, "analyze button Clicked");
        try {
            MPro mp = new MPro();
//            double x = MCalcPro_Activity.INTEREST_MAX;

            EditText cashPrice = findViewById(R.id.pBox);
            String cash = cashPrice.getText().toString();
            mp.setPrinciple(cash);

            EditText amortization = findViewById(R.id.aBox);
            String amortize = amortization.getText().toString();
            mp.setAmortization(amortize);

            EditText interest = findViewById(R.id.iBox);
            String inter = interest.getText().toString();
            //this below two lines doesn't work.
//            double interesting = Double.parseDouble(inter);
//            if(interesting < x) {
                mp.setInterest(inter);
//            }
            /*
             * cash = 400000
             * amort = 20
             * interest = 5
             * outputs the monthly payment rounded to nearest cent with a 1000-separator
             * and the outstanding balance on the 2nd anniversary rounded to nearest dollar, with a 1000 separato, and right justified
             * in afield of 16 characters.
             * */
            System.out.println(mp.computePayment("%,.2f"));
            System.out.println(mp.outstandingAfter(2,"%,16.0f"));

            String s = "Monthly Payment = " + mp.computePayment("%,.2f");
            s += "\n\n";
            s += "By making this payments monthly for " + amortize + " years, the mortgage will be paid in full. But if you " +
                    "terminate the mortgage on its nth anniversary, the balance still owing depends on n as shown below: \n\n\n";

            String firstColumn = "n";
            String secondColumn = "Balance";
            s += String.format(Locale.CANADA,"%8s %16s",firstColumn, secondColumn);
            s += "\n\n";
            s += String.format(Locale.CANADA,"%8d", 0) + mp.outstandingAfter(0, "%16.0f\n");
            s += "\n\n";
            s += String.format(Locale.CANADA,"%8d", 1) + mp.outstandingAfter(1, "%16.0f\n");
            s += "\n\n";
            s += String.format(Locale.CANADA,"%8d", 2) + mp.outstandingAfter(2, "%16.0f\n");
            s += "\n\n";
            s += String.format(Locale.CANADA,"%8d", 3) + mp.outstandingAfter(3, "%16.0f\n");
            s += "\n\n";
            s += String.format(Locale.CANADA,"%8d", 4) + mp.outstandingAfter(4, "%16.0f\n");
            s += "\n\n";
            s += String.format(Locale.CANADA,"%8d", 5) + mp.outstandingAfter(5, "%16.0f\n");
            s += "\n\n";
            s += String.format(Locale.CANADA,"%8d", 6) + mp.outstandingAfter(6, "%16.0f\n");
            s += "\n\n";
            s += String.format(Locale.CANADA,"%8d", 7) + mp.outstandingAfter(7, "%16.0f\n");
            s += "\n\n";
            s += String.format(Locale.CANADA,"%8d", 8) + mp.outstandingAfter(8, "%16.0f\n");
            s += "\n\n";
            s += String.format(Locale.CANADA,"%8d", 9) + mp.outstandingAfter(9, "%16.0f\n");
            s += "\n\n";
            s += String.format(Locale.CANADA,"%8d", 10) + mp.outstandingAfter(10, "%16.0f\n");
            s += "\n\n";
            s += String.format(Locale.CANADA,"%8d", 11) + mp.outstandingAfter(11, "%16.0f\n");
            s += "\n\n";
            s += String.format(Locale.CANADA,"%8d", 12) + mp.outstandingAfter(12, "%16.0f\n");
            s += "\n\n";
            s += String.format(Locale.CANADA,"%8d", 13) + mp.outstandingAfter(13, "%16.0f\n");
            s += "\n\n";
            s += String.format(Locale.CANADA,"%8d", 14) + mp.outstandingAfter(14, "%16.0f\n");
            s += "\n\n";
            s += String.format(Locale.CANADA,"%8d", 15) + mp.outstandingAfter(15, "%16.0f\n");
            s += "\n\n";
            s += String.format(Locale.CANADA,"%8d", 16) + mp.outstandingAfter(16, "%16.0f\n");
            s += "\n\n";
            s += String.format(Locale.CANADA,"%8d", 17) + mp.outstandingAfter(17, "%16.0f\n");
            s += "\n\n";
            s += String.format(Locale.CANADA,"%8d", 18) + mp.outstandingAfter(18, "%16.0f\n");
            s += "\n\n";
            s += String.format(Locale.CANADA,"%8d", 19) + mp.outstandingAfter(19, "%16.0f\n");
            s += "\n\n";
            s += String.format(Locale.CANADA,"%8d", 20) + mp.outstandingAfter(20, "%16.0f\n");

            /*Once the composition of s is done we poke it to in the view*/
            ((TextView) findViewById(R.id.output)).setText(s);

            /*Whenever you want to read a loud a string s. This is where the action happens*/
            tts.speak(s,TextToSpeech.QUEUE_FLUSH,null);
        }
        catch (Exception e)
        {
            //display e.getMessgae()
            Toast label = Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG);
            label.show();
        }

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
            Log.d(TAG, "onSensorChanged called. We can't check on the comp because we won't shake our laptop.");

            if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                double ax = event.values[0];
                double ay = event.values[1];
                double az = event.values[2];

                double a = Math.sqrt(ax * ax + ay * ay + az * az);
                if (a > 20) {
                    ((EditText) findViewById(R.id.pBox)).setText("");
                    ((EditText) findViewById(R.id.aBox)).setText("");
                    ((EditText) findViewById(R.id.iBox)).setText("");
                    ((TextView) findViewById(R.id.output)).setText("");
                    tts.stop();

                }
            }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
