package com.example.womenssafetyappfinalproj;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import static android.telephony.SmsMessage.createFromPdu;


public class MainActivity extends AppCompatActivity implements SensorEventListener {

    Button registerc;
    Button select;
    Button callcops;
    Button c;
    private final int REQUEST_CODE = 99;
    String originaddy;
    String text;
    String reply;
    String phoneNumber;
    String name;
    String contactNumber;
    TextView s;
    ImageButton info;


    private SensorManager sensorManager;
    private Sensor accelerometerSensor;
    private float x;
    private float y;
    private float z;
    private float x2;
    private float y2;
    private float z2;
    private float shakeThreshold = 5.0f;
    private float xdiff;
    private float ydiff;
    private float zdiff;
    private boolean isAcceloremeterAvailable;
    private boolean itisNotFirstTime;
    private boolean shakeDetected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerc = findViewById(R.id.id_rc);
        select = findViewById(R.id.id_sel);
        s = findViewById(R.id.id_shake);
        info = (ImageButton)findViewById(R.id.id_info);
        c = findViewById(R.id.id_cop);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CODE);
        }

        TextView link = (TextView) findViewById(R.id.textView1);
        link.setMovementMethod(LinkMovementMethod.getInstance());

       // shakeDetected = false;

        registerc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterContact.class);
                startActivity(intent);
            }
        });

        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String polnumber = "912";
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+polnumber));
                startActivity(callIntent);
            }
        });

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(MainActivity.this, SelectContact.class);
                startActivity(i);


            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, Info.class);
                startActivity(i);
            }

        });

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);


        if(sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)!=null)
        {
            accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            isAcceloremeterAvailable = true;

        }else{
            //s.setText("NOT AVAILABLE");
            isAcceloremeterAvailable = false;
        }


    } //end oncreate


    @Override
    public void onSensorChanged(SensorEvent event) {

        x = event.values[0];
        y = event.values[1];
        z = event.values[2];

        if(itisNotFirstTime)
        {
            xdiff = Math.abs(x2-x);
           Log.d("TAG", "x:" + xdiff);
            ydiff = Math.abs(y2-y);
            zdiff = Math.abs(z2-z);

            if((xdiff>shakeThreshold && ydiff>shakeThreshold) || (xdiff>shakeThreshold&& zdiff>shakeThreshold)|| (ydiff>shakeThreshold&& zdiff>shakeThreshold))
            {
               // s.setText("SHAKE DETECTED");
                String polnumber = "912";
               Intent callIntent = new Intent(Intent.ACTION_CALL);
               callIntent.setData(Uri.parse("tel:"+polnumber));
               startActivity(callIntent);
               shakeDetected =true;


            }

        }
        x2 =x;
        y2 =y;
        z2 =z;
        itisNotFirstTime=true;

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    protected void onResume()
    {
        super.onResume();

        if(isAcceloremeterAvailable)
            sensorManager.registerListener(this, accelerometerSensor,sensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case REQUEST_CODE:
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CODE);
                }


                break;     
            default:
                break;
        }
    }
}



