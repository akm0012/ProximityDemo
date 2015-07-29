package com.andrewkingmarshall.proximitydemo;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends Activity implements SensorEventListener{
    private SensorManager mSensorManager;
    private Sensor mProximity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get an instance of the sensor service, and use that to get an instance of a particular sensor.
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        float distance = sensorEvent.values[0];

        Log.e("sensor", "onSensorChanged: " + distance);
        Log.e("sensor", "Hello");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        // Do not think this applies to a proximity sensor
        Log.e("sensor", "onAccuracyChanged");
        Log.e("sensor", "Sensor: " + sensor.toString());
        Log.e("sensor", "i: " + i);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mProximity, SensorManager.SENSOR_DELAY_NORMAL);
        //Note: SensorManager.SENSOR_DELAY_NORMAL is the sampling delay
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
}