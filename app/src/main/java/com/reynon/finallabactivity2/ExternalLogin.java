package com.reynon.finallabactivity2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExternalLogin extends AppCompatActivity {
    EditText txtUsername, txtPassword;
    Button loginbtn, internalbtn;
    FileOutputStream fileOutputStream;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_externallogin);
        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        loginbtn = findViewById(R.id.loginbtn);
        internalbtn = findViewById(R.id.internalbtn);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = txtUsername.getText().toString()+"\n";
                String password = txtPassword.getText().toString();
                try{
                    ActivityCompat.requestPermissions(ExternalLogin.this, new String[]{
                            Manifest.permission.READ_EXTERNAL_STORAGE},23);
                    File folder = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
                    File myFile = new File(folder,"user_details");
                    fileOutputStream = new FileOutputStream(myFile);
                    fileOutputStream.write(username.getBytes());
                    fileOutputStream.write(password.getBytes());
                    fileOutputStream.close();
                    Toast.makeText(getApplicationContext(), "details saved in  " + myFile.getAbsolutePath(), Toast.LENGTH_LONG).show();
                    intent = new Intent(ExternalLogin.this,DetailsActivity.class);
                    startActivity(intent);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        internalbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ExternalLogin.this, MainActivity.class);
                startActivity(i);
            }
        });

    }
}