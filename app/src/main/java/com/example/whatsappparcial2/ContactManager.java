package com.example.whatsappparcial2;


import android.content.Context;
import android.util.Log;

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

class ContactManager {
    private Contacto contacto;
    private Context context;
    public Contacto getContacto() {
        return contacto;
    }

    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
    }



    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }




    public void guardarContacto(String mensaje) {
        try {

            OutputStreamWriter fout = new OutputStreamWriter(
                    context.openFileOutput( "_chat.txt", Context.MODE_APPEND));
            fout.write(mensaje + "\n");
            fout.close();
        } catch (Exception ex) {
            Log.e("Ficheros", "Error al escribir fichero a memoria interna");
        }
    }

}
