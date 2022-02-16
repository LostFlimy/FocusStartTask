package com.example.p0081viewbyid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView myTextView = (TextView) findViewById(R.id.myText);
        myTextView.setText("New Text!!!");

        Button myButton = findViewById(R.id.myButton);
        myButton.setText("New Button!!!");
        myButton.setEnabled(false);

        CheckBox myCheck = findViewById(R.id.checkBoxId);
        myCheck.setChecked(true);
    }
}