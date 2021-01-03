package com.example.week10sensorinclass;

import androidx.appcompat.app.AppCompatActivity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import android.graphics.Color;
import android.hardware.*;
import android.widget.TextView;
import com.squareup.seismic.ShakeDetector;
import java.util.Optional.*;
import java.util.concurrent.*;

public abstract class MainActivity extends AppCompatActivity implements ShakeDetector.Listener/*SensorEventListener*/{
    private Sensor mysensor;
    private SensorManager sensorMan;
    private ShakeDetector shakeDet;
    private TextView world, xtext, ytext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
    }

    private void initialize(){
        //mysensor = new Sensor();
        /*world = (TextView) findViewById(R.id.world);
        xtext = (TextView) findViewById(R.id.xtextView);
        ytext = (TextView) findViewById(R.id.ytextView);*/

        sensorMan = (SensorManager) getSystemService(SENSOR_SERVICE);
        mysensor = sensorMan.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        shakeDet = new ShakeDetector(this);
        shakeDet.start();
    }

    /*@Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        int  b = (int)sensorEvent.values[0];

        xtext.setText("X = " + sensorEvent.values[0]);
        ytext.setText("Y = " + sensorEvent.values[1]);
        world.setText("Z = " + sensorEvent.values[0]);

        if(b > 5){
            getWindow().getDecorView().setBackgroundColor(Color.BLUE);
        }
        else if(b < -5){
            getWindow().getDecorView().setBackgroundColor(Color.RED);
        }
        else{
            getWindow().getDecorView().setBackgroundColor(Color.YELLOW);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    protected void onResume(){
        super.onResume();
        sensorMan.registerListener(this,mysensor, SensorManager.SENSOR_DELAY_NORMAL);
    }
    protected void onPause(){
        super.onPause();
        sensorMan.unregisterListener(this);
    }*/

    @Override
    public  void hearShake(){
        getWindow().getDecorView().setBackgroundColor(Color.GREEN);
    }
}
