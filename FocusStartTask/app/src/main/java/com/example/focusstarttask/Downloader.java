package com.example.focusstarttask;

import android.content.Context;
import android.widget.Toast;

import com.example.focusstarttask.databinding.ActivityMainBinding;
import com.example.focusstarttask.model.Currency;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class Downloader {
    private final Context context;
    private final JSONHelper jsonHelper = new JSONHelper();
    private final ActivityMainBinding binding;

    public Downloader(Context context, ActivityMainBinding binding) {
        this.context = context;
        this.binding = binding;
    }

    public void updateContent() {
        try {
            String content = getContent("https://www.cbr-xml-daily.ru/daily_json.js");
            List<Currency> currencies = jsonHelper.getCurrencys(content);
            binding.title.post(new Runnable() {
                @Override
                public void run() {
                    binding.title.setText("Курсы валют:");
                }
            });
            binding.recyclerView.post(new Runnable() {
                @Override
                public void run() {
                    CurrencyAdapter adapter = (CurrencyAdapter) binding.recyclerView.getAdapter();
                    adapter.setNewCurrencies(currencies);
                }
            });
        } catch (IOException ex) {
            Toast.makeText(context, "Ошибка", Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            Toast.makeText(context, "Ошибка", Toast.LENGTH_SHORT).show();
        }
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
