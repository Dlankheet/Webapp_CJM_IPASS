package nl.cjm.webapp.model;

import java.util.Date;

public class Review extends GastBlok{
    private int aantalSterren;
    private boolean geaccepteerd;

    public Review(String nm, String em, int tel, String tt, String bs, Date dt, int aS, boolean acc) {
        super(nm, em, tel, tt, bs, dt);
        this.aantalSterren = aS;
        this.geaccepteerd = acc;
    }
    public Review(String nm, String em, String tt, String bs, Date dt, int aS, boolean acc) {
        super(nm, em, tt, bs, dt);
        this.aantalSterren = aS;
        this.geaccepteerd = acc;
    }

    public int getAantalSterren() {
        return aantalSterren;
    }

    public void setAantalSterren(int aantalSterren) {
        this.aantalSterren = aantalSterren;
    }

    public boolean isGeaccepteerd() {
        return geaccepteerd;
    }

    public void setGeaccepteerd(boolean geaccepteerd) {
        this.geaccepteerd = geaccepteerd;
    }
}
