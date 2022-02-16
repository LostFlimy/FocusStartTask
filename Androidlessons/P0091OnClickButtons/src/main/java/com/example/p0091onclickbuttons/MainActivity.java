package com.example.p0091onclickbuttons;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.p0091onclickbuttons.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ActivityMainBinding binding;

    private static final String TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Log.d(TAG, "Поиск компонентов экрана по id");
        Log.d(TAG, "Поиск завершен успешно");

        View.OnClickListener btListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.closeBt :
                        Log.d(TAG, "Кнопка Close");
                        binding.textId.setText("Нажата кнопка Close");
                        Toast.makeText(
                                MainActivity.this,
                                "Нажата кнопка Close",
                                Toast.LENGTH_LONG
                        ).show();
                        break;
                    case R.id.okBt :
                        Log.d(TAG, "Кнопка OK");
                        binding.textId.setText("Нажата кнопка OK");
                        Toast.makeText(
                                MainActivity.this,
                                "Нажата кнопка OK",
                                Toast.LENGTH_LONG
                        ).show();
                        break;
                }
            }
        };

        binding.okBt.setOnClickListener(btListener);
        binding.closeBt.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.closeBt :
                Log.d(TAG, "Кнопка Close");
                binding.textId.setText("Нажата кнопка Close");
                Toast.makeText(
                        MainActivity.this,
                        "Нажата кнопка Close",
                        Toast.LENGTH_LONG
                ).show();
                break;
            case R.id.okBt :
                Log.d(TAG, "Кнопка Ok");
                binding.textId.setText("Нажата кнопка OK");
                Toast.makeText(
                        MainActivity.this,
                        "Нажата кнопка OK",
                        Toast.LENGTH_LONG
                ).show();
                break;
            case R.id.enterBt:
                Log.d(TAG, "Кнопка Enter");
                binding.textId.setText("Нажата кнопка Enter");
                Toast.makeText(
                        MainActivity.this,
                        "Нажата кнопка Enter",
                        Toast.LENGTH_LONG
                ).show();
                break;
        }
    }
}