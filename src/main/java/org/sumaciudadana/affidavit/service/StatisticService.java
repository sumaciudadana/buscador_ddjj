package org.sumaciudadana.affidavit.service;

import java.util.List;
import java.util.Map;

import org.sumaciudadana.affidavit.entity.Affidavit;
import org.sumaciudadana.affidavit.entity.Belonging;
import org.sumaciudadana.affidavit.entity.Pservant;

public interface StatisticService {

	public List<Affidavit> getIncomeIncrement(int increment);
	public Affidavit getIncomeIncrement(int serId, int increment);
	public List<Affidavit> getPatrimonyIncrement(int increment);
	public Affidavit getPatrimonyIncrement(int serId, int increment);
	public List<Belonging> getMaxBelongs(int amount, Pservant servant);
	public Belonging getMaxBelong(Pservant pservant);
	public Map<String, Number> getUnprovidedAffidavit();

}

