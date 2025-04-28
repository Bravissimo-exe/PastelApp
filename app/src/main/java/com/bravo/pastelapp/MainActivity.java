package com.bravo.pastelapp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import androidx.core.app.NotificationCompat;

public class MainActivity extends AppCompatActivity {

    private Button buttonPastel;
    private static final String CANAL_ID = "pasel_app";
    private static final int NOTIFICACIONES_ID = 1;
    private NotificationManager notificationManager;

    private String pastelText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        TextView contadorTexto = findViewById(R.id.contador_caracteres);

        EditText pastelEdit = findViewById(R.id.pastel_text);

        buttonPastel = findViewById(R.id.pastelear_button);

        buttonPastel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pastelText = pastelEdit.getText().toString();

                notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                crearNotificacion();
                mostrarNotificacion(pastelText);

            }
        });

        pastelEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int currentLength = s.length();
                contadorTexto.setText(currentLength + "/100");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void crearNotificacion() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            CharSequence nombre_canal = "mi canal";
            String descripcion_canal = "canal para pastelear";
            int importancia = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel canal = new NotificationChannel(CANAL_ID, nombre_canal, importancia);
            canal.setDescription(descripcion_canal);
            notificationManager.createNotificationChannel(canal);
        }
    }

    private void mostrarNotificacion(String PastelText) {
        Notification.Builder builder = null;
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            builder = new Notification.Builder(this,CANAL_ID)
                    .setSmallIcon(R.drawable.pastel_app_icon)
                    .setContentTitle("Pastel")
                    .setContentText(PastelText)
                    .setPriority(Notification.PRIORITY_DEFAULT)
                    .setAutoCancel(true);
        }
        Notification notification;
        notification = builder.build();
        notificationManager.notify(NOTIFICACIONES_ID, notification);
    }

}