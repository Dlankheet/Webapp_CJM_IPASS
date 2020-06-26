package nl.cjm.model;

import java.io.Serializable;
import java.util.Objects;

public abstract class GastBlok implements Serializable {
    protected String naam;
    protected String email;
    protected String telefoon; //Wiskundige expressies zijn niet nodig bij een telefoonnummer dus dat kan een string zijn.
    protected String datum;

    public GastBlok(String nm, String em, String tel, String dt) {
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

    public String getTelefoon() {
        return telefoon;
    }

    public String getDatum() {
        return datum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GastBlok)) return false;
        GastBlok gastBlok = (GastBlok) o;
        return datum.equals(gastBlok.datum); //Datum is uniek per verzoek.
    }

}