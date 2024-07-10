package com.example.whatsappparcial2.adaptador;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.whatsappparcial2.R;
import com.example.whatsappparcial2.entidades.Contacto;

import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends ArrayAdapter<Contacto> {
    List<Contacto> opciones = new ArrayList<>();

    public ContactAdapter (Context context, List<Contacto> datos){
        super(context, R.layout.listview_mensaje,datos);

        this.opciones = datos;
    }
    public View getView(int pos, View view, ViewGroup viewGroup){
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.listview_mensaje,null);

        TextView nombre = (TextView) item.findViewById(R.id.card_nombre);
        nombre.setText(opciones.get(pos).getNombre() + " " + opciones.get(pos).getApellido());

        TextView numero = (TextView) item.findViewById(R.id.card_numero);
        numero.setText(opciones.get(pos).getNumero());

        ImageView img = (ImageView) item.findViewById(R.id.card_img);
        int resourceId = getContext().getResources().getIdentifier("profile" + opciones.get(pos).getImg(), "drawable", getContext().getPackageName());
        img.setImageResource(resourceId);

        return item;
    }
}
