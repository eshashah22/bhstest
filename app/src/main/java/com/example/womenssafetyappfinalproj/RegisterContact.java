package com.example.womenssafetyappfinalproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.nfc.Tag;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class RegisterContact extends AppCompatActivity {

    EditText name;
    EditText phone;
    Button addContact;
    ImageButton homebutton;
    ImageButton info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_contact);
        name = findViewById(R.id.id_name);
        phone = findViewById(R.id.id_phone);
        addContact = findViewById(R.id.id_addContact);
        homebutton = (ImageButton)findViewById(R.id.id_home);
        info = (ImageButton)findViewById(R.id.id_info);

        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(RegisterContact.this, MainActivity.class);
                startActivity(i);
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(RegisterContact.this, Info.class);
                startActivity(i);
            }

        });

        addContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!name.getText().toString().isEmpty() && !phone.getText().toString().isEmpty()) {

                    Intent intent = new Intent(Intent.ACTION_INSERT_OR_EDIT);
                    intent.setType(ContactsContract.RawContacts.CONTENT_ITEM_TYPE);
                    intent.putExtra(ContactsContract.Intents.Insert.NAME, name.getText().toString());
                    intent.putExtra(ContactsContract.Intents.Insert.PHONE, phone.getText().toString());

                    if(intent.resolveActivity(getPackageManager()) != null){
                        startActivity(intent);
                    }else{
                        Toast.makeText(RegisterContact.this, "There is no app that support this action", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(RegisterContact.this, "Please fill all the fields",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}