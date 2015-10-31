package org.sumaciudadana.statistic.object;

import org.sumaciudadana.affidavit.entity.Pservant;

public class AffidavitFault {

	private int idPservant;
	private String namePservant;
	private int validCounter;
	private int invalidCounter;
	private double faultPorcent;
	private String faultPorcentMsg;

	public AffidavitFault(Pservant pservant) {
		this.idPservant = pservant.getIdpservant();
		this.namePservant = pservant.getSerFullName();
		this.validCounter = 0;
		this.invalidCounter = 0;
	}

	public void setIdPservant(int idPservant) {
		this.idPservant = idPservant;
	}

	public String getNamePservant() {
		return namePservant;
	}

	public int getIdPservant() {
		return idPservant;
	}

	public void setNamePservant(String namePservant) {
		this.namePservant = namePservant;
	}

	public int getValidCounter() {
		return validCounter;
	}

	public int getInvalidCounter() {
		return invalidCounter;
	}

	public void setValid(int valid) {
		if (valid == 1) {
			++validCounter;
		} else if (valid == 0) {
			++invalidCounter;
		}
	}

	public double getFaultPorcent() {
		return faultPorcent;
	}

	public void setFaultPorcent(double faultPorcent) {
		this.faultPorcent = faultPorcent;
	}

	public String getFaultPorcentMsg() {
		return faultPorcentMsg;
	}

	public void setFaultPorcentMsg(String faultPorcentMsg) {
		this.faultPorcentMsg = faultPorcentMsg;
	}

}
