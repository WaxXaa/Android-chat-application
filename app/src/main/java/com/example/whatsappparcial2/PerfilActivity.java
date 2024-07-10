package com.example.whatsappparcial2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.ComponentActivity;
import androidx.annotation.Nullable;

import com.example.whatsappparcial2.entidades.Contacto;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PerfilActivity extends ComponentActivity {
    private ImageButton atras;
    private EditText editarNombre;
    private EditText editarApellido;
    private TextView nombre;
    private TextView apellido;
    private TextView numero;
    private Button editar;
    private int userNum;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        this.inicializar();
        editar.setOnClickListener(e -> {
        this.editarUsuario();
        this.irAMain();
        });
        atras.setOnClickListener(e -> {
            this.irAMain();
        });
    }
    public void obtenerUsuarioDeSharePreferenceYCargarloEnElTextView() {
        int cod = obtenerUsuario();
        SharedPreferences prefs = getSharedPreferences("user" + cod, Context.MODE_PRIVATE);
        String nombre;
        String apellido;
        if(cod == 1) {
            nombre = prefs.getString("nombre", "Checo");
            apellido = prefs.getString("apellido", "Perez");
        } else {
            nombre = prefs.getString("nombre", "Pedro");
            apellido = prefs.getString("apellido", "Altamiranda");
        }
        String numero = prefs.getString("numero", "65364534");
        this.nombre.setText(nombre);
        this.apellido.setText(apellido);
        this.numero.setText(numero);
    }
    public int obtenerUsuario() {
        SharedPreferences prefs = getSharedPreferences("userCod", Context.MODE_PRIVATE);
        return prefs.getInt("cod", 1);
    }
public void editarUsuario() {
        userNum = obtenerUsuario();
        SharedPreferences prefs = getSharedPreferences("user" + userNum, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("nombre",editarNombre.getText().toString());
        editor.putString("apellido",editarApellido.getText().toString());
        editor.apply();
    }

    public void irAMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void inicializar() {
        atras = (ImageButton) findViewById(R.id.flecha);
        nombre = (TextView) findViewById(R.id.nombre);
        apellido = (TextView) findViewById(R.id.apellido);
        numero = (TextView) findViewById(R.id.numeroTelefono);
        editarNombre = (EditText) findViewById(R.id.editarNombre);
        editarApellido = (EditText) findViewById(R.id.editarApellido);
        editar = (Button) findViewById(R.id.editar);
        this.obtenerUsuarioDeSharePreferenceYCargarloEnElTextView();
    }
}
