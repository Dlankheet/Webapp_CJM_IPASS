package nl.cjm.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WebsiteTest {
    //In de toekomst wil ik alle validatie ook laten doen door de klassen zelf in plaats van alleen bij de restservice.
    // Dan zullen de tests ook hierbij komen.
    @Test
    void addPendingReview() {
        Website website = new Website();
        Review testreview = new Review("Hendrik Jan", "test.test@test.nl", 612345678, "Dit is een goede service!", "20-05-200", 5);
        website.addPendingReview(testreview);
        assertTrue(website.getPendingReviews().contains(testreview));
    }

    @Test
    void addContactverzoek() {
        Website website = new Website();
        Contactblok testverzoek = new Contactblok("Hendrik Jan", "test.test@test.nl", 612345678, "Testverzoek", "Ik wil een contact", "12-07-2000");
        website.addContactverzoek(testverzoek);
        assertTrue(website.getContactVerzoeken().contains(testverzoek));
    }

    @Test
    void removeContactverzoek() {
        Website website = new Website();
        Contactblok testverzoek = new Contactblok("Hendrik Jan", "test.test@test.nl", 612345678, "Testverzoek", "Ik wil een contact", "12-07-2000");
        website.addContactverzoek(testverzoek);
        website.removeContactverzoek(testverzoek);
        assertFalse(website.getContactVerzoeken().contains(testverzoek));

    }
}// Tests bekijken bigshopper.