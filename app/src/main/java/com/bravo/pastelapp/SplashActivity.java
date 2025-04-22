package com.bravo.pastelapp;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);

        ImageView imgTop = findViewById(R.id.imageTop);
        ImageView imgBottom = findViewById(R.id.imageBottom);

        int screenHeight = getResources().getDisplayMetrics().heightPixels;
        float dpToPx = getResources().getDisplayMetrics().density;
        int imageHeightPx = (int) (500 * dpToPx); // 500dp en píxeles
        float finalPosTop = (screenHeight / 2f) - (imageHeightPx * 0.7f);
        float finalPosBottom = -(screenHeight / 2f) + (imageHeightPx * 0.3f);

        //animacion para la imagen de arriba

        ObjectAnimator animTop = ObjectAnimator.ofFloat(imgTop,"translationY", 0f, finalPosTop);
        animTop.setDuration(1500);

        //animacion para la imagen de abajo
        ObjectAnimator animBottom = ObjectAnimator.ofFloat(imgBottom,"translationY", 0f, finalPosBottom);
        animTop.setDuration(1500);

        //ejecutar las animaciones en paralelo
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animTop, animBottom);
        animatorSet.start();

        //redirigir al main Activity luego de la animacion
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();}, 2500);

    }
}