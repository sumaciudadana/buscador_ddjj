package org.sumaciudadana.affidavit.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "POSITION", catalog = "sumaciudadana")
public class Position {

	private int idposition;
	private String posName;
	private Jurisdiction jurisdiction;

	public Position() {
	}

	public Position(String posName, Jurisdiction jurisdiction) {
		this.posName = posName;
		this.jurisdiction = jurisdiction;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idposition", unique = true, nullable = false)
	public int getIdposition() {
		return idposition;
	}

	public void setIdposition(int idposition) {
		this.idposition = idposition;
	}

	@Column(name = "pos_name", length = 100)
	public String getPosName() {
		return posName;
	}

	public void setPosName(String posName) {
		this.posName = posName;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idjurisdiction")
	public Jurisdiction getJurisdiction() {
		return jurisdiction;
	}

	public void setJurisdiction(Jurisdiction jurisdiction) {
		this.jurisdiction = jurisdiction;
	}

}
