package org.sumaciudadana.affidavit.mb;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.UploadedFile;
import org.sumaciudadana.affidavit.entity.Pservant;
import org.sumaciudadana.affidavit.service.MailService;
import org.sumaciudadana.affidavit.service.PServantService;
import org.sumaciudadana.affidavit.util.FileUtils;

@ManagedBean(name = "participacionBean")
@RequestScoped
public class ParticipacionMB {

	private final static Logger LOGGER = Logger.getLogger(ParticipacionMB.class
			.getName());

	@ManagedProperty(value = "#{mailService}")
	MailService mailService;
	@ManagedProperty(value = "#{pservantService}")
	PServantService servantService;

	// fields
	@ManagedProperty(value = "#{param.selectedPservant}")
	private int selectedPservant;
	private Pservant selectedPservantObject;

	// criteria
	private String servantComplaint;

	// upload
	private UploadedFile file;

	// GETTERS AND SETTERS

	public int getSelectedPservant() {
		return selectedPservant;
	}

	public PServantService getServantService() {
		return servantService;
	}

	public void setServantService(PServantService servantService) {
		this.servantService = servantService;
	}

	public Pservant getSelectedPservantObject() {
		return selectedPservantObject;
	}

	public void setSelectedPservantObject(Pservant selectedPservantObject) {
		this.selectedPservantObject = selectedPservantObject;
	}

	public void setSelectedPservant(int selectedPservant) {
		this.selectedPservant = selectedPservant;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public String getServantComplaint() {
		return servantComplaint;
	}

	public void setServantComplaint(String servantComplaint) {
		this.servantComplaint = servantComplaint;
	}

	public MailService getMailService() {
		return mailService;
	}

	public void setMailService(MailService mailService) {
		this.mailService = mailService;
	}

	/* ***CONSTRUCTOR*** */
	public ParticipacionMB() {
	}

	@PostConstruct
	public void init() {
		this.selectedPservantObject = servantService.getServantById(selectedPservant);
	}

	/* ***METODOS*** */
	public void sendComplaint() {
		try {
			if (file != null) {
				String fileToSendPath = FileUtils.copyFile(file.getFileName(),file.getInputstream());
				if (fileToSendPath != null) {
					File fileToSend = new File(fileToSendPath);
					mailService.send("sumaciudadana.denuncias@gmail.com", selectedPservantObject.getSerFullName(),servantComplaint, fileToSend);
				}
			} else {
				mailService.send("sumaciudadana.denuncias@gmail.com", selectedPservantObject.getSerFullName(), servantComplaint);
			}
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Envio satisfactorio","Gracias por su participación"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al enviar", "Intente nuevamente"));
			LOGGER.log(Level.SEVERE, "Error sending complaint",e);
		}
	}

}
