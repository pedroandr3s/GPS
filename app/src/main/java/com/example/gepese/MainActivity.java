package com.example.gepese;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText latitud1,latitud2,latitud3,longitud1,longitud2,longitud3;
    private TextView datoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        datoTextView = findViewById(R.id.dato);
        Button btnAgregar = findViewById(R.id.btnAgregar);
        latitud1 = findViewById(R.id.latitud1);
        latitud2 = findViewById(R.id.latitud2);
        latitud3 = findViewById(R.id.latitud3);
        longitud1 = findViewById(R.id.longitud1);
        longitud2 = findViewById(R.id.longitud2);
        longitud3 = findViewById(R.id.longitud3);
        Button btnLimpiar = findViewById(R.id.btnlimpiar);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String datoIngresado = longitud1.getText().toString();

                // Establecer el texto en el TextView
                datoTextView.setText(datoIngresado);
            }
        });
        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Borra el texto y restaura el hint
                latitud1.setText("");
                latitud2.setText("");
                latitud3.setText("");
                longitud1.setText("");
                longitud2.setText("");
                longitud3.setText("");
                latitud1.setHint("latitud");
                latitud2.setHint("latitud");
                latitud3.setHint("latitud");
                longitud1.setHint("longitud");
                longitud2.setHint("longitud");
                longitud3.setHint("longitud");
            }
        });

    }
}
