package com.mustafa.accelormeterngyroscope;

import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;


public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    double ax, ay, az;
    TextView tx;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tx = (TextView) findViewById(R.id.textView);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        final ToggleButton acc = (ToggleButton) findViewById(R.id.buttonAcc);
        final ToggleButton gro = (ToggleButton) findViewById(R.id.buttonGro);
        final ToggleButton Btnstep = (ToggleButton) findViewById(R.id.buttonStep);


        Btnstep.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {

                boolean on = Btnstep.isChecked();

                if (on) {
                    Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
                    if (countSensor != null) {
                        sensorManager.registerListener(MainActivity.this, countSensor, SensorManager.SENSOR_DELAY_UI);
                    } else {
                        Toast.makeText(MainActivity.this, "Count sensor not available!", Toast.LENGTH_LONG).show();
                    }

                }else{
                    stopListening();
                }


            }
        });


        acc.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {

                boolean on = acc.isChecked();

                if (on) {

                    Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
                    if (countSensor != null) {
                        sensorManager.registerListener(MainActivity.this, countSensor, SensorManager.SENSOR_DELAY_UI);
                    } else {
                        Toast.makeText(MainActivity.this, "Count sensor not available!", Toast.LENGTH_LONG).show();
                    }


                } else {
                    stopListening();
                }


            }
        });

        gro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean on = gro.isChecked();

                if (on) {

                    Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
                    if (countSensor != null) {
                        sensorManager.registerListener(MainActivity.this, countSensor, SensorManager.SENSOR_DELAY_UI);
                    } else {
                        Toast.makeText(MainActivity.this, "Count sensor not available!", Toast.LENGTH_LONG).show();
                    }


                } else {
                    stopListening();
                }

            }
        });

    }


    public void stopListening() {
        sensorManager.unregisterListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            ax = Math.round(sensorEvent.values[0]);
            ay = Math.round(sensorEvent.values[1]);
            az = Math.round(sensorEvent.values[2]);

            tx.setText("Accelerometer\nX: " + ax + "\n Y: " + ay + "\n Z: " + az);

        } else if (sensorEvent.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            ax = Math.round(sensorEvent.values[0]);
            ay = Math.round(sensorEvent.values[1]);
            az = Math.round(sensorEvent.values[2]);
            tx.setText("GYROSCOPE \nX: " + ax + "\n Y: " + ay + "\n Z: " + az);

        } else if (sensorEvent.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            tx.setText("" + sensorEvent.values[0]);

        }
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

}
