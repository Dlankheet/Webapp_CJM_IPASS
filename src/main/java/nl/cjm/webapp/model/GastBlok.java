package nl.cjm.webapp.model;

import java.io.Serializable;
import java.util.Date;

public class GastBlok implements Serializable {
    protected String naam;
    protected String email;
    protected int telefoon; //Bij gebruik van +31 of nullen is het handiger om strings te gebruiken.
    protected String titel;
    protected String beschrijving;
    protected Date datum;

    public GastBlok(String nm, String em, int tel, String tt, String bs, Date dt){
        this.naam = nm;
        this.email = em;
        this.beschrijving = bs;
        this.telefoon = tel;
        this.titel = tt;
        this.datum = dt;
    }

    public GastBlok(String nm, String em, String tt, String bs, Date dt){
        this.naam = nm;
        this.email = em;
        this.beschrijving = bs;
        this.titel = tt;
        this.datum = dt;
    }

    public String getNaam() {
        return naam;
    }

    public String getEmail() {
        return email;
    }

    public int getTelefoon() {
        return telefoon;
    }

    public String getTitel() {
        return titel;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public Date getDatum() {
        return datum;
    }

    @Override
    public String toString() {
        return "GastBlok{" +
                "naam='" + naam + '\'' +
                ", email='" + email + '\'' +
                ", telefoon='" + telefoon + '\'' +
                ", titel='" + titel + '\'' +
                ", beschrijving='" + beschrijving + '\'' +
                ", datum=" + datum +
                '}';
    }
}