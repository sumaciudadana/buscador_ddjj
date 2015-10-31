package org.sumaciudadana.affidavit.faces.object;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.springframework.web.jsf.FacesContextUtils;
import org.sumaciudadana.affidavit.entity.Pservant;
import org.sumaciudadana.affidavit.service.PServantService;

@FacesConverter("pservantConverter")
public class PServantConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		PServantService pservantService = (PServantService) FacesContextUtils.getWebApplicationContext(arg0).getBean("pservantService");
//		String[] name = arg2.split(",");
		//Pservant result = pservantService.getServantByFullName(name[1], name[0].split(" ")[0]);
		Pservant result = null;
		try {
			result = pservantService.getServantById(Integer.parseInt(arg2));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		if (result!=null) {
			return result; 
		}else{
			return new Pservant();
		}
		
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		Pservant pservant = new Pservant();
		pservant = (Pservant) arg2;
		if (pservant.getIdpservant() != null) {
			return String.valueOf(pservant.getIdpservant());
		} else {
			return "";
		}
	}

}
