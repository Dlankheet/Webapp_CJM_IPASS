package nl.cjm.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Website implements Serializable {
    private ArrayList<Fotoslider> fotosliders = new ArrayList<>();
    private ArrayList<Review> pendingReviews = new ArrayList<>();
    private ArrayList<Review> geaccepteerdeReviews = new ArrayList<>();
    private ArrayList<Contactblok> contactVerzoeken = new ArrayList<>();

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

    public void addPendingReview(Review pendingReview) {
        pendingReviews.add(pendingReview);
    }

    public ArrayList<Review> getGeaccepteerdeReviews() {
        return geaccepteerdeReviews;
    }

    public void setGeaccepteerdeReviews(ArrayList<Review> geaccepteerdeReviews) {
        this.geaccepteerdeReviews = geaccepteerdeReviews;
    }

    public ArrayList<Contactblok> getContactVerzoeken() {
        return contactVerzoeken;
    }

    public void setContactVerzoeken(ArrayList<Contactblok> contactVerzoeken) {
        this.contactVerzoeken = contactVerzoeken;
    }

    public void addContactverzoek(Contactblok gastblok) {
        contactVerzoeken.add(gastblok);
    }

    public void removeContactverzoek(Contactblok contact){
        contactVerzoeken.remove(contact);
    }
}
