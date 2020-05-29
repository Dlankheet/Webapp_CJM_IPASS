package nl.cjm.webapp.model;

import java.util.Date;

public class GastBlok {
    protected String naam;
    protected String email;
    protected String telefoon; //Bij gebruik van +31 of nullen is het handiger om strings te gebruiken.
    protected String titel;
    protected String beschrijving;
    protected Date datum;

    public GastBlok(String nm, String em, String tel, String tt, String bs, Date dt){
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