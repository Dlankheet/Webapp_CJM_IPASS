package nl.cjm.webapp.model;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Website implements Serializable {
    private ArrayList<Fotoslider> fotosliders = new ArrayList<>();
    private ArrayList<Review> pendingReviews = new ArrayList<>();
    private ArrayList<Review> geaccepteerdeReviews = new ArrayList<>();
    private ArrayList<GastBlok> contactVerzoeken = new ArrayList<>();

    private static Website website = new Website();

    public static Website getWebsite() {
        return website;
    }

    public static void setWebsite(Website website) {
        Website.website = website;
    }

    public ArrayList<Fotoslider> getFotosliders() {
        return fotosliders;
    }

    public void addFotoslider(Fotoslider fotoslider) {
        fotosliders.add(fotoslider);
    }


    public ArrayList<Review> getPendingReviews() {
        return pendingReviews;
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
        return contactVerzoeken;
    }

    public void setContactVerzoeken(ArrayList<GastBlok> contactVerzoeken) {
        this.contactVerzoeken = contactVerzoeken;
    }

    public void addContactverzoek(GastBlok gastblok) {
        contactVerzoeken.add(gastblok);
    }

}
