
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class SendMail {
 String email;
 int otp;
public SendMail(String email, int otp)
{
    this.email = email;
    this.otp = otp;
}
   public static void main(String [] args) {    
      // Recipient's email ID needs to be mentioned.


      // Sender's email ID needs to be mentioned
     

    }
public void sendMail()
{
     String from = Utils.email;

      // Assuming you are sending email from localhost
      String host = "smtp.gmail.com";

      // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(from, Utils.appPassword);

            }

        });

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));

            // Set Subject: header field
            message.setSubject("MFAuth Email OTP");

            // Now set the actual message
            message.setText("Here is your verifcation OTP: "+otp);

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
        }
}
}