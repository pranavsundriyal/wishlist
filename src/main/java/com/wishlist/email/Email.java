package com.wishlist.email;

import com.wishlist.model.rule.Rule;
import com.wishlist.model.slim.SlimResponse;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;


/**
 * Created by psundriyal on 3/23/16.
 */
public class Email {

    public void sendMail(SlimResponse response, Rule rule){

        final String username = "wishlistexpedia@gmail.com";
        final String password = "";
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        // Get a Properties object
        Properties props = System.getProperties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
       // props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.store.protocol", "pop3");
        props.put("mail.transport.protocol", "smtp");

        try{
            Session session = Session.getDefaultInstance(props,
                    new Authenticator(){
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }});
            // -- Create a new message --
            Message msg = new MimeMessage(session);

            // -- Set the FROM and TO fields --
            msg.setFrom(new InternetAddress("wishlist@gmail.com"));
            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(rule.getEmail(),false));
            msg.setSubject("wishlist");
            msg.setText("found cheap tickets");
            msg.setSentDate(new Date());
            Transport.send(msg);
        }catch (MessagingException e){ System.out.println("error cause: " + e);}
    }

}
