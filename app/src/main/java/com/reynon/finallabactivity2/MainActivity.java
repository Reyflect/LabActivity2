package com.reynon.finallabactivity2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    EditText input;
    Button savebtn, readbtn, externalbtn;
    TextView displaytxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = findViewById(R.id.inputtxt);
        savebtn = findViewById(R.id.savebtn);
        readbtn = findViewById(R.id.readbtn);
        displaytxt = findViewById(R.id.displaytxt);
        externalbtn = findViewById(R.id.externalbtn);

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeFile();
            }
        });

        readbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readFile();
            }
        });

        externalbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ExternalLogin.class);
                startActivity(i);
            }
        });
    }

    public void writeFile(){
        String textToSave = input.getText().toString();
        try{
            FileOutputStream fileOutputStream = openFileOutput("SampleFile.txt", MODE_PRIVATE);
            fileOutputStream.write(textToSave.getBytes());
            fileOutputStream.close();

            Toast.makeText(getApplicationContext(),"Text saved", Toast.LENGTH_SHORT).show();
            input.setText("");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void readFile(){
        try{
            FileInputStream fileInputStream = openFileInput("SampleFile.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();

            String lines;

            while ((lines= bufferedReader.readLine())!=null){
                    stringBuffer.append(lines+"\n");
            }
            displaytxt.setText(stringBuffer.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}