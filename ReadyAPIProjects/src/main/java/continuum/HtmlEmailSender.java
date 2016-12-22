package continuum;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Date;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.sound.midi.Receiver;

import org.apache.commons.io.IOUtils;



import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;




/***************
 * Utility to send cucumber reports
 * @author sneha.chemburkar
 *
 */
public class HtmlEmailSender {

	static String absolutePath=new File("").getAbsolutePath();

	public static void sendEmail(final String userName, final String password, String reciever,
			String subject, String message, File report)
	{
		try{
			// sets SMTP server properties
			Properties properties = new Properties();
			properties.setProperty("mail.smtp.host","smtp.office365.com");
			properties.setProperty("mail.smtp.port","587");
			properties.setProperty("mail.smtp.auth", "true");

			properties.setProperty("mail.smtp.starttls.enable", "true");
			//   properties.setProperty("mail.smtp.EnableSSL.enable","true");
			//oo properties.setProperty("mail.smtp.ssl.trust",Utilities.getMavenProperties("emailHost"));

			Session session = Session.getInstance(properties,
					new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(userName, password);
				}
			});

			

			// creates a new e-mail message
			Message msg = new MimeMessage(session);

			msg.setFrom(new InternetAddress(userName));

			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(reciever));


			msg.setSubject(subject);
			msg.setSentDate(new Date());


			addReportToMailBody(msg, report);
			Transport.send(msg);
			System.out.println("********Sending report mail**********");

		} catch (MessagingException e) {
			System.out.println("****************Unable to Send Email : " + e.getMessage());
		}

	}
	public static void addReportToMailBody(Message msg,File report) throws MessagingException{

		StringWriter writer = new StringWriter();
		try {
			IOUtils.copy(new FileInputStream(report), writer);
			msg.setContent(writer.toString(), "text/html");
		} catch (IOException e) {
			System.out.println("Not able to retrive  report file");
			e.printStackTrace();
		}
	}


	public static void sendReport(){
		
			String subject= "Soap UI Report";
					
			String message="Soap UI Report ";

			String sender="qe_automation@continuum.net";	
			String password="R1Soft*88";
			File soapReport=new File(absolutePath+"all-tests.html");
			String reciever="sneha.chemburkar@continuum.net";

			sendEmail(sender, password, reciever, subject, message, soapReport);

		
	}
}





