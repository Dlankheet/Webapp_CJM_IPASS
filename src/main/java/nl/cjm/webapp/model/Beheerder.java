package nl.cjm.webapp.model;

import java.util.Objects;

public class Beheerder {
    private String inlogNaam; //Hier wil ik in de toekomst nog meer over leren om dit veilig weg te schrijven in de online omgeving.
    private String wachtwoord;
    private String emailAdres;

    public Beheerder(String iN, String ww, String eA){
        this.inlogNaam = iN;
        this.wachtwoord = ww;
        this.emailAdres = eA;
    }


    public String getInlogNaam() {
        return inlogNaam;
    }

    public void setInlogNaam(String inlogNaam) {
        this.inlogNaam = inlogNaam;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }

    public String getEmailAdres() {
        return emailAdres;
    }

    public void setEmailAdres(String emailAdres) {
        this.emailAdres = emailAdres;
    }

    public boolean isEqual(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Beheerder beheerder = (Beheerder) o;
        return Objects.equals(inlogNaam, beheerder.inlogNaam) &&
                Objects.equals(wachtwoord, beheerder.wachtwoord) &&
                Objects.equals(emailAdres, beheerder.emailAdres);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inlogNaam, wachtwoord, emailAdres);
    }
}


