package nl.cjm.webapp.model;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Website implements Serializable {
    private ArrayList<Fotoslider> fotosliders = new ArrayList<>();
    private ArrayList<Beheerder> Beheerders = new ArrayList<>();
    private ArrayList<Review> pendingReviews = new ArrayList<>();
    private ArrayList<Review> geaccepteerdeReviews = new ArrayList<>();
    private ArrayList<GastBlok> contactVerzoeken = new ArrayList<>();
    private boolean beheerderIngelogd;

    private static Website website = new Website(false);
    public static Website getWebsite() {
        return website;
    }

    public static void setWebsite(Website website){
        Website.website = website;}

    public Website(boolean beheer){
        this.beheerderIngelogd = beheer;
    }
    public ArrayList<Fotoslider> getFotosliders() {
        return fotosliders;
    }
    public void addFotoslider(Fotoslider fotoslider) {
        fotosliders.add(fotoslider);
    }

    public ArrayList<Beheerder> getBeheerders() {
        return Beheerders;
    }

    public void addBeheerders(String iN, String ww, String eA) {
        Beheerder beheerder = new Beheerder(iN,ww,eA);
        Beheerders.add(beheerder);
    }

    public ArrayList<Review> getPendingReviews() {
        if(beheerderIngelogd) {
            return pendingReviews;
        }else return null;
    }

    public void setPendingReviewsReviews(ArrayList<Review> pendingReviews) {
        this.pendingReviews = pendingReviews;
    }

    public ArrayList<Review> getGeaccepteerdeReviews() {
        return geaccepteerdeReviews;
    }

    public void setGeaccepteerdeReviews(ArrayList<Review> geaccepteerdeReviews) {
        this.geaccepteerdeReviews = geaccepteerdeReviews;
    }

    public ArrayList<GastBlok> getContactVerzoeken() {
        if(beheerderIngelogd) {
            return contactVerzoeken;
        }else return null;
    }

    public void setContactVerzoeken(ArrayList<GastBlok> contactVerzoeken) {
        this.contactVerzoeken = contactVerzoeken;
    }
}
