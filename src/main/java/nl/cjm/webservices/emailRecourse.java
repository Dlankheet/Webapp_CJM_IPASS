//package nl.cjm.webservices;
//import java.util.Properties;
//import javax.jms.Message;
//import javax.mail.*;
//import javax.mail.internet.*;
//
//public class emailRecourse {
//    String host = "smtp-mail.outlook.com";
//    final String user="daniel.lankheet@hotmail.com";//change accordingly
//    final String password="";//change accordingly
//
//    String to="lankheet.daniel@gmail.com";//change accordingly
//
//    //Get the session object
//    Properties props = new Properties();
//   props.put("mail.smtp.host", host);
//   props.put("mail.smtp.auth", "true");
//
//    Session session = Session.getDefaultInstance(props,
//            new javax.mail.Authenticator() {
//                protected PasswordAuthentication getPasswordAuthentication() {
//                    return new PasswordAuthentication(user,password);
//                }
//            });
//
//    public static void sendMail(String klantmail, String titel, String beschrijving) {
//        //Compose the message
//        try {
//            MimeMessage message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(user));
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//            message.setSubject("U heeft een melding van de CJM-Webapp!");
//            message.setText("Zender: " + klantmail+ "\n Titel: + " + titel
//            + "\n Body: " + beschrijving);
//
//            //send the message
//            Transport.send(message);
//
//            System.out.println("message sent successfully...");
//
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//    }
//}
