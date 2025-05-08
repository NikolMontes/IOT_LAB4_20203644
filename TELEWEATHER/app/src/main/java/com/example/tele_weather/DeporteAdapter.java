package com.example.tele_weather;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tele_weather.bean.SportModel;

import java.util.List;

public class DeporteAdapter extends RecyclerView.Adapter<DeporteAdapter.ViewHolder>{
    private final List<SportModel.Match> footballMatches;

    public DeporteAdapter(List<SportModel.Match> footballMatches) {
        this.footballMatches = footballMatches;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvMatch, tvTournament, tvStadium, tvStart, tvCountry;

        public ViewHolder(View itemView) {
            super(itemView);
            tvMatch = itemView.findViewById(R.id.tvMatch);
            tvTournament = itemView.findViewById(R.id.tvTournament);
            tvStadium = itemView.findViewById(R.id.tvStadium);
            tvStart = itemView.findViewById(R.id.tvStart);
            tvCountry = itemView.findViewById(R.id.tvCountry);
        }
    }
    @NonNull
    @Override
    public DeporteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_deportes, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeporteAdapter.ViewHolder holder, int position) {
        SportModel.Match match = footballMatches.get(position);
        holder.tvMatch.setText(match.getMatch());
        holder.tvTournament.setText(match.getTournament());
        holder.tvStadium.setText(match.getStadium());
        holder.tvStart.setText(match.getStart());
        holder.tvCountry.setText(match.getCountry());
    }

    @Override
    public int getItemCount() {
        return footballMatches.size();
    }
}
