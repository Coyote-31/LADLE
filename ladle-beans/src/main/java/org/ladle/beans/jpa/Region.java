package org.ladle.beans.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Classe des régions pour hibernate
 *
 * @author Coyote
 */
@Entity
@Table(name = "[region]", schema = "[ladle_db]")
public class Region {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "region_id")
  private Integer regionID;
  @Column(name = "region_code", length = 3, nullable = false)
  private String regionCode;
  @Column(name = "nom", nullable = false)
  private String nom;
  @Column(name = "soundex", nullable = false)
  private String soundex;

  /**
   * Constructeurs
   */

  public Region() {
  }

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
