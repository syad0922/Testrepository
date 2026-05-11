package utils;

import java.io.File;
import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

public class EmailSender {
	
	public static void sendReport(String reportPath) {
		//emails information
		
		 final String senderEmail="surati.yadagiri@gmail.com";
		 final String appPassword="faxrmaxcsthwtldr";
		 final String recipientEmail="surati.yadagiri@gmail.com";
		 
		 //SMTP server properties
		 System.out.println("Mail information");
		 Properties prop=new Properties();
		 prop.put("mail.smtp.auth", "true");
		 prop.put("mail.smtp.host", "smtp.gmail.com");
		 prop.put("mail.smtp.starttls.enable", "true");
		 prop.put("mail.smtp.port", "587");
		 
		 //Create sessio with authentiction
		 Session session=Session.getInstance(prop,new Authenticator() {
			 protected PasswordAuthentication getPasswordAuthentication() {
				 return new PasswordAuthentication(senderEmail,appPassword);
			 }
		 });
		 session.setDebug(true);
		 //Continue to next page
		 
		 try {
			 //Create EMAIl message
			 Message message=new MimeMessage(session);
			 message.setFrom(new InternetAddress(senderEmail));
			 message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(recipientEmail));
			 message.setSubject("Test email from automation project");
			 //message.setText("Hello \n This is a test email \n Regards \n QA Team");
			 
			 //Email body part
			 MimeBodyPart textPart=new MimeBodyPart();
			 textPart.setText("Hello,this is testing for email with attachement");
			 
			 //Attachmentpart
			 MimeBodyPart attachpart=new MimeBodyPart();
			// String filepath=System.getProperty("user.dir")+"/reports/ExtentReport.html";
			 attachpart.attachFile(new File(reportPath));
			 
			 //combile email body and attachment
			 MimeMultipart multipart=new MimeMultipart();
			 multipart.addBodyPart(textPart);
			 multipart.addBodyPart(attachpart);
			 message.setContent(multipart);
			 
			//send Email
			 Transport.send(message);
			 System.out.println("Mail sent successfully");
		 }
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}

/*public static void main(String[] args) {
		//emails information
		
		 final String senderEmail="surati.yadagiri@gmail.com";
		 final String appPassword="faxrmaxcsthwtldr";
		 final String recipientEmail="surati.yadagiri@gmail.com";
		 
		 //SMTP server properties
		 System.out.println("Mail information");
		 Properties prop=new Properties();
		 prop.put("mail.smtp.auth", "true");
		 prop.put("mail.smtp.host", "smtp.gmail.com");
		 prop.put("mail.smtp.starttls.enable", "true");
		 prop.put("mail.smtp.port", "587");
		 
		 //Create sessio with authentiction
		 Session session=Session.getInstance(prop,new Authenticator() {
			 protected PasswordAuthentication getPasswordAuthentication() {
				 return new PasswordAuthentication(senderEmail,appPassword);
			 }
		 });
		 session.setDebug(true);
		 //Continue to next page
		 
		 try {
			 //Create EMAIl message
			 Message message=new MimeMessage(session);
			 message.setFrom(new InternetAddress(senderEmail));
			 message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(recipientEmail));
			 message.setSubject("Test email from automation project");
			 //message.setText("Hello \n This is a test email \n Regards \n QA Team");
			 
			 //Email body part
			 MimeBodyPart textPart=new MimeBodyPart();
			 textPart.setText("Hello,this is testing for email with attachement");
			 
			 //Attachmentpart
			 MimeBodyPart attachpart=new MimeBodyPart();
			 String filepath=System.getProperty("user.dir")+"/reports/ExtentReport.html";
			 attachpart.attachFile(new File(filepath));
			 
			 //combile email body and attachment
			 MimeMultipart multipart=new MimeMultipart();
			 multipart.addBodyPart(textPart);
			 multipart.addBodyPart(attachpart);
			 message.setContent(multipart);
			 
			//send Email
			 Transport.send(message);
			 System.out.println("Mail sent successfully");
		 }
		catch(Exception e) {
			e.printStackTrace();
		}
	}*/
