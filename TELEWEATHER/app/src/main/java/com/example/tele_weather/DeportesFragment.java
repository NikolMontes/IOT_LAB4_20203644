package com.example.tele_weather;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.tele_weather.bean.SportModel;
import com.example.tele_weather.databinding.FragmentDeportesBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeportesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private FragmentDeportesBinding binding;
    private DeporteAdapter adapter;
    private List<SportModel.Match> footballMatches = new ArrayList<>();

    // TODO: Rename and change types of parameters

    public DeportesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDeportesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Configurar RecyclerView
        adapter = new DeporteAdapter(footballMatches);
        binding.rvDeportes.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvDeportes.setAdapter(adapter);

        // Botón buscar
        binding.btnSearch.setOnClickListener(v -> {
            String lugar = binding.etIdLocation.getText().toString().trim();
            if (TextUtils.isEmpty(lugar)) {
                Toast.makeText(getContext(), "Ingresa un lugar", Toast.LENGTH_SHORT).show();
                return;
            }

            buscarPartidosDeFootball(lugar);
        });
    }

    private void buscarPartidosDeFootball(String location) {
        WeatherApiService apiService = RetrofitInstance.getRetrofit().create(WeatherApiService.class);
        Call<SportModel> call = apiService.getFootballMatches(location);

        Log.d("DEPORTES", "Entró al onResponse");
        call.enqueue(new Callback<SportModel>() {
            @Override
            public void onResponse(Call<SportModel> call, Response<SportModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("DEPORTES", "Respuesta exitosa");
                    footballMatches.clear();
                    List<SportModel.Match> partidos = response.body()
                            .getFootball();

                    if (partidos != null && !partidos.isEmpty()) {
                        Log.d("DEPORTES", "Se recibieron " + partidos.size() + " partidos");

                        footballMatches.addAll(partidos);
                        adapter.notifyDataSetChanged();

                        for (SportModel.Match m : partidos) {
                            Log.d("PARTIDO", m.getMatch() + " - " + m.getStart());
                        }
                    } else {
                        Log.d("DEPORTES", "No hay partidos de fútbol en la respuesta.");
                    }

                } else {
                    Log.e("DEPORTES", "Respuesta fallida o nula: " + response.code());
                    Toast.makeText(getContext(), "No se encontraron partidos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SportModel> call, Throwable t) {
                Toast.makeText(getContext(), "Error de red", Toast.LENGTH_SHORT).show();
                Log.e("DEPORTES", "Fallo en la red: " + t.getMessage(), t);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}