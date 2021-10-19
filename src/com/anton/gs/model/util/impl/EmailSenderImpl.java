package com.anton.gs.model.util.impl;

import com.anton.gs.model.exception.ServiceException;
import com.anton.gs.model.util.EmailSender;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Properties;

public final class EmailSenderImpl implements EmailSender {
    private static Logger logger = LogManager.getLogger();


    private final String MAIL_CONFIG = "mail.properties";
    private final String MAIL_CONFIG_USER = "username";
    private final String MAIL_CONFIG_PASSWORD = "userpass";
    private final String TYPE_HTML = "text/html";

    private static EmailSenderImpl emailSenderImpl;
    private Properties properties = new Properties();


    public static EmailSenderImpl getInstance() {
        if (emailSenderImpl == null) {
            emailSenderImpl = new EmailSenderImpl();
        }
        return emailSenderImpl;
    }

    @Override
    public void sendEmail(String subject, String message, String recipientEmail) throws ServiceException {
        try {
            properties.load(EmailSenderImpl.class.getClassLoader().getResourceAsStream(MAIL_CONFIG));
        } catch (IOException e) {
            logger.log(Level.ERROR, "Sending emil failed. Couldn't find email conf or file cinf: " + MAIL_CONFIG, e);
            throw new ServiceException(e);
        }
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(properties.getProperty(MAIL_CONFIG_USER),
                        properties.getProperty(MAIL_CONFIG_PASSWORD));
            }
        });
        Message mimeMessage = new MimeMessage(session);
        Address addressTo = null;
        MimeMultipart multipart = new MimeMultipart();
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        try {
            mimeMessage.setSubject(subject);
            addressTo = new InternetAddress(recipientEmail);
            mimeMessage.setRecipient(Message.RecipientType.TO, addressTo);
            messageBodyPart.setText(message);
            multipart.addBodyPart(messageBodyPart);
            mimeMessage.setContent(multipart);
            Transport.send(mimeMessage);
        } catch (AddressException e) {
            logger.log(Level.ERROR, "email sending failed, " +
                    "wrong recipient address: " + addressTo + "or sservice sending address" + MAIL_CONFIG_USER +
                    "or wrong password ", e);
            throw new ServiceException(e);
        } catch (MessagingException e) {
            throw new ServiceException();
        }


    }
}

