package com.example.tele_weather;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tele_weather.bean.LocationModel;

import java.util.List;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.ViewHolder>{
    private List<LocationModel> locationList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(String idLocation);
    }

    public LocationAdapter(List<LocationModel> locationList , OnItemClickListener listener) {
        this.locationList = locationList;
        this.listener = listener;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvLocationName, tvRegion, tvCountry, tvCoordinates;

        public ViewHolder(View itemView) {
            super(itemView);
            tvLocationName = itemView.findViewById(R.id.tvLocationName);
            tvRegion = itemView.findViewById(R.id.tvRegion);
            tvCountry = itemView.findViewById(R.id.tvCountry);
            tvCoordinates = itemView.findViewById(R.id.tvCoordinates);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_location, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LocationModel loc = locationList.get(position);
        holder.tvLocationName.setText(loc.getName());
        holder.tvRegion.setText(loc.getRegion());
        holder.tvCountry.setText(loc.getCountry());
        holder.tvCoordinates.setText("Lat: " + loc.getLat() + ", Lon: " + loc.getLon());

        holder.itemView.setOnClickListener(v -> {
            listener.onItemClick(loc.getId());
        });
    }

    @Override
    public int getItemCount() {
        return locationList.size();
    }
}
