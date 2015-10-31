package org.sumaciudadana.affidavit.mb;

import java.io.File;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.UploadedFile;
import org.sumaciudadana.affidavit.entity.Jurisdiction;
import org.sumaciudadana.affidavit.entity.Position;
import org.sumaciudadana.affidavit.service.MailService;
import org.sumaciudadana.affidavit.service.PositionService;
import org.sumaciudadana.affidavit.util.FileUtils;
import org.sumaciudadana.affidavit.util.StringUtils;

@ManagedBean(name = "participacionBean")
@RequestScoped
public class ParticipacionMB {
	
	@ManagedProperty(value = "#{positionService}")
	PositionService positionService;
	@ManagedProperty(value = "#{mailService}")
	MailService mailService;

	// fields
	private List<Position> positions;

	// criteria
	private String servantComplaint;
	private String selectedPosition;
	
	//upload
	private UploadedFile file;

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public void setPositionService(PositionService positionService) {
		this.positionService = positionService;
	}

	public String getSelectedPosition() {
		return selectedPosition;
	}

	public void setSelectedPosition(String selectedPosition) {
		this.selectedPosition = selectedPosition;
	}

	public PositionService getPositionService() {
		return positionService;
	}

	public List<Position> getPositions() {
		return positions;
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
		positions = positionService.getPositions(new Jurisdiction());
	}

	/* ***METODOS*** */
	public void sendComplaint() {
		try {
			if (file!=null) {
				String fileToSendPath = FileUtils.copyFile(file.getFileName(), file.getInputstream()); 
				if (fileToSendPath!=null) {
					File fileToSend = new File(fileToSendPath);
					mailService.send("sumaciudadana.denuncias@gmail.com", StringUtils.formatMuni(selectedPosition), servantComplaint, fileToSend);
				}
			}else{
				mailService.send("sumaciudadana.denuncias@gmail.com", StringUtils.formatMuni(selectedPosition), servantComplaint);
			}
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Envio satisfactorio", "Gracias por su participación"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al enviar", "Intente nuevamente"));
			e.printStackTrace();
		}
	}
	
	
}
