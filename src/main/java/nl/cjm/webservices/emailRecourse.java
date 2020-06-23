package nl.cjm.webservices;
import nl.cjm.model.Contactblok;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class emailRecourse {
    public static void sendContactMail(Contactblok contactblok){
        String host="smtp.gmail.com";
        final String user="cjmwebapp@gmail.com";//change accordingly
        final String password="CJMwebapp1020.";//change accordingly

        String to="lankheet.daniel@gmail.com";//change accordingly

        //Get the session object
        Properties props = new Properties();
        props.put("mail.smtp.host",host);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable",true);

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user,password);
                    }
                });

        //Compose the message
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            message.setSubject("Nieuw contactverzoek: " +contactblok.getTitel());
            message.setText(contactblok.toString());

            //send the message
            Transport.send(message);

            System.out.println(message);

        } catch (MessagingException e) {e.printStackTrace();}
    }
}