package com.example.focusstarttask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

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
    private final JSONHelper jsonHelper = new JSONHelper();
    private final long DELAY = 180000;//задержка обновления списка валют в мс
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView title = findViewById(R.id.title);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        CurrencyAdapter adapter = new CurrencyAdapter(this, currencies);
        recyclerView.setAdapter(adapter);

        title.setText("Загрузка...");
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        while(true) {
                            try {
                                String content = getContent("https://www.cbr-xml-daily.ru/daily_json.js");
                                currencies = jsonHelper.getCurrencys(content);
                                title.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        title.setText("Курсы валют:");
                                    }
                                });
                                recyclerView.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        adapter.setNewCurrencies(currencies);
                                    }
                                });
                            } catch (IOException ex) {
                                Toast.makeText(getApplicationContext(), "Ошибка", Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                Toast.makeText(getApplicationContext(), "Ошибка", Toast.LENGTH_SHORT).show();
                            }
                            try {
                                Thread.sleep(DELAY);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        ).start();
    }

    private String getContent(String path) throws IOException {
        BufferedReader reader=null;
        InputStream stream = null;
        HttpsURLConnection connection = null;
        try {
            URL url=new URL(path);
            connection =(HttpsURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(10000);
            connection.connect();
            stream = connection.getInputStream();
            reader= new BufferedReader(new InputStreamReader(stream));
            StringBuilder buf=new StringBuilder();
            String line;
            while ((line=reader.readLine()) != null) {
                buf.append(line).append("\n");
            }
            return(buf.toString());
        }
        finally {
            if (reader != null) {
                reader.close();
            }
            if (stream != null) {
                stream.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}