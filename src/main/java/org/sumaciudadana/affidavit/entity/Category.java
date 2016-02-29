package org.sumaciudadana.affidavit.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pservant_category", catalog = "sumaciudadana")
public class Category implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7988621787586479768L;
	
	private int idCategory;
	private String name;
	private int dependency;
	
	public Category() {
		super();
	}
	
	public Category(int idCategory, String name, int dependency) {
		this.idCategory = idCategory;
		this.name = name;
		this.dependency = dependency;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idcategory", unique = true, nullable = false)
	public int getIdCategory() {
		return idCategory;
	}
	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}
	
	@Column(name = "cat_name", length = 45)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "cat_dependency")
	public int getDependency() {
		return dependency;
	}
	public void setDependency(int dependency) {
		this.dependency = dependency;
	}

}
