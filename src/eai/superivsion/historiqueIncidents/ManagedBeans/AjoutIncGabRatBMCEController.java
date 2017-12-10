/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eai.superivsion.historiqueIncidents.ManagedBeans;

import eai.superivsion.historiqueIncidents.entities.IncApplicatif;
import eai.superivsion.historiqueIncidents.entities.IncGabRat;
import eai.superivsion.historiqueIncidents.jpaControllers.IncGabRatJpaController;
import eai.superivsion.historiqueIncidents.jpaControllers.exceptions.NonexistentEntityException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.Persistence;


import Util.Parsers;

/**
 *
 * @author Mohammed AGHOUI
 */
@ManagedBean(name = "ajoutIncGabRatBMCEController")
@SessionScoped
public class AjoutIncGabRatBMCEController {

    private String ville;
    private String adresseIp;
    private Integer ticketGabRat;
    private String duree;
    private Date dateDebut;
    private Date dateFin;
    private String etat;
    private String commentaire;  
    private List<IncGabRat> listInc;
    private List<IncGabRat> ListInc2;
    private List<IncApplicatif> filteredIncs;
    private IncGabRat selectedInc;

    
    public AjoutIncGabRatBMCEController() {
        listInc = new ArrayList<>();
    }

    public AjoutIncGabRatBMCEController(String idGab, String ville, Integer ticketGabRat, String duree, Date dateDebut, Date dateFin, String commentaire) {
        this.ville = ville;
        this.ticketGabRat = ticketGabRat;
        this.duree = duree;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.commentaire = commentaire;
        listInc = new ArrayList<>();
    }

    

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getAdresseIp() {
        return adresseIp;
    }

    public void setAdresseIp(String adresseIp) {
        this.adresseIp = adresseIp;
    }

    public Integer getTicketGabRat() {
        return ticketGabRat;
    }

    public void setTicketGabRat(Integer ticketGabRat) {
        this.ticketGabRat = ticketGabRat;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }



    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
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

    public List<IncGabRat> getListInc() {
        return listInc;
    }

    public void setListInc(List<IncGabRat> ListInc) {
        this.listInc = ListInc;
    }

    

   
    public void ajouterInc() {
        IncGabRat inc;
        boolean existe = false;
        String messageText;
        IncGabRatJpaController Ijc = new IncGabRatJpaController(Persistence.createEntityManagerFactory("Test"));
        for (Object ListInc1 : listInc) {
            if (((IncGabRat) ListInc1).getTicketGabRat()== ticketGabRat) {
                existe = true;
                messageText = "Ticket :" + ticketGabRat + ", déjà existant dans la Liste!";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, messageText, null);
                FacesContext.getCurrentInstance().addMessage(null, message);
                break;
            }
        }
        if (Ijc.findIncGabRatticket(ticketGabRat) != null) {
            existe = true;
            messageText = "Ticket :" + ticketGabRat + ", déjà existant dans la base de données!";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, messageText, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        if (!existe) {
            inc = new IncGabRat(adresseIp, ville, ticketGabRat, dateDebut, etat, dateFin, ticketGabRat, commentaire);
            listInc.add(inc);
        }

    }

    public void insertInc(IncGabRat inc) throws Exception {
        IncGabRatJpaController IJpaC = new IncGabRatJpaController(Persistence.createEntityManagerFactory("Test"));
        IJpaC.create(inc);
    }

    public String validerAjout() throws Exception {
        for (int i = 0; i < listInc.size(); i++) {
        	
            insertInc(listInc.get(i));
        }
        listInc.clear();
        return "";
      
    }
    

    public String supprimerInc(IncGabRat inc) throws NonexistentEntityException {
        System.out.println(inc);
		listInc.remove(inc);
		return "";
    }
	public List<IncGabRat> getListInc2() {
		IncGabRatJpaController inc = new IncGabRatJpaController(Persistence.createEntityManagerFactory("Test"));
		ListInc2 = inc.findIncGabRatEntities();
		return ListInc2;
	}

	public void setListInc2(List<IncGabRat> listInc2) {
		ListInc2 = listInc2;
	}

	public List<IncApplicatif> getFilteredIncs() {
		return filteredIncs;
	}

	public void setFilteredIncs(List<IncApplicatif> filteredIncs) {
		this.filteredIncs = filteredIncs;
	}
	 public String modifier() throws NonexistentEntityException, Exception {	
		    
	    	IncGabRat incGR = getSelectedInc();
	    	IncGabRatJpaController inc = new IncGabRatJpaController(Persistence.createEntityManagerFactory("Test"));
	    	
	    	
	    	incGR.setAdresseIp(adresseIp);
	    	incGR.setCommentaire(commentaire);
	    	incGR.setDateDebut(dateDebut);
	    	incGR.setDateFin(dateFin);
	    	incGR.setDuree(Parsers.parseDureeMin(duree));
	    	incGR.setEtat(etat);
	    	incGR.setVille(ville);
	    	//incApp.setTicketApp(Ticket);
	    	inc.edit(incGR);
	    	return "AffIncGabRatBMCE.xhtml";
	    }
	
	public String supprimer(IncGabRat incGabRat) throws NonexistentEntityException {
	    	 IncGabRatJpaController inc = new IncGabRatJpaController(Persistence.createEntityManagerFactory("Test"));
	    	 inc.destroy(incGabRat);	 
	         
	         return "";
	    }

		public IncGabRat getSelectedInc() {
			return selectedInc;
		}

		public void setSelectedInc(IncGabRat selectedInc) {
			this.selectedInc = selectedInc;
		}

}
