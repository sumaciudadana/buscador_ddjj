package org.sumaciudadana.affidavit.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "filelog", catalog = "sumaciudadana")
public class Filelog {

	private int idfilelog;
	private User user;
	private String nameOriginal;
	private String currentName;
	private Date createTime;

	public Filelog() {
	}

	public Filelog(int idfilelog, User user, String nameOriginal,
			String currentName, Date createTime) {
		this.idfilelog = idfilelog;
		this.user = user;
		this.nameOriginal = nameOriginal;
		this.currentName = currentName;
		this.createTime = createTime;
	}
	
	public Filelog(User user, String nameOriginal,
			String currentName, Date createTime) {
		this.idfilelog = idfilelog;
		this.user = user;
		this.nameOriginal = nameOriginal;
		this.currentName = currentName;
		this.createTime = createTime;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idfilelog", unique = true, nullable = false)
	public int getIdfilelog() {
		return idfilelog;
	}

	public void setIdfilelog(int idfilelog) {
		this.idfilelog = idfilelog;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "iduser")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "file_original_name", length = 45)
	public String getNameOriginal() {
		return nameOriginal;
	}

	public void setNameOriginal(String nameOriginal) {
		this.nameOriginal = nameOriginal;
	}

	@Column(name = "file_current_name", length = 45)
	public String getCurrentName() {
		return currentName;
	}

	public void setCurrentName(String currentName) {
		this.currentName = currentName;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_create", length = 19)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
