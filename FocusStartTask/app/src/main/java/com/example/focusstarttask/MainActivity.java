package com.example.focusstarttask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.focusstarttask.databinding.ActivityMainBinding;
import com.example.focusstarttask.model.Currency;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {
    public static List<Currency> currencies = new LinkedList<>();
    ActivityMainBinding binding;
    private final long DELAY = 180000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        TextView title = findViewById(R.id.title);
        Button refreshButton = findViewById(R.id.refreshButton);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        CurrencyAdapter adapter = new CurrencyAdapter(this, currencies);
        recyclerView.setAdapter(adapter);

        Downloader downloader = new Downloader(this, binding);

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.setNewCurrencies(new LinkedList<>());
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        downloader.updateContent();
                    }
                }).start();
            }
        });

        title.setText("Загрузка...");
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    downloader.updateContent();
                    try {
                        Thread.sleep(DELAY);
                    } catch (InterruptedException e) {
                        Toast.makeText(MainActivity.this, "Ошибка загрузки", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }).start();
    }
}