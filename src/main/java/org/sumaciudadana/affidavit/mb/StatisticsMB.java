package org.sumaciudadana.affidavit.mb;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.primefaces.model.chart.PieChartModel;
import org.sumaciudadana.affidavit.entity.Affidavit;
import org.sumaciudadana.affidavit.entity.Belonging;
import org.sumaciudadana.affidavit.entity.Pservant;
import org.sumaciudadana.affidavit.service.AffidavitService;
import org.sumaciudadana.affidavit.service.BelongingService;
import org.sumaciudadana.affidavit.service.PServantService;
import org.sumaciudadana.affidavit.service.StatisticService;
import org.sumaciudadana.statistic.object.AffidavitFault;

@ManagedBean(name = "statistics")
@SessionScoped
public class StatisticsMB {

	private static final String APP_PROPERTIES = "/app.properties";
	private static final String MSG_PROPERTIES = "/messages.properties";
	

	// Services
	@ManagedProperty(value = "#{pservantService}")
	PServantService servantService;
	@ManagedProperty(value = "#{affidavitService}")
	AffidavitService affidavitService;
	@ManagedProperty(value = "#{statisticService}")
	StatisticService statisticService;
	@ManagedProperty(value = "#{belongingService}")
	BelongingService belongingService;

	// Resources
	private Properties appProp = new Properties();
	private Properties msgProp = new Properties();
	private String formPage;

	// Fields
	private Pservant selectedServant;
	private boolean checkAll;
	private int increment;
	private int resultAmount;

	// Results
	private List<AffidavitFault> affiFaultList;
	private List<Affidavit> detailAffis;
	private List<Affidavit> incomeIncrement;
	private List<Affidavit> belongIncrement;
	private List<Belonging> maxBelong;
	private List<Belonging> belList;

	// Result visibility
	boolean breaches;
	boolean salaryIncrement;
	boolean patrimonyIncrement;
	boolean wealth;
	boolean mapBelong;

	// Pie Chart
	private PieChartModel pieModel;

	@PostConstruct
	public void init() {

		try {
			appProp.load(this.getClass().getClassLoader()
					.getResourceAsStream(APP_PROPERTIES));
			msgProp.load(this.getClass().getClassLoader().getResourceAsStream(MSG_PROPERTIES));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Ensure that default is been set.
		this.formPage = appProp.getProperty("pages.dummy");

		// Hide all responses
		setResponseVisibility("");

		// Load chart
		getAffiFaults();
		
		// List all Belongings for the map
		belList = belongingService.getAllBelongings();
	}

	// Getter + setter.
	public String getFormPage() {
		return formPage;
	}

	public void setFormPage(String formPage) {
		this.formPage = formPage;
	}

	public PServantService getServantService() {
		return servantService;
	}

	public void setServantService(PServantService servantService) {
		this.servantService = servantService;
	}

	public StatisticService getStatisticService() {
		return statisticService;
	}

	public void setStatisticService(StatisticService statisticService) {
		this.statisticService = statisticService;
	}

	public BelongingService getBelongingService() {
		return belongingService;
	}

	public void setBelongingService(BelongingService belongingService) {
		this.belongingService = belongingService;
	}

	public Pservant getSelectedServant() {
		return selectedServant;
	}

	public void setSelectedServant(Pservant selectedServant) {
		this.selectedServant = selectedServant;
	}

	public boolean isCheckAll() {
		return checkAll;
	}

	public void setCheckAll(boolean checkAll) {
		this.checkAll = checkAll;
	}

	public AffidavitService getAffidavitService() {
		return affidavitService;
	}

	public void setAffidavitService(AffidavitService affidavitService) {
		this.affidavitService = affidavitService;
	}

	public List<AffidavitFault> getAffiFaultList() {
		return affiFaultList;
	}

	public List<Affidavit> getDetailAffis() {
		return detailAffis;
	}

	public int getIncrement() {
		return increment;
	}

	public void setIncrement(int increment) {
		this.increment = increment;
	}

	public List<Affidavit> getIncomeIncrement() {
		return incomeIncrement;
	}

	public boolean isBreaches() {
		return breaches;
	}

	public boolean isSalaryIncrement() {
		return salaryIncrement;
	}

	public boolean isPatrimonyIncrement() {
		return patrimonyIncrement;
	}

	public List<Affidavit> getBelongIncrement() {
		return belongIncrement;
	}

	public int getResultAmount() {
		return resultAmount;
	}

	public void setResultAmount(int resultAmount) {
		this.resultAmount = resultAmount;
	}

	public boolean isWealth() {
		return wealth;
	}

	public List<Belonging> getMaxBelong() {
		return maxBelong;
	}

	public PieChartModel getPieModel() {
		return pieModel;
	}

	public boolean isMapBelong() {
		return mapBelong;
	}

	public List<Belonging> getBelList() {
		return belList;
	}

	/**
	 * Set all response divs to hide
	 * 
	 * @param formPage
	 */
	public void setResponseVisibility(String formPage) {
		breaches = false;
		salaryIncrement = false;
		patrimonyIncrement = false;
		wealth = false;
		mapBelong = false;

		if (formPage.equals("income")) {
			salaryIncrement = true;
		} else if (formPage.equals("affidavit")) {
			breaches = true;
		} else if (formPage.equals("patrimony")) {
			patrimonyIncrement = true;
		} else if (formPage.equals("wealth")) {
			wealth = true;
		} else if (formPage.equals("mapBelong")) {
			mapBelong = true;
		}
	}

	/**
	 * Auto complete servant name
	 * 
	 * @param query
	 * @return
	 */
	public List<Pservant> completeServant(String query) {
		List<Pservant> suggestions = new ArrayList<Pservant>();
		List<Pservant> matches = servantService.getAllPservants();

		for (Pservant pservant : matches) {
			if (pservant.getSerFirstsurname().toUpperCase()
					.startsWith(query.toUpperCase())) {
				suggestions.add(pservant);
			}
		}

		return suggestions;
	}

	/**
	 * Process the statistic request
	 */
	public void showStats() {

		if (checkAll == true) {
			selectedServant = null;
		}

		if (formPage.equals("affidavit")) {
			affidavitFailures(selectedServant);
		} else if (formPage.equals("patrimony")) {
			patrimonyIncrement(selectedServant);
		} else if (formPage.equals("income")) {
			incomeIncrement(selectedServant);
		} else if (formPage.equals("wealth")) {
			if (checkAll || selectedServant != null) {
				maxBelongs(checkAll == true ? 20 : 0, selectedServant);
			} else {
				wealth = false;
			}

		}

		setResponseVisibility(formPage);
	}

	/**
	 * creates a chart with the affidavit faults
	 */
	private void getAffiFaults() {
		Map<String, Number> chartInfo = statisticService
				.getUnprovidedAffidavit();
		pieModel = new PieChartModel();
		pieModel.setLegendPosition("e");
		pieModel.setFill(false);
		pieModel.setTitle(msgProp.getProperty("chart_faults_title"));
		pieModel.setDiameter(150);
		pieModel.setSliceMargin(5);
		pieModel.setShowDataLabels(true);
		pieModel.setData(chartInfo);
	}

	private void maxBelongs(int amount, Pservant servant) {
		maxBelong = statisticService.getMaxBelongs(amount, servant);
	}

	private void patrimonyIncrement(Pservant selected) {
		int servantId = selected != null ? selected.getIdpservant() : 0;
		if (servantId==0) {
			belongIncrement = statisticService.getPatrimonyIncrement(increment);
		}else{
			belongIncrement = new ArrayList<Affidavit>();
			belongIncrement.add(statisticService.getPatrimonyIncrement(servantId,increment));
		}
		
	}

	private void incomeIncrement(Pservant selected) {
		int servantId = selected != null ? selected.getIdpservant() : 0;
		if (servantId==0) {
			incomeIncrement = statisticService.getIncomeIncrement(increment);
		}else{
			incomeIncrement = new ArrayList<Affidavit>();
			incomeIncrement.add(statisticService.getIncomeIncrement(servantId,increment));
		}
	}

	/***
	 * percent of not presented affidavits by servant
	 * 
	 * @param selected
	 */
	private void affidavitFailures(Pservant selected) {
		Map<Integer, AffidavitFault> affiFaultMap = new HashMap<Integer, AffidavitFault>();
		DecimalFormat df = new DecimalFormat("#.##");

		List<Affidavit> resp = affidavitService.getAffidavitQuery(
				selected != null ? selected.getIdpservant() : 0, 0, 0, 0, 0, 0);

		for (Affidavit affidavit : resp) {
			Integer id = affidavit.getPservant().getIdpservant();
			AffidavitFault fault = (AffidavitFault) affiFaultMap.get(id);

			if (fault == null) {
				fault = new AffidavitFault(affidavit.getPservant());
			}

			fault.setValid(affidavit.getAffiValid());
			affiFaultMap.put(id, fault);
		}

		affiFaultList = new ArrayList<AffidavitFault>(affiFaultMap.values());
		for (AffidavitFault fault : affiFaultList) {
			int valid = fault.getValidCounter();
			int invalid = fault.getInvalidCounter();
			int total = valid + invalid;
			double porcent = 100D / total * invalid;
			fault.setFaultPorcent(porcent);
			fault.setFaultPorcentMsg(df.format(porcent));
		}
	}

	/**
	 * get servant affidavit detail
	 * 
	 * @param pservant
	 */
	public void viewDetail(int pservant) {
		detailAffis = affidavitService.getAffidavitQuery(pservant, 0, 0, 0, 0,
				0);
	}

	public void valueChangeMethod(ValueChangeEvent vce) {
		setResponseVisibility("");
	}

}
