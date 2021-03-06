package nl.cjm.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WebsiteTest {
    @Test //Test of review daadwerkelijk bij de klant wordt toegevoegd.
    void addPendingReviewGoed() {
        Website website = new Website();
        Review testreview = new Review("Hendrik Jan", "test.test@test.nl", "0612345678", "Dit is een goede service!", "20-05-200", 5);
        website.addPendingReview(testreview);
        assertTrue(website.getPendingReviews().contains(testreview));
    }

    @Test
    void addReviewTeKortNummer(){ //Testen of een te kort nummer erdoor komt.
        Website website = new Website();
        Review review = new Review("Hendrik Jan", "test.test@test.nl", "061234", "Dit is een goede service!", "20-05-200", 5);
        assertThrows(IllegalArgumentException.class,()-> website.addPendingReview(review));
        assertFalse(website.getPendingReviews().contains(review));
    }

    @Test
    void addreviewTeLangNummer(){ //Testen of een te lang nummer erdoor komt.
        Website website = new Website();
        Review review = new Review("Hendrik Jan", "test.test@test.nl", "06123456789", "Dit is een goede service!", "20-05-200", 5);
        assertThrows(IllegalArgumentException.class,()-> website.addPendingReview(review));
        assertFalse(website.getPendingReviews().contains(review));
    }

    @Test
    void addReviewPluseenendertig() { //Testen of een +31 er wel door komt
        Website website = new Website();
        Review review = new Review("Hendrik Jan", "test.test@test.nl", "+31612345678", "Dit is een goede service!", "20-05-200", 5);
        website.addPendingReview(review);
        assertTrue(website.getPendingReviews().contains(review));
    }

    @Test
    void addReviewDuplicate(){ //Test of een duplicaat word tegengehouden.
        Website website = new Website();
        Review review = new Review("Hendrik Jan", "test.test@test.nl", "+31612345678", "Dit is een goede service!", "20-05-200", 5);
        website.addPendingReview(review);
        assertThrows(IllegalArgumentException.class,()-> website.addPendingReview(review));
    }

    @Test
    void removeReviewverzoek() { // Kijken of een verwijderd object niet terug te vinden is.
        Website website = new Website();
        Review review = new Review("Hendrik Jan", "test.test@test.nl", "+31612345678", "Dit is een goede service!", "20-05-200", 5);
        website.addPendingReview(review);
        website.removeReview(review);
        assertFalse(website.getPendingReviews().contains(review));
    }
    @Test
    void acceptReview() { // Kijken of een verwijderd object niet terug te vinden is.
        Website website = new Website();
        Review review = new Review("Hendrik Jan", "test.test@test.nl", "+31612345678", "Dit is een goede service!", "20-05-200", 5);
        website.addPendingReview(review);
        website.acceptReview(review);
        assertTrue(website.getGeaccepteerdeReviews().contains(review));
    }
    @Test
    void acceptReviewSafety() { // Kijken of de data van de klant op null is gezet.
        Website website = new Website();
        Review review = new Review("Hendrik Jan", "test.test@test.nl", "+31612345678", "Dit is een goede service!", "20-05-200", 5);
        website.addPendingReview(review);
        website.acceptReview(review);
        Review acceptedReview = website.getGeaccepteerdeReviews().get(0);
        assertNull(acceptedReview.email);
        assertNull(acceptedReview.telefoon);
    }

    @Test
    void addContactverzoekGoed() { //test of Object echt toegevoegd wordt.
        Website website = new Website();
        Contactblok testverzoek = new Contactblok("Hendrik Jan", "test.test@test.nl", "0612345678", "Testverzoek", "Ik wil een contact", "12-07-2000");
        website.addContactverzoek(testverzoek);
        assertTrue(website.getContactVerzoeken().contains(testverzoek));
    }

    @Test
    void addContactverzoekTeKortNummer(){ //Testen of een te kort nummer erdoor komt.
        Website website = new Website();
        Contactblok testverzoek = new Contactblok("Hendrik Jan", "test.test@test.nl", "061234", "Testverzoek", "Ik wil een contact", "12-07-2000");
        assertThrows(IllegalArgumentException.class,()-> website.addContactverzoek(testverzoek));
        assertFalse(website.getContactVerzoeken().contains(testverzoek));
    }

    @Test
    void addContactverzoekTeLangNummer(){ //Testen of een te lang nummer erdoor komt.
        Website website = new Website();
        Contactblok testverzoek = new Contactblok("Hendrik Jan", "test.test@test.nl", "06123456789", "Testverzoek", "Ik wil een contact", "12-07-2000");
        assertThrows(IllegalArgumentException.class,()-> website.addContactverzoek(testverzoek));
    }

    @Test
    void addContactverzoekPluseenendertig() { //Testen of een +31 er wel door komt
        Website website = new Website();
        Contactblok testverzoek = new Contactblok("Hendrik Jan", "test.test@test.nl", "+31612345678", "Testverzoek", "Ik wil een contact", "12-07-2000");
        website.addContactverzoek(testverzoek);
        assertTrue(website.getContactVerzoeken().contains(testverzoek));
    }

    @Test
    void addContactverzoekDuplicate(){ //Test of een duplicaat word tegengehouden.
        Website website = new Website();
        Contactblok testverzoek = new Contactblok("Hendrik Jan", "test.test@test.nl", "+31612345678", "Testverzoek", "Ik wil een contact", "12-07-2000");
        website.addContactverzoek(testverzoek);
        assertThrows(IllegalArgumentException.class,()-> website.addContactverzoek(testverzoek));
    }

    @Test
    void addContactverzoekEmailtest(){ //Test verschillende e-mail adressen op fout en niet fout!
        Website website = new Website();
        Contactblok testverzoek = new Contactblok("Hendrik Jan", "john@somewhere.com", "0612345678", "Testverzoek1", "Ik wil een contact enzo", "12-07-2000");
        Contactblok testverzoek1 = new Contactblok("Hendrik Jan", "john@192.168.1.10", "0612345678", "Testverzoek3", "Dit is ook een test", "13-07-2000");
        Contactblok testverzoek2 = new Contactblok("Hendrik Jan", "john+label@192.168.1.10", "0612345678", "Testverzoek", "Ik wil een contact", "14-07-2000");
        Contactblok testverzoek3 = new Contactblok("Hendrik Jan", "@someserver.com", "0612345678", "Testverzoek", "Ik wil een contact", "12-07-2000");
        Contactblok testverzoek4 = new Contactblok("Hendrik Jan", "john@.", "0612345678", "Testverzoek", "Ik wil een contact", "12-07-2000");
        Contactblok testverzoek5 = new Contactblok("Hendrik Jan", ".@somewhere.com", "0612345678", "Testverzoek", "Ik wil een contact", "12-07-2000");

        website.addContactverzoek(testverzoek);
        website.addContactverzoek(testverzoek1);
        website.addContactverzoek(testverzoek2);

        assertThrows(IllegalArgumentException.class,()-> website.addContactverzoek(testverzoek3));
        assertThrows(IllegalArgumentException.class,()-> website.addContactverzoek(testverzoek4));
        assertThrows(IllegalArgumentException.class,()-> website.addContactverzoek(testverzoek5));
        assertTrue(website.getContactVerzoeken().contains(testverzoek));
        assertTrue(website.getContactVerzoeken().contains(testverzoek1));
        assertTrue(website.getContactVerzoeken().contains(testverzoek2));
    }

    @Test
    void removeContactverzoek() { // Kijken of een verwijderd object niet terug te vinden is.
        Website website = new Website();
        Contactblok testverzoek = new Contactblok("Hendrik Jan", "test.test@test.nl", "0612345678", "Testverzoek", "Ik wil een contact", "12-07-2000");
        website.addContactverzoek(testverzoek);
        website.removeContactverzoek(testverzoek);
        assertFalse(website.getContactVerzoeken().contains(testverzoek));

    }


    @Test
    void addLeGFoto() {
        Website website = new Website();
        Foto legfoto = new Foto("ditMoetEenFotoVoorstellen", "Dit is de titel van de foto", "Dit is de beschrijving van de foto.");
        website.addLeGFoto(legfoto);
        assertTrue(website.getFotosliderLeG().contains(legfoto));
    }

    @Test
    void addElektraFoto() {
        Website website = new Website();
        Foto elektra = new Foto("ditMoetEenFotoVoorstellen", "Dit is de titel van de foto", "Dit is de beschrijving van de foto.");
        website.addElektraFoto(elektra);
        assertTrue(website.getFotosliderElektra().contains(elektra));
    }

    @Test
    void removeFotoElektra() {
        Website website = new Website();
        Foto elektra = new Foto("ditMoetEenFotoVoorstellen", "Dit is de titel van de foto", "Dit is de beschrijving van de foto.");
        website.addElektraFoto(elektra);
        website.removeFotoElektra(elektra);
        assertFalse(website.getFotosliderElektra().contains(elektra));
    }

    @Test
    void removeFotoleg() {
        Website website = new Website();
        Foto legfoto = new Foto("ditMoetEenFotoVoorstellen", "Dit is de titel van de foto", "Dit is de beschrijving van de foto.");
        website.addLeGFoto(legfoto);
        website.removeFotoleg(legfoto);
        assertFalse(website.getFotosliderLeG().contains(legfoto));
    }

    @Test
    void duplicateLeGFoto() {
        Website website = new Website();
        Foto legfoto = new Foto("ditMoetEenFotoVoorstellen", "Dit is de titel van de foto", "Dit is de beschrijving van de foto.");
        website.addLeGFoto(legfoto);
        assertThrows(IllegalArgumentException.class,()-> website.addLeGFoto(legfoto));
    }

    @Test
    void duplicateElektraFoto() {
        Website website = new Website();
        Foto elektra = new Foto("ditMoetEenFotoVoorstellen", "Dit is de titel van de foto", "Dit is de beschrijving van de foto.");
        website.addElektraFoto(elektra);
        assertThrows(IllegalArgumentException.class,()-> website.addElektraFoto(elektra));
    }

}