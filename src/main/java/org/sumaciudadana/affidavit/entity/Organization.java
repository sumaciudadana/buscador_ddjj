package org.sumaciudadana.affidavit.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "organization", catalog = "sumaciudadana")
public class Organization {

	private int idOrganization;
	private String orgName;
	private Set<Jurisdiction> jurisdictions = new HashSet<Jurisdiction>(0);

	public Organization() {
	}

	public Organization(int idOrganization, String orgName,
			Set<Jurisdiction> jurisdictions) {
		this.idOrganization = idOrganization;
		this.orgName = orgName;
		this.jurisdictions = jurisdictions;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idorganization", unique = true, nullable = false)
	public int getIdOrganization() {
		return idOrganization;
	}

	public void setIdOrganization(int idOrganization) {
		this.idOrganization = idOrganization;
	}


	@Column(name = "org_name", length = 100)
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "organization")
	public Set<Jurisdiction> getJurisdictions() {
		return jurisdictions;
	}

	public void setJurisdictions(Set<Jurisdiction> jurisdictions) {
		this.jurisdictions = jurisdictions;
	}
	
	

}
