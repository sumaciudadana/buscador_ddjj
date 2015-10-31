package org.sumaciudadana.affidavit.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;
import java.util.SortedSet;
import java.util.TreeSet;

import org.sumaciudadana.affidavit.entity.Affidavit;

public class AffidavitUtils {

	private static final String PROPERTIES = "app.properties";
	private Properties prop = new Properties();
	private int currentYear;
	private int initYear;

	public AffidavitUtils() {
		try {
			InputStream input = this.getClass().getClassLoader()
					.getResourceAsStream(PROPERTIES);
			prop.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}

		initYear = Integer.valueOf(prop.getProperty("util.baseYear"));
	}

	public ArrayList<String> listOfYears() {
		ArrayList<String> years = new ArrayList<String>();
		Calendar calendar = new GregorianCalendar();
		currentYear = calendar.get(Calendar.YEAR);
		for (int i = initYear; i < currentYear + 1; i++) {
			years.add(String.valueOf(i));
		}

		return years;
	}

	public String getChartData(List<Affidavit> data) {
		List<String> headers = new ArrayList<String>();
		List<String[]> dataChart = new ArrayList<String[]>();

		SortedSet<String> cgr = new TreeSet<String>();
		SortedSet<String> undefined = new TreeSet<String>();

		if (data == null || data.size() == 0) {
			return null;
		}

		for (Affidavit affidavit : data) {
			String type = affidavit.getAffiType() == null ? "" : affidavit
					.getAffiType();
			if (type.equals("CGR") || type.startsWith("MUN")) {
				cgr.add("Contraloría General de la República y/o Municipalidad");
			} else {
				undefined.add("No definido");
			}
		}

		headers.add("'Year'");
		headers.addAll(cgr);
		headers.addAll(undefined);

		dataChart.add(headers.toArray(new String[headers.size()]));

		int pastYear = 0;
		for (Affidavit affidavit : data) {
			Integer year = affidavit.getAffiYear();
			String[] row;
			String presentation = "";

			String type = affidavit.getAffiType() == null ? "" : affidavit
					.getAffiType();

			if (type.equals("CGR") || type.startsWith("MUN")) {
				presentation = "Contraloría General de la República y/o Municipalidad";
			} else {
				presentation = "No definido";
			}

			row = new String[headers.size()];
			row[0] = year.toString();
			row[headers.indexOf(presentation)] = affidavit.getAffiTotalWealth()
					.toString();
			dataChart.add(row);

		}

		String result = Arrays.deepToString(dataChart.toArray());
		result = result.replace("], [", "];[");
		result = result.substring(1);
		result = result.substring(0, result.length() - 1);
		result = result.replace("[", "").replace("]", "");

		return result;
	}
}
