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
@Table(name = "inc_gab_ext")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IncGabExt.findAll", query = "SELECT i FROM IncGabExt i"),
    @NamedQuery(name = "IncGabExt.findByIdGab", query = "SELECT i FROM IncGabExt i WHERE i.idGab = :idGab"),
    @NamedQuery(name = "IncGabExt.findByAdresseIp", query = "SELECT i FROM IncGabExt i WHERE i.adresseIp = :adresseIp"),
    @NamedQuery(name = "IncGabExt.findByVille", query = "SELECT i FROM IncGabExt i WHERE i.ville = :ville"),
    @NamedQuery(name = "IncGabExt.findByTicketGabExt", query = "SELECT i FROM IncGabExt i WHERE i.ticketGabExt = :ticketGabExt"),
    @NamedQuery(name = "IncGabExt.findByDuree", query = "SELECT i FROM IncGabExt i WHERE i.duree = :duree"),
    @NamedQuery(name = "IncGabExt.findByEtat", query = "SELECT i FROM IncGabExt i WHERE i.etat = :etat"),
    @NamedQuery(name = "IncGabExt.findByDateDebut", query = "SELECT i FROM IncGabExt i WHERE i.dateDebut = :dateDebut"),
    @NamedQuery(name = "IncGabExt.findByDateFin", query = "SELECT i FROM IncGabExt i WHERE i.dateFin = :dateFin"),
    @NamedQuery(name = "IncGabExt.findByCommentaire", query = "SELECT i FROM IncGabExt i WHERE i.commentaire = :commentaire")})
public class IncGabExt implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "id_gab")
    private String idGab;
    @Basic(optional = false)
    @Column(name = "adresse_ip")
    private String adresseIp;
    @Basic(optional = false)
    @Column(name = "ville")
    private String ville;
    @Id
    @Basic(optional = false)
    @Column(name = "ticket_gab_ext")
    private Integer ticketGabExt;
    @Basic(optional = false)
    @Column(name = "duree")
    private int duree;
    @Basic(optional = false)
    @Column(name = "etat")
    private String etat;
    @Column(name = "date_debut")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDebut;
    @Basic(optional = false)
    @Column(name = "date_fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateFin;
    @Basic(optional = false)
    @Column(name = "commentaire")
    private String commentaire;

    public IncGabExt() {
    }

    public IncGabExt(Integer ticketGabExt) {
        this.ticketGabExt = ticketGabExt;
    }

    public IncGabExt(String idGab, String adresseIp, String ville, Integer ticketGabExt, int duree, String etat, Date dateDebut, Date dateFin, String commentaire) {
        this.idGab = idGab;
        this.adresseIp = adresseIp;
        this.ville = ville;
        this.ticketGabExt = ticketGabExt;
        this.duree = duree;
        this.etat = etat;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.commentaire = commentaire;
    }
    

    public String getIdGab() {
        return idGab;
    }

    public void setIdGab(String idGab) {
        this.idGab = idGab;
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

    public Integer getTicketGabExt() {
        return ticketGabExt;
    }

    public void setTicketGabExt(Integer ticketGabExt) {
        this.ticketGabExt = ticketGabExt;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
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

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ticketGabExt != null ? ticketGabExt.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IncGabExt)) {
            return false;
        }
        IncGabExt other = (IncGabExt) object;
        if ((this.ticketGabExt == null && other.ticketGabExt != null) || (this.ticketGabExt != null && !this.ticketGabExt.equals(other.ticketGabExt))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eai.superivsion.histroriqueIncidents.entities.IncGabExt[ ticketGabExt=" + ticketGabExt + " ]";
    }
    
}
