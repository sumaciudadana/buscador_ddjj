package org.sumaciudadana.affidavit.entity;

// Generated 06/07/2012 11:12:13 AM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Belonging generated by hbm2java
 */
@Entity
@Table(name = "belonging", catalog = "sumaciudadana")
public class Belonging {

	private Integer idbelonging;
	private BelongingType belongingType;
	private Affidavit affidavit;
	private String categoryBelonging;
	private String desBelonging;
	private String detailBelonging;
	private Long valueBelonging;
	private String belongingSource;
	private String userCreate;
	private Date dateCreate;
	private String userModify;
	private Date dateModify;

	public Belonging() {
	}

	public Belonging(Affidavit affidavit) {
		this.affidavit = affidavit;
	}

	public Belonging(BelongingType belongingType, Affidavit affidavit,
			String categoryBelonging, String desBelonging,
			String detailBelonging, Long valueBelonging, String userCreate,
			Date dateCreate, String userModify, Date dateModify) {
		this.belongingType = belongingType;
		this.affidavit = affidavit;
		this.categoryBelonging = categoryBelonging;
		this.desBelonging = desBelonging;
		this.detailBelonging = detailBelonging;
		this.valueBelonging = valueBelonging;
		this.userCreate = userCreate;
		this.dateCreate = dateCreate;
		this.userModify = userModify;
		this.dateModify = dateModify;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idbelonging", unique = true, nullable = false)
	public Integer getIdbelonging() {
		return this.idbelonging;
	}

	public void setIdbelonging(Integer idbelonging) {
		this.idbelonging = idbelonging;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idbelonging_types")
	public BelongingType getBelongingType() {
		return this.belongingType;
	}

	public void setBelongingType(BelongingType belongingType) {
		this.belongingType = belongingType;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idaffidavit", nullable = false)
	public Affidavit getAffidavit() {
		return this.affidavit;
	}

	public void setAffidavit(Affidavit affidavit) {
		this.affidavit = affidavit;
	}

	@Column(name = "category_belonging", length = 1)
	public String getCategoryBelonging() {
		return categoryBelonging;
	}

	public void setCategoryBelonging(String categoryBelonging) {
		this.categoryBelonging = categoryBelonging;
	}

	@Column(name = "des_belonging", length = 150)
	public String getDesBelonging() {
		return this.desBelonging;
	}

	public void setDesBelonging(String desBelonging) {
		this.desBelonging = desBelonging;
	}

	@Column(name = "detail_belonging", length = 100)
	public String getDetailBelonging() {
		return detailBelonging;
	}

	public void setDetailBelonging(String detailBelonging) {
		this.detailBelonging = detailBelonging;
	}

	@Column(name = "value_belonging", precision = 10, scale = 0)
	public Long getValueBelonging() {
		return this.valueBelonging;
	}

	public void setValueBelonging(Long valueBelonging) {
		this.valueBelonging = valueBelonging;
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

	@Column(name = "belonging_source", length = 50)
	public String getBelongingSource() {
		return belongingSource;
	}

	public void setBelongingSource(String belongingSource) {
		this.belongingSource = belongingSource;
	}

}
