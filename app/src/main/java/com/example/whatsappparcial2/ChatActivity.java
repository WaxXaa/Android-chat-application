package com.example.whatsappparcial2;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.ComponentActivity;

import com.example.whatsappparcial2.entidades.Chat;
import com.example.whatsappparcial2.entidades.Contacto;
import com.example.whatsappparcial2.entidades.Usuario;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends ComponentActivity {
    private ImageButton flecha;
    private TextView nombre;
    private TextView numero;
    private ImageView imagen;
    private ListView listaMensajes;
    private EditText mensajeTexto;
    private ImageButton btnEnviar;
    private ArrayAdapter<Chat> adapter;
    private List<Chat> mensajesList;
    private ChatManager chatManager;
    private int contactoSeleccionado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        this.inicializar();

        Intent i = getIntent();
        contactoSeleccionado = i.getIntExtra("contacto", 1);
        nombre.setText(i.getStringExtra("nombre"));
        numero.setText(i.getStringExtra("numTel"));
        String str = "id contacto" + contactoSeleccionado + " " + nombre.getText() + " " + numero.getText();
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
        chatManager = new ChatManager(this);


        mensajesList = this.obtenerChats();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mensajesList);
        listaMensajes.setAdapter(adapter);
        flecha.setOnClickListener(e-> {
            this.irAMain();
        });
        btnEnviar.setOnClickListener(e -> {

            //agarra el texto
                String mensaje = mensajeTexto.getText().toString();
                if (!mensaje.isEmpty()) {

                    int codUsuario = obtenerUsuario();

                    Chat chat = new Chat(codUsuario,mensaje, contactoSeleccionado);
                    this.guardarMensaje(chat);
                    adapter.notifyDataSetChanged();
                    mensajeTexto.setText("");
                }

        });
    }
    public void irAMain(){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
    public void inicializar() {
        flecha = (ImageButton) findViewById(R.id.flecha);
        imagen = (ImageView) findViewById(R.id.chat_img);
        int resourceId = this.getResources().getIdentifier("profile5", "drawable", this.getPackageName());
        imagen.setImageResource(resourceId);
        nombre = (TextView) findViewById(R.id.chat_nombre);
        numero = (TextView) findViewById(R.id.chat_numero);
        listaMensajes = findViewById(R.id.chats);
        mensajeTexto = findViewById(R.id.mensajeEscrito);
        btnEnviar = findViewById(R.id.btn_enviar);
    }
    public int obtenerUsuario() {
        SharedPreferences prefs = getSharedPreferences("userCod", Context.MODE_PRIVATE);
        return prefs.getInt("cod", 1);
    }
    public List<Chat> obtenerChats() {
        List<Chat> listaChats = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput("chat.txt")));
            String line;
            ; // Inicializa el contador de líneas

            while ((line = br.readLine()) != null) {

                String[] datosChat = line.split(";;");
                if(Integer.parseInt(datosChat[2]) == contactoSeleccionado) {
                    Chat chat = new Chat(Integer.parseInt(datosChat[0]), datosChat[1], Integer.parseInt(datosChat[2]));
                    listaChats.add(chat);
                }

            }

            br.close(); // Importante cerrar el BufferedReader después de leer

            // Ahora puedes usar fileContent como necesites
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listaChats;

    }
    public void guardarMensaje(Chat chat) {
        try {

            String chatNuevos = chat.getCodUsuario() + ";;" + chat.getChat() + ";;" + chat.getCodContacto() + "\n";
            OutputStreamWriter fout = new OutputStreamWriter(openFileOutput("chat.txt", Context.MODE_PRIVATE));
            fout.write(chatNuevos);
            fout.close();
            Toast.makeText(this, chatNuevos, Toast.LENGTH_SHORT).show();
        } catch (Exception ex) {
            Log.e("Ficheros", "Error al leer fichero de chats.txt desde memoria interna" + ex.getMessage());
        }
    }
   /* public void cargarNombreApellidoDeContacto() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput("contacto.txt")));
            String line;
            ; // Inicializa el contador de líneas

            while ((line = br.readLine()) != null) {

                String[] datosContacto = line.split(";;");
                if(Integer.parseInt(datosContacto[0]) == contactoSeleccionado){
                    String nom =datosContacto[1] + " " + datosContacto[2];
                nombre.setText(nom);
                numero.setText(datosContacto[3]);
                }

            }

            br.close(); // Importante cerrar el BufferedReader después de leer

            // Ahora puedes usar fileContent como necesites
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}


