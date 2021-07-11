package com.example.womenssafetyappfinalproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Info extends AppCompatActivity {

    TextView inf;
    ImageButton homebutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        inf = findViewById(R.id.id_inf);
        homebutton = (ImageButton)findViewById(R.id.id_home);

        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Info.this, MainActivity.class);
                startActivity(i);
            }
        });

        inf.setText("Welcome to the Women's Safety App! This app helps to keep women safe. The app allows you to register as many contacts as you want. If you are in danger, you are able to select a contact and it will send an automatic text of your current location. Additionally, if you are in danger you can shake your phone. This will automatically call the police. There is also a link provided on the home page with additional information on what to do if you find yourself in danger. Stay Safe! ");
    }
}