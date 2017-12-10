package eai.superivsion.historiqueIncidents.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the pssoft_data database table.
 * 
 */
@Entity
@Table(name="pssoft_data")
@NamedQuery(name="PssoftData.findAll", query="SELECT p FROM PssoftData p")
public class PssoftData implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer ticket;

	private String createur;

	@Column(name="date_debut")
	private Timestamp dateDebut;

	@Column(name="date_debut_fournisseur")
	private Timestamp dateDebutFournisseur;

	@Column(name="date_fin")
	private Timestamp dateFin;

	@Column(name="date_fin_fournisseur")
	private Timestamp dateFinFournisseur;

	@Column(name="delais_cloture")
	private Integer delaisCloture;

	@Column(name="delais_cloture_j")
	private Integer delaisClotureJ;

	private String designation;

	private String etat;

	@Column(name="groupe_traitement")
	private String groupeTraitement;

	@Column(name="groupe_traitement_2")
	private String groupeTraitement2;

	@Column(name="groupe_traitement_3")
	private String groupeTraitement3;

	@Column(name="n_appel")
	private Integer nAppel;

	@Column(name="operateur_traitement")
	private String operateurTraitement;

	public PssoftData() {
	}

	public Integer getTicket() {
		return this.ticket;
	}

	public void setTicket(Integer ticket) {
		this.ticket = ticket;
	}

	public String getCreateur() {
		return this.createur;
	}

	public void setCreateur(String createur) {
		this.createur = createur;
	}

	public Timestamp getDateDebut() {
		return this.dateDebut;
	}

	public void setDateDebut(Timestamp dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Timestamp getDateDebutFournisseur() {
		return this.dateDebutFournisseur;
	}

	public void setDateDebutFournisseur(Timestamp dateDebutFournisseur) {
		this.dateDebutFournisseur = dateDebutFournisseur;
	}

	public Timestamp getDateFin() {
		return this.dateFin;
	}

	public void setDateFin(Timestamp dateFin) {
		this.dateFin = dateFin;
	}

	public Timestamp getDateFinFournisseur() {
		return this.dateFinFournisseur;
	}

	public void setDateFinFournisseur(Timestamp dateFinFournisseur) {
		this.dateFinFournisseur = dateFinFournisseur;
	}

	public Integer getDelaisCloture() {
		return this.delaisCloture;
	}

	public void setDelaisCloture(Integer delaisCloture) {
		this.delaisCloture = delaisCloture;
	}

	public Integer getDelaisClotureJ() {
		return this.delaisClotureJ;
	}

	public void setDelaisClotureJ(Integer delaisClotureJ) {
		this.delaisClotureJ = delaisClotureJ;
	}

	public String getDesignation() {
		return this.designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getEtat() {
		return this.etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public String getGroupeTraitement() {
		return this.groupeTraitement;
	}

	public void setGroupeTraitement(String groupeTraitement) {
		this.groupeTraitement = groupeTraitement;
	}

	public String getGroupeTraitement2() {
		return this.groupeTraitement2;
	}

	public void setGroupeTraitement2(String groupeTraitement2) {
		this.groupeTraitement2 = groupeTraitement2;
	}

	public String getGroupeTraitement3() {
		return this.groupeTraitement3;
	}

	public void setGroupeTraitement3(String groupeTraitement3) {
		this.groupeTraitement3 = groupeTraitement3;
	}

	public Integer getNAppel() {
		return this.nAppel;
	}

	public void setNAppel(Integer nAppel) {
		this.nAppel = nAppel;
	}

	public String getOperateurTraitement() {
		return this.operateurTraitement;
	}

	public void setOperateurTraitement(String operateurTraitement) {
		this.operateurTraitement = operateurTraitement;
	}

}