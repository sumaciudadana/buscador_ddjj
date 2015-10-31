package org.sumaciudadana.affidavit.mb;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpSession;

import org.sumaciudadana.affidavit.entity.Affidavit;
import org.sumaciudadana.affidavit.entity.Belonging;
import org.sumaciudadana.affidavit.entity.Jurisdiction;
import org.sumaciudadana.affidavit.entity.Organization;
import org.sumaciudadana.affidavit.entity.Position;
import org.sumaciudadana.affidavit.entity.Pservant;
import org.sumaciudadana.affidavit.service.AffidavitService;
import org.sumaciudadana.affidavit.service.PServantService;
import org.sumaciudadana.affidavit.service.PositionService;
import org.sumaciudadana.affidavit.service.StatisticService;
import org.sumaciudadana.affidavit.util.AffidavitUtils;
import org.sumaciudadana.statistic.object.AffidavitFault;

@ManagedBean(name = "affidavitQuery")
@ViewScoped
//@RequestScoped
public class AffidavitQueryMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6685408698729844647L;

	private final static Logger LOGGER = Logger
			.getLogger(AffidavitQueryMB.class.getName());

	private static final String AFFIDAVIT_PAGE = "pages/searchAffidavit.xhtml";

	@ManagedProperty(value = "#{pservantService}")
	PServantService servantService;
	@ManagedProperty(value = "#{positionService}")
	PositionService positionService;
	@ManagedProperty(value = "#{affidavitService}")
	AffidavitService affidavitService;
	@ManagedProperty(value = "#{statisticService}")
	StatisticService statisticService;
	@ManagedProperty(value = "#{affidavitUilts}")
	AffidavitUtils affidavitUilts;

	// fields
	private List<Organization> organizations;
	private List<Jurisdiction> jurisdictions;
	private List<Position> positions;

	// Criteria
	private Organization selectedOrganization;
	private Jurisdiction selectedJurisdiction;
	private Position selectedPosition;
	private Pservant selectedServant;

	// results
	private List<Pservant> selectedServants;
	private List<Affidavit> affidavits;
	private String dataChart;

	// Statistics
	private AffidavitFault affiFault;
	private Affidavit incomeIncrement;
	private Affidavit belongIncrement;
	private Belonging maxBelong;

	// util
	private boolean showModal;

	/* GETTERS AND SETTERS */
	public boolean isShowModal() {
		return showModal;
	}

	public Position getSelectedPosition() {
		return selectedPosition;
	}

	public void setSelectedPosition(Position selectedPosition) {
		this.selectedPosition = selectedPosition;
	}

	public List<Position> getPositions() {
		return positionService.getPositions(selectedJurisdiction);
	}

	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}

	public List<Jurisdiction> getJurisdictions() {
		return positionService.getJurisdictions(selectedOrganization);
	}

	public void setJurisdictions(List<Jurisdiction> jurisdictions) {
		this.jurisdictions = jurisdictions;
	}

	public Organization getSelectedOrganization() {
		return selectedOrganization;
	}

	public void setSelectedOrganization(Organization selectedOrganization) {
		this.selectedOrganization = selectedOrganization;
	}

	public List<Organization> getOrganizations() {
		return organizations;
	}

	public void setOrganizations(List<Organization> organizations) {
		this.organizations = organizations;
	}

	public List<Pservant> getSelectedServants() {
		return selectedServants;
	}

	public void setSelectedServants(List<Pservant> selectedServants) {
		this.selectedServants = selectedServants;
	}

	public Pservant getSelectedServant() {
		return selectedServant;
	}

	public void setSelectedServant(Pservant selectedServant) {
		this.selectedServant = selectedServant;
	}

	public PServantService getServantService() {
		return servantService;
	}

	public void setServantService(PServantService servantService) {
		this.servantService = servantService;
	}

	public PositionService getPositionService() {
		return positionService;
	}

	public void setPositionService(PositionService positionService) {
		this.positionService = positionService;
	}

	public Jurisdiction getSelectedJurisdiction() {
		return selectedJurisdiction;
	}

	public void setSelectedJurisdiction(Jurisdiction selectedJurisdiction) {
		this.selectedJurisdiction = selectedJurisdiction;
	}

	public List<Affidavit> getAffidavits() {
		return affidavits;
	}

	public AffidavitService getAffidavitService() {
		return affidavitService;
	}

	public void setAffidavitService(AffidavitService affidavitService) {
		this.affidavitService = affidavitService;
	}

	public AffidavitFault getAffiFault() {
		return affiFault;
	}

	public Affidavit getIncomeIncrement() {
		return incomeIncrement;
	}

	public Affidavit getBelongIncrement() {
		return belongIncrement;
	}

	public Belonging getMaxBelong() {
		return maxBelong;
	}

	public StatisticService getStatisticService() {
		return statisticService;
	}

	public void setStatisticService(StatisticService statisticService) {
		this.statisticService = statisticService;
	}

	public String getDataChart() {
		return dataChart;
	}

	public AffidavitUtils getAffidavitUilts() {
		return affidavitUilts;
	}

	public void setAffidavitUilts(AffidavitUtils affidavitUilts) {
		this.affidavitUilts = affidavitUilts;
	}

	/* CONSTRUCTOR */
	public AffidavitQueryMB() {
		LOGGER.info("AffidavitQueryMB constructor method");
		selectedOrganization = new Organization();
		selectedJurisdiction = new Jurisdiction();
	}

	/* METODOS */

	@SuppressWarnings("unused")
	public void preRenderView() {
		LOGGER.info("preRenderView method");
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(true);
	}

	@PostConstruct
	public void init() {
		LOGGER.info("init method");
		try {
			organizations = positionService.getOrganization();

		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Error AffidavitQueryMB.init() method", e);
		}

	}

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

	/* Methods from search engine */
	public void organizationChangeListener(ValueChangeEvent event) {
		int newValue = (Integer) event.getNewValue();
		int oldValue = selectedOrganization.getIdOrganization();
		if (newValue != oldValue) {
			selectedJurisdiction = new Jurisdiction();
		}
	}

	public void jurisdictionChangeListener(ValueChangeEvent event) {
		int newValue = (Integer) event.getNewValue();
		int oldValue = selectedJurisdiction.getIdJurisdiction();
		if (newValue != oldValue) {
			selectedPosition = new Position();
		}
	}

	public String searchByServant() {
		int pservant = selectedServant.getIdpservant();
		this.affidavits = affidavitService.getAffidavitQuery(pservant);
		this.dataChart = affidavitUilts.getChartData(affidavits);
		boolean stats = showStatistics();
		return AFFIDAVIT_PAGE;
	}

	/* Methods from statistics */

	private boolean showStatistics() {
		this.affiFault = affidavitFailures();
		this.incomeIncrement = incomeIncrement();
		this.belongIncrement = patrimonyIncrement();
		//this.maxBelong = maxBelongs();
		this.maxBelong = new Belonging();
		return true;
	}

	private AffidavitFault affidavitFailures() {
		DecimalFormat df = new DecimalFormat("#.##");
		AffidavitFault fault = new AffidavitFault(selectedServant);

		for (Affidavit affidavit : affidavits) {
			fault.setValid(affidavit.getAffiValid());
		}

		int valid = fault.getValidCounter();
		int invalid = fault.getInvalidCounter();
		int total = valid + invalid;
		double porcent = 100D / total * invalid;

		fault.setFaultPorcent(porcent);
		fault.setFaultPorcentMsg(df.format(porcent));

		return fault;
	}

	private Affidavit incomeIncrement() {
		return statisticService.getIncomeIncrement(selectedServant.getIdpservant(), 0);
	}

	private Affidavit patrimonyIncrement() {
		return statisticService.getPatrimonyIncrement(
				selectedServant.getIdpservant(), 0);
	}

	private Belonging maxBelongs() {
		return statisticService.getMaxBelong(selectedServant);
	}
}
