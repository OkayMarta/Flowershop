package com.flowershop.logging;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import java.util.logging.Handler;
import java.util.logging.Level;

import java.util.logging.LogRecord;

public class MailHandler extends Handler {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MailHandler.class.getName());

    // Перевіряється рівень помилки. Якщо це SEVERE, то відправляється email
    @Override
    public void publish(LogRecord record) {
        if (record.getLevel() == Level.SEVERE) {
            sendEmail(record.getMessage());
        }
    }

    private void sendEmail(String message) {
        // Налаштування SMTP для Gmail
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // Створення сесії з аутентифікацією
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("emailaddress@gmail.com", "password");
            }
        });

        try {
            // Створення повідомлення
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("useremail@gmail.com"));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("adminemail@gmail.com"));
            msg.setSubject("Критична помилка в FlowerShop");
            msg.setText(message);

            // Відправка повідомлення
            Transport.send(msg);
            System.out.println("Електронна лист успішно надіслано");
        } catch (MessagingException e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, "Не вдалося надіслати електронний лист", e);
        }
    }

    @Override
    public void flush() {}
    // Метод flush не виконує дій, оскільки MailHandler не використовує буфер

    @Override
    public void close() throws SecurityException {}
    // Метод close не виконує дій, оскільки MailHandler не потребує закриття ресурсів
}