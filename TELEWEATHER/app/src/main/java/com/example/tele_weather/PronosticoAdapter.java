package com.example.tele_weather;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tele_weather.bean.PronosticoModel;

import java.util.List;

public class PronosticoAdapter extends RecyclerView.Adapter<PronosticoAdapter.ViewHolder> {

    private final List<PronosticoModel.ForecastDay> forecastList;

    public PronosticoAdapter(List<PronosticoModel.ForecastDay> forecastList) {
        this.forecastList = forecastList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvFecha, tvMaxMin, tvCondicion;

        public ViewHolder(View itemView) {
            super(itemView);
            tvFecha = itemView.findViewById(R.id.tvFecha);
            tvMaxMin = itemView.findViewById(R.id.tvMaxMin);
            tvCondicion = itemView.findViewById(R.id.tvCondicion);
        }
    }

    @NonNull
    @Override
    public PronosticoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pronosticos, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PronosticoAdapter.ViewHolder holder, int position) {
        PronosticoModel.ForecastDay day = forecastList.get(position);

        holder.tvFecha.setText("Fecha: " + day.getDate());

        double max = day.getDay().getMaxtemp_c();
        double min = day.getDay().getMintemp_c();
        holder.tvMaxMin.setText("Max: " + max + "°C / Min: " + min + "°C");

        String condition = day.getDay().getCondition().getText();
        holder.tvCondicion.setText("Condición: " + condition);
    }

    @Override
    public int getItemCount() {
        return forecastList.size();
    }

}
