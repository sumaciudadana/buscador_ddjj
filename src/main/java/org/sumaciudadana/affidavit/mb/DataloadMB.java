package org.sumaciudadana.affidavit.mb;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.model.UploadedFile;
import org.sumaciudadana.affidavit.entity.Filelog;
import org.sumaciudadana.affidavit.entity.User;
import org.sumaciudadana.affidavit.service.FilelogService;
import org.sumaciudadana.affidavit.service.LoginService;

@ManagedBean(name = "dataloadMB")
@ViewScoped
public class DataloadMB {

	private final static Logger LOGGER = Logger.getLogger(DataloadMB.class
			.getName());

	private static final String EXCEL_CONTENT_TYPE = "application/vnd.ms-excel";
	private static final String FILE_PATH = "/home/ubuntu/suma/files/";

	@ManagedProperty(value = "#{filelogService}")
	private FilelogService filelogService;

	@ManagedProperty(value = "#{loginService}")
	private LoginService loginService;

	/* Form fields */
	private UploadedFile file;
	private String username;
	private int restoreFileId;

	/* table */
	private List<Filelog> files;

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public void setFilelogService(FilelogService filelogService) {
		this.filelogService = filelogService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	public List<Filelog> getFiles() {
		return files;
	}

	public void setRestoreFileId(int restoreFileId) {
		this.restoreFileId = restoreFileId;
	}

	public int getRestoreFileId() {
		return restoreFileId;
	}

	@PostConstruct
	public void init() {
		loadRecord();
	}

	public void upload() {
		boolean errorFlag = false;
		if (file != null) {
			String contentType = file.getContentType();
			String fileName = file.getFileName();
			String fileUniqueName = String.valueOf(new Date().getTime())
					.concat(".xls");
			OutputStream output = null;

			if (EXCEL_CONTENT_TYPE.equals(contentType)) {

				// TODO ADD WAITING FIGURE

				// Validate user
				HttpSession session = (HttpSession) FacesContext
						.getCurrentInstance().getExternalContext()
						.getSession(true);
				this.username = (String) session.getAttribute("username");
				User user = loginService.userExists(this.username);

				if (user == null) {
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"Error", "Usuario invalido"));
					return;
				}

				LOGGER.info("usuario valido");

				try {
					// TODO get path from properties
					// TODO get pentaho path from properties
					output = new FileOutputStream(
							FILE_PATH.concat(fileUniqueName));
					IOUtils.copy(file.getInputstream(), output);
					IOUtils.closeQuietly(output);
					LOGGER.info("archivo copiado"
							+ FILE_PATH.concat(fileUniqueName));
				} catch (IOException e) {
					LOGGER.log(Level.SEVERE, "Error saving file " + fileName, e);
				}

				try {
					// TODO CHECK WHEN PENTAHO WORKS FINE
					// TODO CHECK WHEN ERROR IS THROWN
					String error = filelogService.callPentaho(FILE_PATH
							.concat(fileUniqueName));
					if (error.length() != 0) {
						LOGGER.log(Level.SEVERE, "Error loading file "
								+ fileName, error);
						throw new Exception("Error cargando archivo");
					}

					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_INFO, "",
									"Carga satisfactoria"));

					filelogService.saveFile(fileName, fileUniqueName, user);

				} catch (Exception e) {
					errorFlag = true;
					FacesContext.getCurrentInstance().addMessage(
							null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR,
									"Error", e.getMessage()));
				}

				// Remove the file with errors
				if (errorFlag) {
					try {
						FileUtils.deleteDirectory(new File(FILE_PATH
								+ "fileUniqueName"));
					} catch (IOException e) {
						LOGGER.log(Level.SEVERE, "error deleting file", e);
					}
				}

				// TODO REMOVE WAITING FIGURE

			} else {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
								"formato de archivo incorrecto"));
			}
		}
		loadRecord();
	}

	private void loadRecord() {
		try {
			files = filelogService.loadFiles();
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Error loading file record", e);
		}
	}

	public String restoreFile() {
		int id = restoreFileId;
		Filelog file = null;
		try {
			file = filelogService.getFileLog(id);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Error loading file with id " + id, e);
		}

		if (file != null) {
			String fileUniqueName = file.getCurrentName();
			String fileName = file.getNameOriginal();
			String error;
			try {
				error = filelogService.callPentaho(FILE_PATH
						.concat(fileUniqueName));
				if (error.length() != 0) {
					throw new Exception(error);
				}
				
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "",
								"Carga satisfactoria"));
			} catch (Exception e) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al cargar",
								e.getMessage()));
				LOGGER.log(Level.SEVERE, "Error loading file " + fileName,e);
			}
		}
		return null;
	}
}
