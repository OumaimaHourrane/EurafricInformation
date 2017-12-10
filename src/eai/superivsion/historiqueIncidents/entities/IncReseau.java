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
@Table(name = "inc_reseau")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IncReseau.findAll", query = "SELECT i FROM IncReseau i"),
    @NamedQuery(name = "IncReseau.findByTicketReseau", query = "SELECT i FROM IncReseau i WHERE i.ticketReseau = :ticketReseau"),
    @NamedQuery(name = "IncReseau.findByOperateur", query = "SELECT i FROM IncReseau i WHERE i.operateur = :operateur"),
    @NamedQuery(name = "IncReseau.findByCdf", query = "SELECT i FROM IncReseau i WHERE i.cdf = :cdf"),
    @NamedQuery(name = "IncReseau.findByAgence", query = "SELECT i FROM IncReseau i WHERE i.agence = :agence"),
    @NamedQuery(name = "IncReseau.findByDatePro", query = "SELECT i FROM IncReseau i WHERE i.datePro = :datePro"),
    @NamedQuery(name = "IncReseau.findByDateDebut", query = "SELECT i FROM IncReseau i WHERE i.dateDebut = :dateDebut"),
    @NamedQuery(name = "IncReseau.findByDateFin", query = "SELECT i FROM IncReseau i WHERE i.dateFin = :dateFin"),
    @NamedQuery(name = "IncReseau.findByDuree", query = "SELECT i FROM IncReseau i WHERE i.duree = :duree"),
    @NamedQuery(name = "IncReseau.findByTypeLiaison", query = "SELECT i FROM IncReseau i WHERE i.typeLiaison = :typeLiaison"),
    @NamedQuery(name = "IncReseau.findByCommentaire", query = "SELECT i FROM IncReseau i WHERE i.commentaire = :commentaire")})
public class IncReseau implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ticket_reseau")
    private Integer ticketReseau;
    @Basic(optional = false)
    @Column(name = "operateur")
    private String operateur;
    @Basic(optional = false)
    @Column(name = "cdf")
    private String cdf;
    @Basic(optional = false)
    @Column(name = "agence")
    private String agence;
    @Basic(optional = false)
    @Column(name = "date_pro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datePro;
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
    @Column(name = "type_liaison")
    private String typeLiaison;
    @Basic(optional = false)
    @Column(name = "commentaire")
    private String commentaire;

    public IncReseau() {
    }

    public IncReseau(Integer ticketReseau) {
        this.ticketReseau = ticketReseau;
    }

    public IncReseau(Integer ticketReseau, String operateur, String cdf, String agence, Date datePro, Date dateDebut, Date dateFin, int duree, String typeLiaison, String commentaire) {
        this.ticketReseau = ticketReseau;
        this.operateur = operateur;
        this.cdf = cdf;
        this.agence = agence;
        this.datePro = datePro;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.duree = duree;
        this.typeLiaison = typeLiaison;
        this.commentaire = commentaire;
    }

    public Integer getTicketReseau() {
        return ticketReseau;
    }

    public void setTicketReseau(Integer ticketReseau) {
        this.ticketReseau = ticketReseau;
    }

    public String getOperateur() {
        return operateur;
    }

    public void setOperateur(String operateur) {
        this.operateur = operateur;
    }

    public String getCdf() {
        return cdf;
    }

    public void setCdf(String cdf) {
        this.cdf = cdf;
    }

    public String getAgence() {
        return agence;
    }

    public void setAgence(String agence) {
        this.agence = agence;
    }

    public Date getDatePro() {
        return datePro;
    }

    public void setDatePro(Date datePro) {
        this.datePro = datePro;
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

    public String getTypeLiaison() {
        return typeLiaison;
    }

    public void setTypeLiaison(String typeLiaison) {
        this.typeLiaison = typeLiaison;
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
        hash += (ticketReseau != null ? ticketReseau.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IncReseau)) {
            return false;
        }
        IncReseau other = (IncReseau) object;
        if ((this.ticketReseau == null && other.ticketReseau != null) || (this.ticketReseau != null && !this.ticketReseau.equals(other.ticketReseau))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eai.superivsion.histroriqueIncidents.entities.IncReseau[ ticketReseau=" + ticketReseau + " ]";
    }
    
}
