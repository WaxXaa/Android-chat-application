package com.example.whatsappparcial2.entidades;

public class Chat {
    private String chat;
    private int codUsuario;
    private int codContacto;



    public Chat(int codUsuario,String chat,  int codContacto) {
        this.chat = chat;
        this.codUsuario = codUsuario;
        this.codContacto = codContacto;
    }

    public String getChat() {
        return chat;
    }

    public void setChat(String chat) {
        this.chat = chat;
    }

    public int getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(int codUsuario) {
        this.codUsuario = codUsuario;
    }


    public int getCodContacto() {
        return codContacto;
    }

    public void setCodContacto(int codContacto) {
        this.codContacto = codContacto;
    }
}
