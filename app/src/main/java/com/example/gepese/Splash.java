package com.example.gepese;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class Splash extends AppCompatActivity {

    private static final int SPLASH_DURATION = 3000; // 3 segundos en milisegundos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Crear un Handler para postergar la apertura de MainActivity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Crear una nueva intención para abrir MainActivity
                Intent mainIntent = new Intent(Splash.this, MainActivity.class);
                startActivity(mainIntent);
                finish(); // Cierra la actividad Splash
            }
        }, SPLASH_DURATION);
    }
}
