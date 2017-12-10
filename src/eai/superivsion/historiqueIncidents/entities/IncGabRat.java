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
@Table(name = "inc_gab_rat")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IncGabRat.findAll", query = "SELECT i FROM IncGabRat i"),
    @NamedQuery(name = "IncGabRat.findByAdresseIp", query = "SELECT i FROM IncGabRat i WHERE i.adresseIp = :adresseIp"),
    @NamedQuery(name = "IncGabRat.findByVille", query = "SELECT i FROM IncGabRat i WHERE i.ville = :ville"),
    @NamedQuery(name = "IncGabRat.findByDuree", query = "SELECT i FROM IncGabRat i WHERE i.duree = :duree"),
    @NamedQuery(name = "IncGabRat.findByDateDebut", query = "SELECT i FROM IncGabRat i WHERE i.dateDebut = :dateDebut"),
    @NamedQuery(name = "IncGabRat.findByEtat", query = "SELECT i FROM IncGabRat i WHERE i.etat = :etat"),
    @NamedQuery(name = "IncGabRat.findByDateFin", query = "SELECT i FROM IncGabRat i WHERE i.dateFin = :dateFin"),
    @NamedQuery(name = "IncGabRat.findByTicketGabRat", query = "SELECT i FROM IncGabRat i WHERE i.ticketGabRat = :ticketGabRat"),
    @NamedQuery(name = "IncGabRat.findByCommentaire", query = "SELECT i FROM IncGabRat i WHERE i.commentaire = :commentaire")})
public class IncGabRat implements Serializable {
    private static final long serialVersionUID = 1L;
   
    @Column(name = "adresse_ip")
    private String adresseIp;
    @Column(name = "ville")
    private String ville;

    public IncGabRat(String adresseIp, String ville, Integer duree, Date dateDebut, String etat, Date dateFin, Integer ticketGabRat, String commentaire) {
        this.adresseIp = adresseIp;
        this.ville = ville;
        this.duree = duree;
        this.dateDebut = dateDebut;
        this.etat = etat;
        this.dateFin = dateFin;
        this.ticketGabRat = ticketGabRat;
        this.commentaire = commentaire;
    }
    @Column(name = "duree")
    private Integer duree;
    @Column(name = "date_debut")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDebut;
    @Column(name = "etat")
    private String etat;
    @Column(name = "date_fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateFin;
    @Id
    @Basic(optional = false)
    @Column(name = "ticket_gab_rat")
    private Integer ticketGabRat;
    @Basic(optional = false)
    @Column(name = "commentaire")
    private String commentaire;

    public IncGabRat() {
    }

    public IncGabRat(Integer ticketGabRat) {
        this.ticketGabRat = ticketGabRat;
    }

    public IncGabRat(Integer ticketGabRat, String commentaire) {
        this.ticketGabRat = ticketGabRat;
        this.commentaire = commentaire;
    }

   

    public String getAdresseIp() {
        return adresseIp;
    }

    public void setAdresseIp(String adresseIp) {
        this.adresseIp = adresseIp;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public Integer getDuree() {
        return duree;
    }

    public void setDuree(Integer duree) {
        this.duree = duree;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Integer getTicketGabRat() {
        return ticketGabRat;
    }

    public void setTicketGabRat(Integer ticketGabRat) {
        this.ticketGabRat = ticketGabRat;
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
        hash += (ticketGabRat != null ? ticketGabRat.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IncGabRat)) {
            return false;
        }
        IncGabRat other = (IncGabRat) object;
        if ((this.ticketGabRat == null && other.ticketGabRat != null) || (this.ticketGabRat != null && !this.ticketGabRat.equals(other.ticketGabRat))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eai.superivsion.histroriqueIncidents.entities.IncGabRat[ ticketGabRat=" + ticketGabRat + " ]";
    }
    
}
