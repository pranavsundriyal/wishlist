package com.wishlist.email;

import com.wishlist.model.rule.Rule;
import com.wishlist.model.slim.SlimResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Component
public class Email {

    @Value("${setting.user}")
    private String username;

    @Value("${setting.pass}")
    private String password;

    public void sendMail(SlimResponse response, Rule rule){

        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        Properties props = System.getProperties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
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
            Message msg = new MimeMessage(session);

            msg.setFrom(new InternetAddress(username));
            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(rule.getEmail(),false));
            msg.setSubject(WishListMessage.createSubject(rule));
            msg.setContent(WishListMessage.createMessage(rule, response), "text/html");
//            msg.setText(WishListMessage.createMessage(rule, response));
            msg.setSentDate(new Date());
            Transport.send(msg);
        }catch (MessagingException e){ System.out.println("error cause: " + e);}
    }

}
