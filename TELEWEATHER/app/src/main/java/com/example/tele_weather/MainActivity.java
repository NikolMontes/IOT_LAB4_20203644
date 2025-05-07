package com.example.tele_weather;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.net.ConnectivityManager;

import com.example.tele_weather.databinding.ActivityMainBinding;
import com.example.tele_weather.databinding.DialogoConfiguracionBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isInternetAvailable()){
                    Intent intent= new Intent(MainActivity.this, AppActivity.class);
                    startActivity(intent);
                }else{
                    showInternetDialog();
                }
            }
        } );
    }

    /*verifica la conexión a internet */
    private boolean isInternetAvailable(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean isInternet =false;
        if(connectivityManager != null){
            NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            if(capabilities != null){
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)){
                    Log.i("msg-Internet", "NetworkCapabilities. Transport_celular");
                    isInternet=true;
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Log.i("msg-Internet", "NetworkCapabilities. Transport_ethernet||isInternetAvailable");
                    isInternet=true;
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i("msg-Internet", "isInternetAvailable: NetworkCapabilities. Transport_wifi");
                    isInternet=true;
                }
            }
        }

        /*boolean isWifiConn = false;
        boolean isMobileConn = false;
        boolean isEthernetConn = false;
        for (Network network : connectivityManager.getAllNetworks()) {
            NetworkInfo networkInfo = connectivityManager.getNetworkInfo(network);
            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                isWifiConn = networkInfo.isConnected();

                if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                    isMobileConn = networkInfo.isConnected();

                    if (networkInfo.getType() == ConnectivityManager.TYPE_ETHERNET) {
                        isEthernetConn = networkInfo.isConnected();

                        Log.i("msg", "Wifi connected: " + isWifiConn);
                        Log.d("msg", "Mobile connected: " + isMobileConn);
                        Log.i("msg", "Ethernet connected: " + isEthernetConn);
                    }
                }
            }
        }*/
        return isInternet;
    }
    /*muestra el dialogo (el dialogo_configuración.xml)*/
    private void showInternetDialog() {
        final Dialog dialogo = new Dialog(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        DialogoConfiguracionBinding dialogBinding = DialogoConfiguracionBinding.inflate(LayoutInflater.from(this));
        builder.setView(dialogBinding.getRoot());
        builder.setCancelable(false);

        AlertDialog dialog = builder.create();

        dialogBinding.btnConfiguracion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirige a la configuración del dispositivo
                /*Intent intent = new Intent(Settings.ACTION_SETTINGS);
                startActivity(intent);
                dialog.dismiss();*/
                Intent intent =new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
                startActivity(intent);
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Liberar el binding cuando la actividad se destruye
        binding = null;
    }
}