package nl.cjm.model;

import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class Foto implements Serializable {
    private String fotoBase64;
    private String titel;
    private String beschrijving;

    public Foto(String foto, String tt, String desc){
        this.fotoBase64 = foto;
        this.beschrijving = desc;
        this.titel = tt;
    }

    public String getFotoBase64() {
        return fotoBase64;
    }

    public String getTitel() {
        return titel;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Foto)) return false;
        Foto foto = (Foto) o;
        return titel.equals(foto.titel); //Titel van een foto moet uniek zijn!
    }

}