package org.sumaciudadana.affidavit.service;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Transactional
@Service
public class MailServiceImpl implements MailService {

	private static final Log log = LogFactory.getLog(MailServiceImpl.class);

	/** wrapper de Spring sobre javax.mail */
	private JavaMailSenderImpl mailSender;

	public void setMailSender(JavaMailSenderImpl mailSender) {
		this.mailSender = mailSender;
	}

	/** correo electr�nico del remitente */
	private String from;

	public void setFrom(String from) {
		this.from = from;
	}

	public String getFrom() {
		return from;
	}

	private static final File[] NO_ATTACHMENTS = null;

	/** flag para indicar si est� activo el servicio */
	public boolean active = true;

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * env�o de email
	 * 
	 * @param to
	 *            correo electr�nico del destinatario
	 * @param subject
	 *            asunto del mensaje
	 * @param text
	 *            cuerpo del mensaje
	 */
	public void send(String to, String subject, String text) {
		send(to, subject, text, NO_ATTACHMENTS);
	}

	/**
	 * env�o de email con attachments
	 * 
	 * @param to
	 *            correo electr�nico del destinatario
	 * @param subject
	 *            asunto del mensaje
	 * @param text
	 *            cuerpo del mensaje
	 * @param attachments
	 *            ficheros que se anexar�n al mensaje
	 */
	public void send(String to, String subject, String text,
			File... attachments) {
		// chequeo de par�metros
		Assert.hasLength(to, "email 'to' needed");
		Assert.hasLength(subject, "email 'subject' needed");
		Assert.hasLength(text, "email 'text' needed");

		// asegurando la trazabilidad
		if (log.isDebugEnabled()) {
			final boolean usingPassword = !"".equals(mailSender.getPassword());
			log.debug("Sending email to: '" + to + "' [through host: '"
					+ mailSender.getHost() + ":" + mailSender.getPort()
					+ "', username: '" + mailSender.getUsername()
					+ "' usingPassword:" + usingPassword + "].");
			log.debug("isActive: " + active);
		}
		// el servicio esta activo?
		if (!active)
			return;
		
		// plantilla para el env�o de email  
        final MimeMessage message = mailSender.createMimeMessage();
        
        try {  
            // el flag a true indica que va a ser multipart  
            final MimeMessageHelper helper = new MimeMessageHelper(message, true);  
              
            // settings de los par�metros del env�o  
            helper.setTo(to);  
            helper.setSubject(subject);  
            helper.setFrom(getFrom());  
            helper.setText(text);  
  
            // adjuntando los ficheros  
            if (attachments != null) {  
                for (int i = 0; i < attachments.length; i++) {
                    FileSystemResource file = new FileSystemResource(attachments[i]);  
                    helper.addAttachment(attachments[i].getName(), file);
                    if (log.isDebugEnabled()) {
                        log.debug("File '" + file + "' attached.");  
                    }  
                }  
            }  
  
        } catch (MessagingException e) {  
            new RuntimeException(e);  
        }  
          
        // el env�o  
        this.mailSender.send(message);
        
	}

}
