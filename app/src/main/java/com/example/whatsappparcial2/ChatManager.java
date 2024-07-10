package com.example.whatsappparcial2;

import android.content.Context;
import android.util.Log;

import com.example.whatsappparcial2.entidades.Chat;
import com.example.whatsappparcial2.entidades.Contacto;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChatManager {
    private Context context;
    private String nombreContacto;


    public ChatManager(Context context ){
        this.context = context;
        this.nombreContacto = nombreContacto;
    }
    public void crearChat(int contactoId)  {
        String chatsViejos = "";
        try {
            FileInputStream file = context.openFileInput("chats.txt");
            if(file != null){
                BufferedReader bf = new BufferedReader(new InputStreamReader(file));
                chatsViejos = bf.readLine();
                bf.close();
            }
            String[] chatContacto = chatsViejos.split("~~");

            OutputStreamWriter fout = new OutputStreamWriter(context.openFileOutput("chats.txt", Context.MODE_PRIVATE));
            fout.write(contactoId + "~~");
        }catch (Exception e ){

        }
    }


/*    public List<Chat> obtenerMensajes(int contacto) throws IOException {

        List<Chat> mensajes = new ArrayList<>();

        try {
            FileInputStream fis =
        } catch (Exception ex) {
            Log.e("Ficheros", "Error al leer fichero desde memoria interna" + ex.getMessage());
        }
            if (br != null) {
                br.close();

        }
        return mensajes;
    }
    public List<Contacto> obtenerContacto() {
        List<Contacto> listaContactos = new ArrayList<>();
        try {
            FileInputStream fis = openFileInput("contacto.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
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

    }*/


}
