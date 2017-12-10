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
@Table(name = "inc_lien_gab_ext")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IncLienGabExt.findAll", query = "SELECT i FROM IncLienGabExt i"),
    @NamedQuery(name = "IncLienGabExt.findByIdGab", query = "SELECT i FROM IncLienGabExt i WHERE i.idGab = :idGab"),
    @NamedQuery(name = "IncLienGabExt.findByVille", query = "SELECT i FROM IncLienGabExt i WHERE i.ville = :ville"),
    @NamedQuery(name = "IncLienGabExt.findByTicketLienGabExt", query = "SELECT i FROM IncLienGabExt i WHERE i.ticketLienGabExt = :ticketLienGabExt"),
    @NamedQuery(name = "IncLienGabExt.findByDuree", query = "SELECT i FROM IncLienGabExt i WHERE i.duree = :duree"),
    @NamedQuery(name = "IncLienGabExt.findByDateDebut", query = "SELECT i FROM IncLienGabExt i WHERE i.dateDebut = :dateDebut"),
    @NamedQuery(name = "IncLienGabExt.findByDateFin", query = "SELECT i FROM IncLienGabExt i WHERE i.dateFin = :dateFin"),
    @NamedQuery(name = "IncLienGabExt.findByCommentaire", query = "SELECT i FROM IncLienGabExt i WHERE i.commentaire = :commentaire")})
public class IncLienGabExt implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "id_gab")
    private String idGab;
    @Basic(optional = false)
    @Column(name = "ville")
    private String ville;
    @Id
    @Basic(optional = false)
    @Column(name = "ticket_lien_gab_ext")
    private Integer ticketLienGabExt;
    @Column(name = "duree")
    private Integer duree;
    @Basic(optional = false)
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

    public IncLienGabExt() {
    }

    public IncLienGabExt(Integer ticketLienGabExt) {
        this.ticketLienGabExt = ticketLienGabExt;
    }



    public IncLienGabExt(String idGab, String ville, Integer ticketLienGabExt, Integer duree, Date dateDebut, Date dateFin, String commentaire) {
        this.idGab = idGab;
        this.ville = ville;
        this.ticketLienGabExt = ticketLienGabExt;
        this.duree = duree;
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

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public Integer getTicketLienGabExt() {
        return ticketLienGabExt;
    }

    public void setTicketLienGabExt(Integer ticketLienGabExt) {
        this.ticketLienGabExt = ticketLienGabExt;
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
        hash += (ticketLienGabExt != null ? ticketLienGabExt.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IncLienGabExt)) {
            return false;
        }
        IncLienGabExt other = (IncLienGabExt) object;
        if ((this.ticketLienGabExt == null && other.ticketLienGabExt != null) || (this.ticketLienGabExt != null && !this.ticketLienGabExt.equals(other.ticketLienGabExt))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eai.superivsion.histroriqueIncidents.entities.IncLienGabExt[ ticketLienGabExt=" + ticketLienGabExt + " ]";
    }
    
}
