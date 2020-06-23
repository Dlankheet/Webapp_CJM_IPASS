package nl.cjm.model;

import java.util.Objects;

public class Contactblok extends GastBlok{
    private String titel;
    private String beschrijving;

    public Contactblok(String nm, String em, int tel, String tt, String bs, String dt) {
        super(nm, em, tel, dt);
        this.titel = tt;
        this.beschrijving = bs;
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
        if (!(o instanceof Contactblok)) return false;
        Contactblok that = (Contactblok) o;
        return Objects.equals(titel, that.titel) &&
                Objects.equals(beschrijving, that.beschrijving);
    }

    @Override
    public String toString() {
        return "Contactblok{" +
                "titel='" + titel + '\'' +
                ", beschrijving='" + beschrijving + '\'' +
                ", naam='" + naam + '\'' +
                ", email='" + email + '\'' +
                ", telefoon=" + telefoon +
                ", datum='" + datum + '\'' +
                '}';
    }
}
