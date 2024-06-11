package org.uaf.cd_web.components;

import java.util.Properties;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
public class MailSender {

    public static void send(String title, String contentHTML, String toSomeone) {

        final String username = "20130010@st.hcmuaf.edu.vn";
        final String password = "ofyixgacoavjhxxg";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("20130010@st.hcmuaf.edu.vn"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(toSomeone));
            message.setSubject(title);

            MimeBodyPart htmlPart = new MimeBodyPart();
            String htmlContent = contentHTML;
            htmlPart.setContent(htmlContent, "text/html; charset=utf-8");

            // create the Multipart and add the message parts to it
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(htmlPart);

            // add the Multipart to the message
            message.setContent(multipart);

            Transport.send(message);

            System.out.println("Email sent successfully!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}