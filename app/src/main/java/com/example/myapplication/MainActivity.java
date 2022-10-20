package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SensorManager mSensorManager;
    private Sensor sensorProximidade;
    private Sensor sensorLuminosidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        if (mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY) != null) {
            sensorProximidade = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        }
        if (mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT) != null) {
            sensorLuminosidade = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        }

        mSensorManager.registerListener(new SensorProximity(), sensorProximidade, SensorManager.SENSOR_DELAY_UI);
        mSensorManager.registerListener(new SensorTemper(), sensorLuminosidade, SensorManager.SENSOR_DELAY_UI);


        //ImageView imgView = (ImageView)findViewById(R.id.on);

    }

    class SensorProximity implements SensorEventListener {
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

        public void onSensorChanged(SensorEvent event) {

            float x = event.values[0];

            ImageView micOff = (ImageView)findViewById(R.id.off);
            ImageView micOn = (ImageView)findViewById(R.id.off);

            /*TextView a = (TextView)findViewById(R.id.textView4);
            String s=String.valueOf(x);
            a.setText(s);*/

           if(x > 0){//o sensor no meu celular só ficava entre 5 ou 0 por isso n fiz o valor de 6 como falado na tarefa
                micOff.setVisibility(View.VISIBLE);
                micOn.setVisibility(View.INVISIBLE);
            } else {
                micOff.setVisibility(View.INVISIBLE);
                micOn.setVisibility(View.VISIBLE);
            }
        }
    }

    class SensorTemper implements SensorEventListener {
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

        public void onSensorChanged(SensorEvent event) {
            float x = event.values[0];

            ImageView micOff = (ImageView)findViewById(R.id.off);
            ImageView micOn = (ImageView)findViewById(R.id.off);

            /*TextView a = (TextView)findViewById(R.id.textView3);
            String s=String.valueOf(value);
            a.setText(s);*/

            if(x > 10){//o sensor no meu celular só ficava entre 0 e 10 no escuro e maior que isso no claro, entao adaptei pra isso
                micOff.setBackgroundColor(Color.WHITE);
                micOff.setColorFilter(Color.argb(0, 0, 0, 0));
                micOn.setBackgroundColor(Color.WHITE);
                micOn.setColorFilter(Color.argb(0, 0, 0, 0));
            } else {
                micOff.setBackgroundColor(Color.BLACK);
                micOff.setColorFilter(Color.argb(255, 255, 255, 255));
                micOn.setBackgroundColor(Color.BLACK);
                micOn.setColorFilter(Color.argb(255, 255, 255, 255));
            }
        }
    }
}

