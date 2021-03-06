package org.sumaciudadana.affidavit.entity;

// Generated 06/07/2012 11:12:13 AM by Hibernate Tools 3.4.0.CR1

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * Pservant generated by hbm2java
 */
@Entity
@Table(name = "pservant", catalog = "sumaciudadana")
public class Pservant {

	private Integer idpservant;
	private String serName;
	private String serFirstsurname;
	private String serSecondsurname;
	private String serDni;
	private String serAddress;
	private byte[] serImage;
	private String userCreate;
	private Date dateCreate;
	private String userModify;
	private Date dateModify;
	private Set<Complaint> complaints = new HashSet<Complaint>(0);
	private Set<Affidavit> affidavits = new HashSet<Affidavit>(0);

	@Transient
	private String serFullName;

	public Pservant() {
	}
	
	public Pservant(Integer idpservant) {
		this.idpservant = idpservant;
	}

	public Pservant(String serName, String serFirstsurname,
			String serSecondsurname, String serDni, String serAddress,
			byte[] serImage, String userCreate, Date dateCreate,
			String userModify, Date dateModify, Set<Complaint> complaints,
			Set<Affidavit> affidavits) {
		this.serName = serName;
		this.serFirstsurname = serFirstsurname;
		this.serSecondsurname = serSecondsurname;
		this.serDni = serDni;
		this.serAddress = serAddress;
		this.serImage = serImage;
		this.userCreate = userCreate;
		this.dateCreate = dateCreate;
		this.userModify = userModify;
		this.dateModify = dateModify;
		this.complaints = complaints;
		this.affidavits = affidavits;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idpservant", unique = true, nullable = false)
	public Integer getIdpservant() {
		return this.idpservant;
	}

	public void setIdpservant(Integer idpservant) {
		this.idpservant = idpservant;
	}

	@Column(name = "ser_name", length = 45)
	public String getSerName() {
		return this.serName;
	}

	public void setSerName(String serName) {
		this.serName = serName;
	}

	@Column(name = "ser_firstsurname", length = 45)
	public String getSerFirstsurname() {
		return this.serFirstsurname;
	}

	public void setSerFirstsurname(String serFirstsurname) {
		this.serFirstsurname = serFirstsurname;
	}

	@Column(name = "ser_secondsurname", length = 45)
	public String getSerSecondsurname() {
		return this.serSecondsurname;
	}

	public void setSerSecondsurname(String serSecondsurname) {
		this.serSecondsurname = serSecondsurname;
	}

	@Column(name = "ser_dni", length = 8)
	public String getSerDni() {
		return this.serDni;
	}

	public void setSerDni(String serDni) {
		this.serDni = serDni;
	}

	@Column(name = "ser_address", length = 100)
	public String getSerAddress() {
		return this.serAddress;
	}

	public void setSerAddress(String serAddress) {
		this.serAddress = serAddress;
	}

	@Column(name = "ser_image")
	public byte[] getSerImage() {
		return this.serImage;
	}

	public void setSerImage(byte[] serImage) {
		this.serImage = serImage;
	}

	@Column(name = "user_create", length = 45)
	public String getUserCreate() {
		return this.userCreate;
	}

	public void setUserCreate(String userCreate) {
		this.userCreate = userCreate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_create", length = 19)
	public Date getDateCreate() {
		return this.dateCreate;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}

	@Column(name = "user_modify", length = 45)
	public String getUserModify() {
		return this.userModify;
	}

	public void setUserModify(String userModify) {
		this.userModify = userModify;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_modify", length = 19)
	public Date getDateModify() {
		return this.dateModify;
	}

	public void setDateModify(Date dateModify) {
		this.dateModify = dateModify;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pservant")
	public Set<Complaint> getComplaints() {
		return this.complaints;
	}

	public void setComplaints(Set<Complaint> complaints) {
		this.complaints = complaints;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pservant")
	public Set<Affidavit> getAffidavits() {
		return this.affidavits;
	}

	public void setAffidavits(Set<Affidavit> affidavits) {
		this.affidavits = affidavits;
	}

	@Transient
	public String getSerFullName() {
		if (this.getIdpservant() != null) {
			return this.serFirstsurname.concat(" ")
					.concat(this.serSecondsurname).concat(", ")
					.concat(this.serName);
		} else {
			return "";
		}
	}

	public void setSerFullName(String serFullName) {
		this.serFullName = serFullName;
	}

}
