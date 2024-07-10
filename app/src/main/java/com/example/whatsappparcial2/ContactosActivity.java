package com.example.whatsappparcial2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import androidx.activity.ComponentActivity;

import com.example.whatsappparcial2.adaptador.ContactAdapter;
import com.example.whatsappparcial2.entidades.Contacto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ContactosActivity extends ComponentActivity {
    ListView listv_contactos;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactos);
        context = getApplicationContext();
        InicializarControles();

    }

    public void irAMain(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    private void InicializarControles() {
        listv_contactos = findViewById(R.id.listview_mensaje);
        LlenarListViews();
    }

    private void LlenarListViews() {
        List<Contacto> contactos = obtenerContacto();
        ContactAdapter cuentaAdapter = new ContactAdapter(this, contactos); // Utiliza "this" en lugar de "listv_contactos.getContext()"
        listv_contactos.setAdapter(cuentaAdapter);
    }
    public List<Contacto> obtenerContacto() {
        List<Contacto> listaContactos = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput("contacto.txt")));
            String line;
            ; // Inicializa el contador de líneas

            while ((line = br.readLine()) != null) {

                String[] datosContacto = line.split(";;");
                Contacto contacto = new Contacto(Integer.parseInt(datosContacto[0]), datosContacto[1], datosContacto[2], datosContacto[3], "1");
                listaContactos.add(contacto);
            }

            br.close(); // Importante cerrar el BufferedReader después de leer

            // Ahora puedes usar fileContent como necesites
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listaContactos;

    }

}
