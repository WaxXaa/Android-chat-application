package com.example.whatsappparcial2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.ComponentActivity;
import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CrearContactoActivity extends ComponentActivity {
    private EditText nombre;
    private EditText apellido;
    private EditText num;
    private Button confi;

    private Context context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_contacto);

        context = getApplicationContext();
        this.inicializarControles();
        confi.setOnClickListener(e -> {
            try {
                this.guardarContactos();

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public void inicializarControles() {
        nombre = findViewById(R.id.nombre);
        apellido = findViewById(R.id.apellido);
        num = findViewById(R.id.num);
        confi = findViewById(R.id.confi);

    }
    public void irAContactos(){
        Intent intent = new Intent(this, ContactosActivity.class);
        startActivity(intent);
    }
    public int obtenerProximoIdContacto() {
        int lineCount = 0;
        try {
            FileInputStream fis = openFileInput("contacto.txt");
            if (fis == null) {
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader br = new BufferedReader(isr);
                StringBuilder sb = new StringBuilder();
                String line;
                ; // Inicializa el contador de líneas

                while ((line = br.readLine()) != null) {
                    sb.append(line);
                    sb.append('\n');
                    lineCount++; // Incrementa el contador por cada línea leída
                }

                br.close(); // Importante cerrar el BufferedReader después de leer
                String fileContent = sb.toString();

                // Ahora puedes usar fileContent como necesites
                System.out.println("Número de líneas en el archivo: " + lineCount);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return lineCount;
    }
    public void guardarContactos() throws IOException {
        if (nombre.getText().toString().isEmpty()) {
            nombre.setError("El nombre es obligatorio");
        } else if (apellido.getText().toString().isEmpty()) {
            apellido.setError("El apellido es obligatorio");
        } else if (num.getText().toString().isEmpty()) {
            num.setError("El número de teléfono es obligatorio");
        } else {
            int longitud = obtenerProximoIdContacto();
            try {
                String nuevoContacto = (longitud + 1) + ";;" + nombre.getText().toString() + ";;" + apellido.getText().toString() + ";;" + num.getText().toString() + "\n";
                OutputStreamWriter osw = new OutputStreamWriter(openFileOutput("contacto.txt", Context.MODE_APPEND));
                osw.write(nuevoContacto);
                osw.close();
                /*FileOutputStream fl = openFileOutput("contacto.txt", Context.MODE_APPEND);
                OutputStreamWriter osw = new OutputStreamWriter(fl);
                PrintWriter pw = new PrintWriter(osw);
                pw.println(nuevoContacto);*/

            } catch (Exception ex) {
                Log.e("Ficheros", "Error al crear un nuevo contacto en contacto.txt", ex);
            }
            this.irAContactos();
        }
    }

    public EditText getNombre() {
        return nombre;
    }

    public void setNombre(EditText nombre) {
        this.nombre = nombre;
    }

    public EditText getApellido() {
        return apellido;
    }

    public void setApellido(EditText apellido) {
        this.apellido = apellido;
    }

    public EditText getNum() {
        return num;
    }

    public void setNum(EditText num) {
        this.num = num;
    }

    public Button getConfi() {
        return confi;
    }

    public void setConfi(Button confi) {
        this.confi = confi;
    }
}
