/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eai.superivsion.historiqueIncidents.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mohammed AGHOUI
 */
@Entity
@Table(name = "inc_applicatif")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IncApplicatif.findAll", query = "SELECT i FROM IncApplicatif i"),
    @NamedQuery(name = "IncApplicatif.findByApplication", query = "SELECT i FROM IncApplicatif i WHERE i.application = :application"),
    @NamedQuery(name = "IncApplicatif.findByPlateforme", query = "SELECT i FROM IncApplicatif i WHERE i.plateforme = :plateforme"),
    @NamedQuery(name = "IncApplicatif.findByTicketApp", query = "SELECT i FROM IncApplicatif i WHERE i.ticketApp = :ticketApp"),
    @NamedQuery(name = "IncApplicatif.findByDateDebut", query = "SELECT i FROM IncApplicatif i WHERE i.dateDebut = :dateDebut"),
    @NamedQuery(name = "IncApplicatif.findByDateFin", query = "SELECT i FROM IncApplicatif i WHERE i.dateFin = :dateFin"),
    @NamedQuery(name = "IncApplicatif.findByDuree", query = "SELECT i FROM IncApplicatif i WHERE i.duree = :duree"),
    @NamedQuery(name = "IncApplicatif.findByDisponibilite", query = "SELECT i FROM IncApplicatif i WHERE i.disponibilite = :disponibilite"),
    @NamedQuery(name = "IncApplicatif.findByCommentaire", query = "SELECT i FROM IncApplicatif i WHERE i.commentaire = :commentaire")})
public class IncApplicatif implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "application")
    private String application;
    @Basic(optional = false)
    @Column(name = "plateforme")
    private String plateforme;
    @Id
    @Basic(optional = false)
    @Column(name = "ticket_app")
    private Integer ticketApp;
    @Basic(optional = false)
    @Column(name = "date_debut")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDebut;
    @Basic(optional = false)
    @Column(name = "date_fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateFin;
    @Basic(optional = false)
    @Column(name = "duree")
    private int duree;
    @Basic(optional = false)
    @Column(name = "disponibilite")
    private double disponibilite;
    @Column(name = "commentaire")
    private String commentaire;

    public IncApplicatif() {
    }

    public IncApplicatif(Integer ticketApp) {
        this.ticketApp = ticketApp;
    }

    public IncApplicatif(Integer ticketApp, String application, String plateforme, Date dateDebut, Date dateFin, int duree, double disponibilite) {
        this.ticketApp = ticketApp;
        this.application = application;
        this.plateforme = plateforme;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.duree = duree;
        this.disponibilite = disponibilite;
    }

    public IncApplicatif(Integer ticketApp, String application, String plateforme,  Date dateDebut, Date dateFin, 
	int duree, double disponibilite, String commentaire) {
        this.application = application;
        this.plateforme = plateforme;
        this.ticketApp = ticketApp;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.duree = duree;
        this.disponibilite = disponibilite;
        this.commentaire = commentaire;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getPlateforme() {
        return plateforme;
    }

    public void setPlateforme(String plateforme) {
        this.plateforme = plateforme;
    }

    public Integer getTicketApp() {
        return ticketApp;
    }

    public void setTicketApp(Integer ticketApp) {
        this.ticketApp = ticketApp;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public double getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(double disponibilite) {
        this.disponibilite = disponibilite;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ticketApp != null ? ticketApp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

    	if (!(object instanceof IncApplicatif)) {
            return false;
        }
        IncApplicatif other = (IncApplicatif) object;
        if ((this.ticketApp == null && other.ticketApp != null) || (this.ticketApp != null && !this.ticketApp.equals(other.ticketApp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eai.superivsion.histroriqueIncidents.entities.IncApplicatif[ ticketApp=" + ticketApp + " ]";
    }

}
