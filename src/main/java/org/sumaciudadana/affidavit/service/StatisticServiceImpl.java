package org.sumaciudadana.affidavit.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.sumaciudadana.affidavit.DAO.AffidavitDAO;
import org.sumaciudadana.affidavit.DAO.BelongingDAO;
import org.sumaciudadana.affidavit.DAO.PServantDAO;
import org.sumaciudadana.affidavit.entity.Affidavit;
import org.sumaciudadana.affidavit.entity.Belonging;
import org.sumaciudadana.affidavit.entity.Pservant;

@Transactional
@Service
public class StatisticServiceImpl implements StatisticService {

	private static final String ERROR_CALCULO = "No se puede calcular";

	@Autowired
	AffidavitDAO affidavitDAO;
	@Autowired
	PServantDAO pServantDAO;
	@Autowired
	BelongingDAO belongingDAO;

	/**
	 * Calculate the income increment between the first and last year
	 */
	@Override
	public List<Affidavit> getIncomeIncrement(int increment) {
		List<Affidavit> results = new ArrayList<Affidavit>();
		List<Pservant> servants = pServantDAO.getAllPservants();
		for (Pservant pservant : servants) {
			Affidavit partialResult = incomeIncrement(pservant.getIdpservant(),
					increment);
			if (partialResult != null) {
				results.add(partialResult);
			}
		}
		return results;
	}

	public Affidavit getIncomeIncrement(int serId, int increment) {
		Affidavit singleResult = incomeIncrement(serId, increment);
		return singleResult;
	}

	/**
	 * Calculate the patrimony increment between the first and last year
	 */
	public List<Affidavit> getPatrimonyIncrement(int increment) {
		List<Affidavit> results = new ArrayList<Affidavit>();
		List<Pservant> servants = pServantDAO.getAllPservants();
		for (Pservant pservant : servants) {
			Affidavit partialResult = patrimonyIncrement(
					pservant.getIdpservant(), increment);
			if (partialResult != null) {
				if (!partialResult.getPatrimonyIncrement()
						.equals(ERROR_CALCULO)) {
					results.add(partialResult);
				}
			}
		}
		return results;
	}

	public Affidavit getPatrimonyIncrement(int serId, int increment) {
		Affidavit singleResult = patrimonyIncrement(serId, increment);
		return singleResult;
	}

	private Affidavit incomeIncrement(int serId, int limit) {
		Double minValue = 0d;
		Double maxValue = 0d;
		Integer minYear = 0;
		Integer maxYear = 0;
		Affidavit temp = new Affidavit();
		Double increment = 0D;

		// Get servant
		Pservant ser = pServantDAO.getServantById(serId);
		temp.setPservant(ser);

		// Get max and min year
		try {
			minYear = affidavitDAO.getMaxYear(serId, "min");
			maxYear = affidavitDAO.getMaxYear(serId, "max");
		} catch (Exception e) {
			temp.setIncomeIncrement("Error al calcular");
			e.printStackTrace();
			return temp;
		}

		// No matching affidavit
		if (minYear == null || maxYear == null) {
			temp.setIncomeIncrement(ERROR_CALCULO);
			return temp;
		}

		// Calculate increment
		if (minYear != 0 || maxYear != 0) {
			Affidavit minAffi = affidavitDAO.getMaxWealthAffi(minYear, serId);
			Affidavit maxAffi = affidavitDAO.getMaxWealthAffi(maxYear, serId);

			minValue = Double.valueOf(minAffi.getAffiTotalMonthWealth());
			maxValue = Double.valueOf(maxAffi.getAffiTotalMonthWealth());

			increment = (maxValue - minValue) / minValue * 100;

			if (limit == 0 || increment >= limit) {
				double salInc = Math.round(increment * 100.0) / 100.0;
				temp.setIncomeIncrement(String.valueOf(salInc) + "%");
				temp.setSalIncAmount(salInc);
				return temp;
			}

		}

		// No matching case
		return null;
	}

	private Affidavit patrimonyIncrement(int serId, int limit) {
		Double minValue = 0d;
		Double maxValue = 0d;
		Integer minYear = 0;
		Integer maxYear = 0;
		Affidavit temp = new Affidavit();
		Double increment = 0D;

		// Get servant
		Pservant ser = pServantDAO.getServantById(serId);
		temp.setPservant(ser);

		// Get max and min year
		try {
			minYear = affidavitDAO.getMaxYearPat(serId, "min");
			maxYear = affidavitDAO.getMaxYearPat(serId, "max");
		} catch (Exception e) {
			temp.setPatrimonyIncrement("Error al calcular");
			e.printStackTrace();
			return temp;
		}

		// No matching affidavit
		if (minYear == null || maxYear == null) {
			temp.setPatrimonyIncrement("No se puede calcular");
			return temp;
		}

		// Calculate increment
		if (minYear != 0 || maxYear != 0) {
			Affidavit minAffi = affidavitDAO.getMaxPatrimonyhAffi(minYear,
					serId);
			Affidavit maxAffi = affidavitDAO.getMaxPatrimonyhAffi(maxYear,
					serId);

			minValue = Double.valueOf(minAffi.getAffiTotalWealth());
			maxValue = Double.valueOf(maxAffi.getAffiTotalWealth());

			increment = (maxValue - minValue) / minValue * 100;

			if (limit == 0 || increment >= limit) {
				double resp = Math.round(increment * 100.0) / 100.0;
				temp.setPatrimonyIncrement(String.valueOf(resp) + " %");
				temp.setPatIncAmount(resp);
				return temp;
			}
		}

		// No matching case
		return null;
	}

	public List<Belonging> getMaxBelongs(int amount, Pservant pservant) {
		List<Belonging> belongs = belongingDAO.getMaxBelong(amount,
				pservant != null ? pservant.getIdpservant() : 0);
		return belongs;
	}
	
	public Belonging getMaxBelong(Pservant pservant) {
		Belonging belong = belongingDAO.getMaxBelong(pservant != null ? pservant.getIdpservant() : 0);
		return belong;
	}

	/**
	 * get the amount of servants who did not presented 1,2,3,4,5+ affidavits
	 * 
	 */
	public Map<String, Number> getUnprovidedAffidavit() {
		Long amount;
		Map<String, Number> chartMap = new TreeMap<String, Number>();

		// list of public servants
		List<Pservant> servants = pServantDAO.getAllPservants();

		for (Pservant pservant : servants) {
			amount = affidavitDAO.getInvalidAffi(pservant.getIdpservant());
			if (amount == 1) {
				Integer counter = (Integer) (chartMap.get(amount
						+ " declaración jurada no presentada") != null ? chartMap
						.get(amount + " declaración jurada no presentada") : 0);
				chartMap.put(amount.intValue()
						+ " declaración jurada no presentada", counter + 1);
			} else if ((amount == 0) || (amount > 1 && amount < 5)) {
				Integer counter = (Integer) (chartMap.get(amount
						+ " declaraciones juradas no presentada") != null ? chartMap
						.get(amount + " declaraciones juradas no presentada")
						: 0);
				chartMap.put(amount.intValue()
						+ " declaraciones juradas no presentada", counter + 1);
			} else {
				Integer counter = (Integer) (chartMap.get(5 + " o más") != null ? chartMap
						.get(5 + " o más") : 0);
				chartMap.put(5 + " o más", counter + 1);
			}
		}
		return chartMap;
	}

}
