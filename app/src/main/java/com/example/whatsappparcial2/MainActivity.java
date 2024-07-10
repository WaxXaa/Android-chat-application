package com.example.whatsappparcial2;

import androidx.activity.ComponentActivity;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.whatsappparcial2.adaptador.ContactAdapter;
import com.example.whatsappparcial2.entidades.Contacto;
import com.example.whatsappparcial2.entidades.Usuario;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ComponentActivity {

    ListView listv_contactos;
    ImageButton mas;
    Usuario usuario;
    ImageButton trespuntosmenu;

    TextView user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.InicializarControles();

        mas.setOnClickListener(e ->{
            irAContactos();
        });

        Intent intent = getIntent();
        if(intent == null) {
            usuario.setEstado(1);
        }
        registerForContextMenu(trespuntosmenu);
        //para detectar click en la lista
        listv_contactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Contacto contactoSeleccionado = obtenerContactoMensaje().get(i);
                String str = "nombre del contacto sel " + contactoSeleccionado.getNombre();
                Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();
                irAChat(contactoSeleccionado);

            }
        });


    }
    public void obtenerUsuarioDeSharePreferenceYCargarloEnElTextView() {
        int cod = obtenerUsuario();
        SharedPreferences prefs = getSharedPreferences("user" + cod, Context.MODE_PRIVATE);
        if(cod == 1) {
            String nombre = prefs.getString("nombre", "Checo");
            String apellido = prefs.getString("apellido", "Perez");
            String u = nombre + apellido;
            user.setText(u);
        } else {
            String nombre = prefs.getString("nombre", "Pedro");
            String apellido = prefs.getString("apellido", "Altamiranda");
            String u = nombre + apellido;
            user.setText(u);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal,menu);
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.editarPerfil:
            irAEditarPerfil();
            return true;
        case R.id.agregarperfil:
            irAAgragarContacto();
            return true;
        case R.id.cambiarUsuario:
            cambiarUsuario();
            return true;
        default:
            return super.onContextItemSelected(item);
        }
    }
    public void irAEditarPerfil(){
        Intent intentEditar = new Intent(this, PerfilActivity.class);
        startActivity(intentEditar);
    }
    public void irAAgragarContacto() {
        Intent intentCrear = new Intent(this, CrearContactoActivity.class);
        startActivity(intentCrear);
    }
    public void cambiarUsuario(){
        int userNum = obtenerUsuario();
        int newUserNum;
        if(userNum == 1) {
            newUserNum = 2;
        }
        else {
            newUserNum = 1;
        }
        SharedPreferences prefs = getSharedPreferences("userCod", Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = prefs.edit();
        ed.putInt("cod", newUserNum);
        ed.apply();
        this.obtenerUsuarioDeSharePreferenceYCargarloEnElTextView();
    }
    public int obtenerUsuario() {
        SharedPreferences prefs = getSharedPreferences("userCod", Context.MODE_PRIVATE);
        return prefs.getInt("cod", 1);
    }

    public void irAContactos(){
        Intent i = new Intent(this, ContactosActivity.class);
        startActivity(i);
    }
    public void irAChat(Contacto contactoSeleccionado){
        Intent i = new Intent(this, ChatActivity.class);
        i.putExtra("nombre", contactoSeleccionado.getNombre() +" " + contactoSeleccionado.getApellido());
        i.putExtra("numTel", contactoSeleccionado.getNumero());
        i.putExtra("contacto", contactoSeleccionado.getCod());
        startActivity(i);
    }
    private void InicializarControles() {
        usuario = new Usuario();
        listv_contactos = (ListView) findViewById(R.id.listview_mensaje);
        mas = (ImageButton) findViewById(R.id.mas);
        this.LlenarListViews();
        trespuntosmenu = (ImageButton) findViewById(R.id.tresPuntosMenu);
        user = (TextView) findViewById(R.id.usuario);
        this.obtenerUsuarioDeSharePreferenceYCargarloEnElTextView();
    }

    private void LlenarListViews() {
        List<Contacto> contactos = this.obtenerContactoMensaje();
        ContactAdapter cuentaAdapter = new ContactAdapter(listv_contactos.getContext(), contactos);
        listv_contactos.setAdapter(cuentaAdapter);

    }



    public List<Contacto> obtenerContactoMensaje() {
        List<Contacto> listaContactos = new ArrayList<>();
        int lineCount = 0;
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
            System.out.println("Número de líneas en el archivo: " + lineCount);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listaContactos;

    }
}