package nl.cjm.model;

import java.io.Serializable;

public abstract class GastBlok implements Serializable {
    protected String naam;
    protected String email;
    protected int telefoon; //Bij gebruik van +31 of nullen is het handiger om strings te gebruiken.
    protected String datum;

    public GastBlok(String nm, String em, int tel, String dt) {
        this.naam = nm;
        this.email = em;
        this.telefoon = tel;
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

    public String getDatum() {
        return datum;
    }
// TODO: 23-6-2020 Schrijven equals voor 4 variabelen

}