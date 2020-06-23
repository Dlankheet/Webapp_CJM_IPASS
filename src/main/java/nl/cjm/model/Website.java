package nl.cjm.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Website implements Serializable {
    private ArrayList<Foto> fotosliderElektra = new ArrayList<>();
    private ArrayList<Foto> fotosliderLeG = new ArrayList<>();
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

    public ArrayList<Review> getPendingReviews() {
        return pendingReviews;
    }

    public void addPendingReview(Review pendingReview) {
        pendingReviews.add(pendingReview);
    }

    public ArrayList<Review> getGeaccepteerdeReviews() {
        return geaccepteerdeReviews;
    }

    public ArrayList<Contactblok> getContactVerzoeken() {
        return contactVerzoeken;
    }

    public void setContactVerzoeken(ArrayList<Contactblok> contactVerzoeken) {
        this.contactVerzoeken = contactVerzoeken;
    }

    public void addContactverzoek(Contactblok gastblok) {
        if(!contactVerzoeken.contains(gastblok)) {// minimale check
            contactVerzoeken.add(gastblok);
        }
    }

    public void removeContactverzoek(Contactblok contact) {
        contactVerzoeken.remove(contact);
    }

    public void removeReview(Review review) {
        pendingReviews.remove(review);
    }

    public void acceptReview(Review review) {
        review.setPrivateinfoNull();
        geaccepteerdeReviews.add(review);
        pendingReviews.remove(review);
        System.out.println("reviewwueeueii"+geaccepteerdeReviews);
    }

    public ArrayList<Foto> getFotosliderElektra() {
        return fotosliderElektra;
    }

    public boolean addElektraFoto(Foto foto) {
        if(!fotosliderElektra.contains(foto)) {// minimale check
            fotosliderElektra.add(foto);
            return true;
        }return false;
    }
    public void removeFotoElektra(Foto foto) {
        fotosliderElektra.remove(foto);
    }

    public boolean addLeGFoto(Foto foto){
        if(!fotosliderLeG.contains(foto)) {// minimale check
            fotosliderLeG.add(foto);
            return true;
        }return false;
    }

    public void removeFotoleg(Foto foto) {
        fotosliderLeG.remove(foto);
    }

    public ArrayList<Foto> getFotosliderLeG() {
        return fotosliderLeG;
    }
}
