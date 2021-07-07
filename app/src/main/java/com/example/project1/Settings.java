package com.example.project1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Settings extends AppCompatActivity {

    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        btn = (Button)findViewById(R.id.buttonprofile);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openprofile();
            }
        });

        btn = (Button)findViewById(R.id.buttonau);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openaboutus();
            }
        });

        btn = (Button)findViewById(R.id.buttoncu);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opencontactus();
            }
        });

        btn = (Button)findViewById(R.id.buttonec);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openemergencycontact();
            }
        });
    }

    private void openprofile() {
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setMessage("Need to create profile activity and then using intent need to link it here");
        dialog.setTitle("Profile");

        AlertDialog alertDialog=dialog.create();
        alertDialog.show();
    }
    private void openaboutus() {
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setMessage("A short details abt developers");
        dialog.setTitle("About Us");

        AlertDialog alertDialog=dialog.create();
        alertDialog.show();
    }
    private void opencontactus() {
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setMessage("we can provide multiple social media links here");
        dialog.setTitle("Contact us");

        AlertDialog alertDialog=dialog.create();
        alertDialog.show();
    }

    private void openemergencycontact() {
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setMessage("Contact numbers of Police , hospital and other services");
        dialog.setTitle("Emergency Contact");

        AlertDialog alertDialog=dialog.create();
        alertDialog.show();
    }
}