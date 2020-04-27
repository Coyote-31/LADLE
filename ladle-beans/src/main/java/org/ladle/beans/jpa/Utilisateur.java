package org.ladle.beans.jpa;

import java.io.Serializable;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Classe des utilisateurs pour Hibernate
 *
 * @author Coyote
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "[utilisateur]", schema = "[ladle_db]")
public class Utilisateur implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "utilisateur_id")
  private Integer utilisateurID;
  @Column(name = "ville_id")
  private Integer villeID;
  @Column(name = "pseudo")
  private String pseudo;
  @Column(name = "genre")
  private String genre;
  @Column(name = "nom")
  private String nom;
  @Column(name = "prenom")
  private String prenom;
  @Column(name = "email")
  private String email;
  @Column(name = "mdp")
  private String mdp;
  @Column(name = "salt")
  private byte[] salt;
  @Column(name = "role")
  private Integer role;
  @Column(name = "email_sha")
  private String emailSHA;
  @Column(name = "date_email")
  private ZonedDateTime dateEmail;
  @Column(name = "date_compte")
  private ZonedDateTime dateCompte;
  @Column(name = "token_login")
  private String tokenLogin;

  /**
   * Constructeurs
   */

  public Utilisateur() {
  }

  public Utilisateur(Integer villeID, String pseudo, String genre, String nom, String prenom, String email, String mdp,
      byte[] salt, Integer role, String emailSHA, ZonedDateTime dateEmail, ZonedDateTime dateCompte) {
    super();
    this.villeID = villeID;
    this.pseudo = pseudo;
    this.genre = genre;
    this.nom = nom;
    this.prenom = prenom;
    this.email = email;
    this.mdp = mdp;
    this.salt = salt.clone();
    this.role = role;
    this.emailSHA = emailSHA;
    this.dateEmail = dateEmail;
    this.dateCompte = dateCompte;
  }

  /**
   * Getters & Setters
   */

  public Integer getUtilisateurID() {
    return utilisateurID;
  }

  public void setUtilisateurID(Integer utilisateurID) {
    this.utilisateurID = utilisateurID;
  }

  public Integer getVilleID() {
    return villeID;
  }

  public void setVilleID(Integer villeID) {
    this.villeID = villeID;
  }

  public String getPseudo() {
    return pseudo;
  }

  public void setPseudo(String pseudo) {
    this.pseudo = pseudo;
  }

  public String getGenre() {
    return genre;
  }

  public void setGenre(String genre) {
    this.genre = genre;
  }

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public String getPrenom() {
    return prenom;
  }

  public void setPrenom(String prenom) {
    this.prenom = prenom;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getMdp() {
    return mdp;
  }

  public void setMdp(String mdp) {
    this.mdp = mdp;
  }

  public byte[] getSalt() {
    return salt.clone();
  }

  public void setSalt(byte[] salt) {
    this.salt = salt.clone();
  }

  public Integer getRole() {
    return role;
  }

  public void setRole(Integer role) {
    this.role = role;
  }

  public String getEmailSHA() {
    return emailSHA;
  }

  public void setEmailSHA(String emailSHA) {
    this.emailSHA = emailSHA;
  }

  public ZonedDateTime getDateEmail() {
    return dateEmail;
  }

  public void setDateEmail(ZonedDateTime dateEmail) {
    this.dateEmail = dateEmail;
  }

  public ZonedDateTime getDateCompte() {
    return dateCompte;
  }

  public void setDateCompte(ZonedDateTime dateCompte) {
    this.dateCompte = dateCompte;
  }

  public String getTokenLogin() {
    return tokenLogin;
  }

  public void setTokenLogin(String tokenLogin) {
    this.tokenLogin = tokenLogin;
  }

}
