package org.sumaciudadana.affidavit.DAO;

import java.util.List;

import org.sumaciudadana.affidavit.entity.Affidavit;

public interface AffidavitDAO {

	public List<Affidavit> getAffidavitQuery(int pservant, int position,
			long minwealth, long maxwealth, int minperiod, int maxperiod);
	
	public Integer getMaxYear(int pservant, String type);
	public Affidavit getMaxWealthAffi(int year, int idpservant);
	
	public Integer getMaxYearPat(int pservant, String type);
	public Affidavit getMaxPatrimonyhAffi(int year, int idpservant);
	
	public long getInvalidAffi(int idpservant);

	public List<Affidavit> getAffidavitQuery(int pservant);
	public List<Affidavit> getAffidavitByPosition(int idposition);

	public Affidavit getLastAffiByPresentation(String presentation, int pservant);

}
