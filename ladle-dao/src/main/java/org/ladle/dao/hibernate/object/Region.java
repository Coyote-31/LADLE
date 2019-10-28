package org.ladle.dao.hibernate.object;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Classe de test de BDD avec hibernate
 * @author Coyote
 */
@Entity
@Table(name="region")
public class Region {
	
	@Id
	@Column(name="region_id")
	private Integer regionID;
	@Column(name="region_code")
	private String regionCode;
	@Column(name="nom")
	private String nom;
	@Column(name="soundex")
	private String soundex;
	
	/**
	 * Constructeurs
	 */
	
	public Region() {}

	public Region(String nom, String soundex) {
		super();
		this.nom = nom;
		this.soundex = soundex;
	}

	/**
	 * Getters & Setters
	 */
	
	public Integer getRegionID() {
		return regionID;
	}

	public void setRegionID(Integer regionID) {
		this.regionID = regionID;
	}
	
	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getSoundex() {
		return soundex;
	}

	public void setSoundex(String soundex) {
		this.soundex = soundex;
	}
	
}
