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
@Table(name = "inc_infra")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IncInfra.findAll", query = "SELECT i FROM IncInfra i"),
    @NamedQuery(name = "IncInfra.findByEquipement", query = "SELECT i FROM IncInfra i WHERE i.equipement = :equipement"),
    @NamedQuery(name = "IncInfra.findByTicketInfra", query = "SELECT i FROM IncInfra i WHERE i.ticketInfra = :ticketInfra"),
    @NamedQuery(name = "IncInfra.findByDateDebut", query = "SELECT i FROM IncInfra i WHERE i.dateDebut = :dateDebut"),
    @NamedQuery(name = "IncInfra.findByDateFin", query = "SELECT i FROM IncInfra i WHERE i.dateFin = :dateFin"),
    @NamedQuery(name = "IncInfra.findByDuree", query = "SELECT i FROM IncInfra i WHERE i.duree = :duree"),
    @NamedQuery(name = "IncInfra.findByDisponibilite", query = "SELECT i FROM IncInfra i WHERE i.disponibilite = :disponibilite"),
    @NamedQuery(name = "IncInfra.findByCommentaire", query = "SELECT i FROM IncInfra i WHERE i.commentaire = :commentaire")})
public class IncInfra implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "equipement")
    private String equipement;
    @Id
    @Basic(optional = false)
    @Column(name = "ticket_infra")
    private Integer ticketInfra;
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

    public IncInfra() {
    }

    public IncInfra(Integer ticketInfra) {
        this.ticketInfra = ticketInfra;
    }

    public IncInfra(Integer ticketInfra, String equipement, Date dateDebut, Date dateFin, int duree, double disponibilite) {
        this.ticketInfra = ticketInfra;
        this.equipement = equipement;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.duree = duree;
        this.disponibilite = disponibilite;
    }

    public IncInfra( Integer ticketInfra, String equipement, Date dateDebut, Date dateFin, int duree, double disponibilite, String commentaire) {
        this.equipement = equipement;
        this.ticketInfra = ticketInfra;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.duree = duree;
        this.disponibilite = disponibilite;
        this.commentaire = commentaire;
    }
    
    

    public String getEquipement() {
        return equipement;
    }

    public void setEquipement(String equipement) {
        this.equipement = equipement;
    }

    public Integer getTicketInfra() {
        return ticketInfra;
    }

    public void setTicketInfra(Integer ticketInfra) {
        this.ticketInfra = ticketInfra;
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
        hash += (ticketInfra != null ? ticketInfra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IncInfra)) {
            return false;
        }
        IncInfra other = (IncInfra) object;
        if ((this.ticketInfra == null && other.ticketInfra != null) || (this.ticketInfra != null && !this.ticketInfra.equals(other.ticketInfra))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eai.superivsion.histroriqueIncidents.entities.IncInfra[ ticketInfra=" + ticketInfra + " ]";
    }
    
}
