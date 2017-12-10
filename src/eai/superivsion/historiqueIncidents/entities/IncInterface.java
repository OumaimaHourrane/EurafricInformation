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
@Table(name = "inc_interface")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IncInterface.findAll", query = "SELECT i FROM IncInterface i"),
    @NamedQuery(name = "IncInterface.findByTicketInt", query = "SELECT i FROM IncInterface i WHERE i.ticketInt = :ticketInt"),
    @NamedQuery(name = "IncInterface.findByCdf", query = "SELECT i FROM IncInterface i WHERE i.cdf = :cdf"),
    @NamedQuery(name = "IncInterface.findByAgence", query = "SELECT i FROM IncInterface i WHERE i.agence = :agence"),
    @NamedQuery(name = "IncInterface.findByInterface1", query = "SELECT i FROM IncInterface i WHERE i.interface1 = :interface1"),
    @NamedQuery(name = "IncInterface.findByNbrInc", query = "SELECT i FROM IncInterface i WHERE i.nbrInc = :nbrInc"),
    @NamedQuery(name = "IncInterface.findByDisponibilite", query = "SELECT i FROM IncInterface i WHERE i.disponibilite = :disponibilite"),
    @NamedQuery(name = "IncInterface.findByDateDebut", query = "SELECT i FROM IncInterface i WHERE i.dateDebut = :dateDebut"),
    @NamedQuery(name = "IncInterface.findByDuree", query = "SELECT i FROM IncInterface i WHERE i.duree = :duree"),
    @NamedQuery(name = "IncInterface.findByCumule", query = "SELECT i FROM IncInterface i WHERE i.cumule = :cumule"),
    @NamedQuery(name = "IncInterface.findByOperateur", query = "SELECT i FROM IncInterface i WHERE i.operateur = :operateur")})
public class IncInterface implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ticket_int")
    private Integer ticketInt;
    @Basic(optional = false)
    @Column(name = "cdf")
    private String cdf;
    @Basic(optional = false)
    @Column(name = "agence")
    private String agence;
    @Basic(optional = false)
    @Column(name = "interface")
    private String interface1;
    @Basic(optional = false)
    @Column(name = "nbr_inc")
    private int nbrInc;
    @Basic(optional = false)
    @Column(name = "disponibilite")
    private double disponibilite;
    @Basic(optional = false)
    @Column(name = "date_debut")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDebut;
    @Basic(optional = false)
    @Column(name = "duree")
    private int duree;
    @Basic(optional = false)
    @Column(name = "cumule")
    private int cumule;
    @Basic(optional = false)
    @Column(name = "operateur")
    private String operateur;

    public IncInterface() {
    }

    public IncInterface(Integer ticketInt) {
        this.ticketInt = ticketInt;
    }

    public IncInterface(Integer ticketInt, String cdf, String agence, String interface1, int nbrInc, double disponibilite, Date dateDebut, int duree, int cumule, String operateur) {
        this.ticketInt = ticketInt;
        this.cdf = cdf;
        this.agence = agence;
        this.interface1 = interface1;
        this.nbrInc = nbrInc;
        this.disponibilite = disponibilite;
        this.dateDebut = dateDebut;
        this.duree = duree;
        this.cumule = cumule;
        this.operateur = operateur;
    }

    public Integer getTicketInt() {
        return ticketInt;
    }

    public void setTicketInt(Integer ticketInt) {
        this.ticketInt = ticketInt;
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

    public String getInterface1() {
        return interface1;
    }

    public void setInterface1(String interface1) {
        this.interface1 = interface1;
    }

    public int getNbrInc() {
        return nbrInc;
    }

    public void setNbrInc(int nbrInc) {
        this.nbrInc = nbrInc;
    }

    public double getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(double disponibilite) {
        this.disponibilite = disponibilite;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public int getCumule() {
        return cumule;
    }

    public void setCumule(int cumule) {
        this.cumule = cumule;
    }

    public String getOperateur() {
        return operateur;
    }

    public void setOperateur(String operateur) {
        this.operateur = operateur;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ticketInt != null ? ticketInt.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IncInterface)) {
            return false;
        }
        IncInterface other = (IncInterface) object;
        if ((this.ticketInt == null && other.ticketInt != null) || (this.ticketInt != null && !this.ticketInt.equals(other.ticketInt))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eai.superivsion.histroriqueIncidents.entities.IncInterface[ ticketInt=" + ticketInt + " ]";
    }
    
}
