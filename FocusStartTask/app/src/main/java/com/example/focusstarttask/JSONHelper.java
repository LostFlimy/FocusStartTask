package com.example.focusstarttask;

import com.example.focusstarttask.model.Currency;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

public class JSONHelper {
    public List<Currency> getCurrencys(String response) throws JSONException {
        LinkedList<Currency> result = new LinkedList<>();
        JSONObject responseCurrencys = new JSONObject(response);
        JSONObject valutes = responseCurrencys.getJSONObject("Valute");
        JSONArray valutesNames = valutes.names();
        if(valutesNames == null)  {
            throw new NullPointerException("JSON array is null");
        }
        for (int i = 0; i < valutesNames.length(); ++i) {
            JSONObject currency = valutes.getJSONObject(valutesNames.getString(i));
            Currency newCurrency = new Currency(
                currency.getString("CharCode"),
                currency.getInt("Nominal"),
                currency.getString("Name"),
                currency.getDouble("Value")
            );
            result.add(newCurrency);
        }
        return result;
    }
}
