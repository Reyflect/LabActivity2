package com.reynon.finallabactivity2;

import androidx.annotation.StringDef;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class DetailsActivity extends AppCompatActivity {

    FileInputStream fileInputStream;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        TextView result = findViewById(R.id.credentialstxt);
        Button back = findViewById(R.id.backBtn);
        try{

            ActivityCompat.requestPermissions(DetailsActivity.this, new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE},23);
            File folder = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
            File myFile = new File(folder,"user_details");
            fileInputStream = new FileInputStream(myFile);
            StringBuffer stringBuffer = new StringBuffer();
            int i;
            while((i=fileInputStream.read())!=-1){
                stringBuffer.append((char)i);
            }
            fileInputStream.close();
            String details[] = stringBuffer.toString().split("\n");
            result.setText("Name: "+ details[0]+"\nPassword: "+details[1]);
        } catch (IOException e) {
            e.printStackTrace();
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DetailsActivity.this, ExternalLogin.class);
                startActivity(i);
            }
        });
    }
}
