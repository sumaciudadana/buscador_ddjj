package org.sumaciudadana.affidavit.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "jurisdiction", catalog = "sumaciudadana")
public class Jurisdiction {

	private int idJurisdiction;
	private Organization organization;
	private String jurName;
	private Set<Position> positions = new HashSet<Position>(0);

	public Jurisdiction() {
	}

	public Jurisdiction(int idJurisdiction, Organization organization,
			String jurName, Set<Position> positions) {
		this.idJurisdiction = idJurisdiction;
		this.organization = organization;
		this.jurName = jurName;
		this.positions = positions;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idjurisdiction", unique = true, nullable = false)
	public int getIdJurisdiction() {
		return idJurisdiction;
	}

	public void setIdJurisdiction(int idJurisdiction) {
		this.idJurisdiction = idJurisdiction;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idorganization")
	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization entity) {
		this.organization = entity;
	}

	@Column(name = "jur_name", length = 100)
	public String getJurName() {
		return jurName;
	}

	public void setJurName(String jurName) {
		this.jurName = jurName;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "jurisdiction")
	public Set<Position> getPositions() {
		return positions;
	}

	public void setPositions(Set<Position> positions) {
		this.positions = positions;
	}

}
