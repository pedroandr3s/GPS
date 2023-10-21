package com.example.gepese;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText latitud1, latitud2, latitud3;
    private TextView datoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        datoTextView = findViewById(R.id.datos);
        Button btnAgregar = findViewById(R.id.btnAgregar);
        latitud1 = findViewById(R.id.lyl1);
        latitud2 = findViewById(R.id.lyl2);
        latitud3 = findViewById(R.id.lyl3);

        Button btnLimpiar = findViewById(R.id.btnlimpiar);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String datoIngresado = latitud1.getText().toString();
                String dato1 = latitud1.getText().toString();
                String dato2 = latitud2.getText().toString();
                String dato3 = latitud3.getText().toString();
                String[] coordenadas1 = dato1.split(",");
                String[] coordenadas2 = dato2.split(",");
                String[] coordenadas3 = dato3.split(",");
                datoTextView.setText(datoIngresado);

                Intent intent = null;
                if (sonCoordenadasValidas(dato1) && sonCoordenadasValidas(dato2) && sonCoordenadasValidas(dato3)) {
                    intent = new Intent(MainActivity.this, MapsActivity.class);
                    intent.putExtra("latitud1", coordenadas1[0]);
                    intent.putExtra("longitud1", coordenadas1[1]);
                    intent.putExtra("latitud2", coordenadas2[0]);
                    intent.putExtra("longitud2", coordenadas2[1]);
                    intent.putExtra("latitud3", coordenadas3[0]);
                    intent.putExtra("longitud3", coordenadas3[1]);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Ingrese coordenadas válidas", Toast.LENGTH_SHORT).show();
                }
            }
        });


        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                latitud1.setText("");
                latitud2.setText("");
                latitud3.setText("");

                latitud1.setHint("latitud y longitud");
                latitud2.setHint("latitud y longitud");
                latitud3.setHint("latitud y longitud");
            }
        });
    }

    private boolean sonCoordenadasValidas(String coordenadas) {
        String[] partes = coordenadas.split(",");
        if (partes.length == 2) {
            try {
                Double.parseDouble(partes[0]);
                Double.parseDouble(partes[1]);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return false;
    }
}
