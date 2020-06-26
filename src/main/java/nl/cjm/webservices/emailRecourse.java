package nl.cjm.webservices;

import nl.cjm.model.Contactblok;
import nl.cjm.model.Review;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class emailRecourse {
    public static void sendContactMail(Contactblok contactblok) {
        String host = "smtp.gmail.com";
        final String user = "cjmwebapp@gmail.com";//change accordingly
        final String password = "CJMwebapp1020.";//change accordingly

        String to = "cjmwebapp@gmail.com";//change accordingly

        //Get the session object
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", true);

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user, password);
                    }
                });

        //Compose the message
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Nieuw contactverzoek: " + contactblok.getTitel());
            message.setText("Beste medewerker,\nU heeft een nieuw contactverzoek van " + contactblok.getNaam() + " met het adres " + contactblok.getEmail()
                    + ".\nDe beschrijving is als volgt: \n" + contactblok.getBeschrijving());

            //send the message
            Transport.send(message);

            System.out.println(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    public static void sendReviewMail(Review review) {
        String host = "smtp.gmail.com";
        final String user = "cjmwebapp@gmail.com";//change accordingly
        final String password = "CJMwebapp1020.";//change accordingly

        String to = "cjmwebapp@gmail.com";//change accordingly

        //Get the session object
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", true);

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user, password);
                    }
                });

        //Compose the message
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Nieuwe review met " + review.getAantalSterren() + " sterren!");
            message.setText("Beste medewerker,\nU heeft een nieuwe review van " + review.getNaam() + " met het adres " + review.getEmail()
                    + ".\nDe onderbouwing is als volgt: \n" + review.getOnderbouwing());

            //send the message
            Transport.send(message);

            System.out.println(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}