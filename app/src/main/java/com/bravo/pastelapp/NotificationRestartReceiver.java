package com.bravo.pastelapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import androidx.core.app.NotificationManagerCompat;

public class NotificationRestartReceiver extends BroadcastReceiver {
    MainActivity mainActivity = new MainActivity();

    @Override
    public void onReceive(Context context, Intent intent) {
        // Relanzar la notificación al ser eliminada
        Toast.makeText(context, "Notificación eliminada", Toast.LENGTH_LONG).show();

        mainActivity.mostrarNotificacion(mainActivity.getPastel());
    }
}