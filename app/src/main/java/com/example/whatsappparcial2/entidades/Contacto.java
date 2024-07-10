package com.example.whatsappparcial2.entidades;
public class Contacto {

    private int cod;
    private String nombre;
    private String apellido;
    private String numero;
    private String img;
    public Contacto(){}
    public Contacto(int cod, String nombre, String apellido, String numero, String img){
        this.cod = cod;
        this.nombre = nombre;
        this.apellido = apellido;
        this.numero = numero;
        this.img = img;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}