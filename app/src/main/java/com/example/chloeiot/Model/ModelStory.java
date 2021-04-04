package com.example.chloeiot.Model;

import android.widget.ImageView;

public class ModelStory {

    String judul,isi,foto;

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public ModelStory() {
    }
    public ModelStory(String judul, String isi, String foto) {
        this.judul = judul;
        this.isi = isi;
        this.foto=foto;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }
}
