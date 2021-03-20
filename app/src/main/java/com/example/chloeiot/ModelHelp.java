package com.example.chloeiot;

public class ModelHelp {

  String pertanyaan;

    public ModelHelp(String judul) {
        this.pertanyaan = judul;
    }

    public ModelHelp() {
    }

    public String getPertanyaan() {
        return pertanyaan;
    }

    public void setPertanyaan(String pertanyaan) {
        this.pertanyaan = pertanyaan;
    }
}
