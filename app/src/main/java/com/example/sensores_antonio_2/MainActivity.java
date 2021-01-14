package com.example.sensores_antonio_2;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private TextView t_sensor, t_ejex, t_ejey, t_ejez;
    private SensorManager sensorManager;
    private Sensor acelerometerSensor;
    private Sensor proximitySensor1;
    private Sensor lightSensor1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t_sensor = (TextView) findViewById(R.id.tv_sensor);
        t_ejex = (TextView) findViewById(R.id.tv_EjeX);
        t_ejey = (TextView) findViewById(R.id.tv_EjeY);
        t_ejez = (TextView) findViewById(R.id.tv_EjeZ);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        List<Sensor> listaSensores;

        listaSensores = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
        if (!listaSensores.isEmpty()) {

                    acelerometerSensor = listaSensores.get(0);
            sensorManager.registerListener(this, acelerometerSensor,
                    SensorManager.SENSOR_DELAY_UI);
        }

        listaSensores = sensorManager.getSensorList(Sensor.TYPE_PROXIMITY);
        if (!listaSensores.isEmpty()) {
            proximitySensor1 = listaSensores.get(0);
            sensorManager.registerListener(this, proximitySensor1,
                    SensorManager.SENSOR_DELAY_UI);
        }

        listaSensores = sensorManager.getSensorList(Sensor.TYPE_LIGHT);
        if (!listaSensores.isEmpty()) {
            lightSensor1 = listaSensores.get(0);

            sensorManager.registerListener(this, lightSensor1,
                    SensorManager.SENSOR_DELAY_UI);
        }

    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        synchronized (this) {
            switch (event.sensor.getType()) {
                case Sensor.TYPE_ACCELEROMETER:
                    this.t_sensor.setText("Acelerómetro");
                    this.t_ejex.setText(event.values[0] + "");
                    this.t_ejey.setText(event.values[1] + "");
                    this.t_ejez.setText(event.values[2] + "");
                    break;
                case Sensor.TYPE_PROXIMITY:
                    this.t_sensor.setText("Proximidad");
                    this.t_ejex.setText(event.values[0] + "");
                    this.t_ejey.setText("");
                    this.t_ejez.setText("");
                    break;
                case Sensor.TYPE_LIGHT:
                    this.t_sensor.setText("Luz");
                    this.t_ejex.setText(event.values[0] + "");
                    this.t_ejey.setText("");
                    this.t_ejez.setText("");
                    break;
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
// TODO Auto-generated method stub
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, acelerometerSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, proximitySensor1,
                SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, lightSensor1,
                SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override

    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}