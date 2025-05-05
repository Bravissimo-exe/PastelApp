package com.bravo.pastelapp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

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

        int[] seekValores = {0, 5, 10, 15, 20, 30};

        EditText pastelEdit = findViewById(R.id.pastel_text);

        TextView minutosText = findViewById(R.id.minutos);

        SeekBar minutos = findViewById(R.id.barra_minutos);

        buttonPastel = findViewById(R.id.pastelear_button);

        minutosText.setText(seekValores[minutos.getProgress()] + " minutos");

        minutos.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                int valoresMinutos = seekValores[progress];
                minutosText.setText(valoresMinutos + " minutos");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        buttonPastel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pastelText = pastelEdit.getText().toString();

                notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                crearNotificacion();
                mostrarNotificacion(pastelText);

            }
        });
    }

    public String getPastel(){
        return pastelText;
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

    void mostrarNotificacion(String pastelText) {

        // Construir notificación
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CANAL_ID)
                .setSmallIcon(R.drawable.pastel_app_icon)
                .setContentTitle("Pastel")
                .setContentText(pastelText)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        notificationManager.notify(NOTIFICACIONES_ID, builder.build());
    }

}