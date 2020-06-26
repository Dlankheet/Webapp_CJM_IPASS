package nl.cjm.model;

import java.io.Serializable;
import java.util.ArrayList;
import nl.cjm.setup.Utils;

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

    public ArrayList<Review> getGeaccepteerdeReviews() {
        return geaccepteerdeReviews;
    }

    public ArrayList<Contactblok> getContactVerzoeken() {
        return contactVerzoeken;
    }

    public void setContactVerzoeken(ArrayList<Contactblok> contactVerzoeken) {
        this.contactVerzoeken = contactVerzoeken;
    }

    public ArrayList<Foto> getFotosliderElektra() {
        return fotosliderElektra;
    }

    public void addContactverzoek(Contactblok gastblok) {
        if (contactVerzoeken.contains(gastblok)) {
            throw new IllegalArgumentException("Dit verzoek bestaat al.");
        } else if (!Utils.checkPhone(gastblok.getTelefoon())) {
            throw new IllegalArgumentException("Uw telefoonnummer klopt niet.");
        }else if (!Utils.checkEmail(gastblok.getEmail())) {
            throw new IllegalArgumentException("Uw e-mail klopt niet.");}
        else {
            contactVerzoeken.add(gastblok);
        }
    }

    public void addPendingReview(Review pendingReview) {
        if (pendingReviews.contains(pendingReview)) {
            throw new IllegalArgumentException("Dit verzoek bestaat al.");
        } else if (!Utils.checkPhone(pendingReview.getTelefoon())) {
            throw new IllegalArgumentException("Uw telefoonnummer klopt niet.");
        }else if (!Utils.checkEmail(pendingReview.getEmail())) {
            throw new IllegalArgumentException("Uw e-mail klopt niet.");}
        else if (pendingReview.getAantalSterren() > 5 || pendingReview.getAantalSterren() < 1) {
            throw new IllegalArgumentException("Voer een geldig aantal sterren in!");}
        else {
            pendingReviews.add(pendingReview);
        }
    }

    public void addLeGFoto(Foto foto) {
        if (fotosliderLeG.contains(foto)) {
            throw new IllegalArgumentException("Een foto met deze titel is al geplaats.");
        }else{
            fotosliderLeG.add(foto);
        }
    }

    public void addElektraFoto(Foto foto) {
        if (fotosliderElektra.contains(foto)) {
            throw new IllegalArgumentException("Een foto met deze titel is al geplaats.");
        }else{
            fotosliderElektra.add(foto);
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
    }


    public void removeFotoElektra(Foto foto) {
        fotosliderElektra.remove(foto);
    }


    public void removeFotoleg(Foto foto) {
        fotosliderLeG.remove(foto);
    }

    public ArrayList<Foto> getFotosliderLeG() {
        return fotosliderLeG;
    }
}
