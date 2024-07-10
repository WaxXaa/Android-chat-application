package com.example.whatsappparcial2.adaptador;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.whatsappparcial2.R;
import com.example.whatsappparcial2.entidades.Chat;
import com.example.whatsappparcial2.entidades.Contacto;

import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends ArrayAdapter<Chat> {
    List<Chat> opciones = new ArrayList<>();

    public ChatAdapter (Context context, List<Chat> datos){
        super(context, R.layout.mensajes,datos);

        this.opciones = datos;
    }



    public View getView(int pos, View view, ViewGroup viewGroup){
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.mensajes,null);

        TextView mensaje = (TextView) item.findViewById(R.id.mensaje);
        int usser = opciones.get(pos).getCodUsuario();
        if (usser == 1 ){
            mensaje.setGravity(Gravity.END);
        }
        else {
            mensaje.setGravity(Gravity.START);
        }

        mensaje.setText(opciones.get(pos).getChat());
        return item;
    }
}

