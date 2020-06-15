package nl.cjm.webapp.model;

import java.util.Date;
import java.util.Objects;

public class Review extends GastBlok {
    private int aantalSterren;
    private String onderbouwing;

    public Review(String nm, String em, int tel, String onderbouwing, String dt, int aS) {
        super(nm, em, tel, dt);
        this.aantalSterren = aS;
        this.onderbouwing = onderbouwing;
    }

    public int getAantalSterren() {
        return aantalSterren;
    }

    public String getOnderbouwing() {
        return onderbouwing;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Review)) return false;
        Review review = (Review) o;
        return aantalSterren == review.aantalSterren &&
                Objects.equals(onderbouwing, review.onderbouwing);
    }

    @Override
    public String toString() {
        return "Review{" +
                "aantalSterren=" + aantalSterren +
                ", onderbouwing='" + onderbouwing + '\'' +
                ", naam='" + naam + '\'' +
                ", email='" + email + '\'' +
                ", telefoon=" + telefoon +
                ", datum='" + datum + '\'' +
                '}';
    }
}
