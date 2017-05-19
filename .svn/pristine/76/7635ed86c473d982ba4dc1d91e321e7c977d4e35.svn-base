package com.generic;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * @Description	: this class is used to send email
 * @Author		: 
 * @Modified By :  
 * @Author	    : Automation Tester (SQS)
 */
public class SendEmail  
{
	private String SMTP_AUTH_USER = "";
	private String SMTP_AUTH_PWD = "";
	public void sendEmail(String host, String port, String from,
							String to, String cc, String bcc, String subject, 
							String body,String userName,String password, 
							List<String> imageNames, List<File> attachment) 
	{
		try
		{
			// Get system properties
			Properties properties = System.getProperties();
			// Setup mail server
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.starttls.enable", "true");
			properties.put("mail.smtp.host", host);
			properties.put("mail.smtp.port", port);
			properties.put("mail.smtp.ssl.trust", host);
			
			// SQS mail details
			//String host = "SWEX004.resource.group.intl";
			//String host = "mail1.sqs.com";
			//String port = 443;
			
			SMTP_AUTH_USER = userName;
			SMTP_AUTH_PWD = password;
			// Get the default Session object.
			Authenticator auth = new SMTPAuthenticator();
			Session session = Session.getInstance(properties,auth);
			session.setDebug(true);

			MimeMessage message = new MimeMessage(session);
			// Set mail from adrress.
			message.setFrom(new InternetAddress(from));

			// Set mail to
			if(to!=null && !to.equals(""))
			{
				ArrayList<InternetAddress> internetToAddress =  new ArrayList<InternetAddress>();
				if(to.contains(";"))
				{
					String[] mailToList = to.split(";");
					for(String mailTo : mailToList)
					{
						internetToAddress.add(new InternetAddress(mailTo));
					}
				}
				else
					internetToAddress.add(new InternetAddress(to));

				InternetAddress[] addressTo = new InternetAddress[internetToAddress.size()];
				addressTo =	internetToAddress.toArray(addressTo);
				message.setRecipients(Message.RecipientType.TO, addressTo);
			}

			// Set mail cc
			if(cc!=null && !cc.equals(""))
			{
				ArrayList<InternetAddress> internetCcAddress =  new ArrayList<InternetAddress>();
				if(cc.contains(";"))
				{
					String[] mailCcList = cc.split(";");
					for(String mailCc : mailCcList)
					{
						internetCcAddress.add(new InternetAddress(mailCc));
					}
				}
				else
					internetCcAddress.add(new InternetAddress(cc));

				InternetAddress[] addressCc = new InternetAddress[internetCcAddress.size()];
				addressCc =	internetCcAddress.toArray(addressCc);
				message.setRecipients(Message.RecipientType.CC, addressCc);
			}

			// Set mail bcc
			if(bcc!=null && !bcc.equals(""))
			{
				ArrayList<InternetAddress> internetBccAddress =  new ArrayList<InternetAddress>();
				if(bcc.contains(";"))
				{
					String[] mailBccList = bcc.split(";");
					for(String mailBcc : mailBccList)
					{
						internetBccAddress.add(new InternetAddress(mailBcc));
					}
				}
				else
					internetBccAddress.add(new InternetAddress(bcc));

				InternetAddress[] addressBcc = new InternetAddress[internetBccAddress.size()];
				addressBcc = internetBccAddress.toArray(addressBcc);
				message.setRecipients(Message.RecipientType.BCC, addressBcc);
			}
			// Set subject
			if(subject!=null && !subject.equals(""))
				message.setSubject(subject);

			// Set message body
			if(body!=null && !body.equals(""))
				message.setText(body);
			// Create a multipart message

			MimeMultipart multipart = new MimeMultipart("related");

			// first part (the html)
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(body, "text/html");
			// add it
			multipart.addBodyPart(messageBodyPart);

			// Attachment
			if(imageNames!=null && !imageNames.equals(""))
			{
				for(String imageFilePath : imageNames)
				{
					messageBodyPart = new MimeBodyPart();
					DataSource image = new FileDataSource(new File(imageFilePath));
					messageBodyPart.setDataHandler(new DataHandler(image));
					messageBodyPart.setHeader("Content-ID", "<image>");
					// add image to the multipart
					multipart.addBodyPart(messageBodyPart);
				}
			}

			// Attachment
			if(attachment!=null && !attachment.equals(""))
			{
				for(File attachmentFilePath : attachment)
				{
					BodyPart attachmentBodyPart = new MimeBodyPart();
					DataSource source = new FileDataSource(attachmentFilePath);
					attachmentBodyPart.setDataHandler(new DataHandler(source));
					String  finalFilename = attachmentFilePath.getName();
					attachmentBodyPart.setFileName(finalFilename);
					multipart.addBodyPart(attachmentBodyPart);
				}
			}
			
			// Send the complete message parts
			message.setContent(multipart);
			Transport.send(message);
		}
		catch (MessagingException messagingException) 
		{
			messagingException.printStackTrace();
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
	}

	private class SMTPAuthenticator extends javax.mail.Authenticator
	{
		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(SMTP_AUTH_USER, SMTP_AUTH_PWD);
		}
	}
}
