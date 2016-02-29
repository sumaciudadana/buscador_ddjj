package org.sumaciudadana.affidavit.service;

import java.util.List;

import org.sumaciudadana.affidavit.entity.Affidavit;

public interface AffidavitService {

	public List<Affidavit> getAffidavitQuery(int pservant, int position,
			long minwealth, long maxwealth, int minperiod, int maxperiod);
	public List<Affidavit> getAffidavitQuery(int pservant);
	public List<Affidavit> getAffidavitByPosition(int idposition);

}
