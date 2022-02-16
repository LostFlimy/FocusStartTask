package com.example.focusstarttask;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.focusstarttask.model.Currency;

import java.util.List;

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.ViewHolder> {
    private final LayoutInflater inflater;
    private final List<Currency> currencies;

    CurrencyAdapter(Context context, List<Currency> currencies) {
        this.inflater = LayoutInflater.from(context);
        this.currencies = currencies;
    }

    public void setNewCurrencies(@NonNull List<Currency> currencies) {
        this.currencies.clear();
        for (int i = 0; i < currencies.size(); ++i) {
            this.currencies.add(currencies.get(i));
        }
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_val, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Currency currency = currencies.get(position);
        holder.nameView.setText(
                String.format("%s(%s)",
                        currency.getName(),
                        currency.getCharCode()
                )
        );
        holder.valView.setText(
                String.format(
                        "%d %s = %f RUB",
                        currency.getNominal(),
                        currency.getCharCode(),
                        currency.getValue()
                )
        );
    }

    @Override
    public int getItemCount() {
        return (currencies != null ? currencies.size() : 0);
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        final TextView nameView, valView;
        ViewHolder(View view) {
            super(view);
            nameView = view.findViewById(R.id.valName);
            valView = view.findViewById(R.id.valValue);

        }
    }
}
