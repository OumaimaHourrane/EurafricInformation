/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eai.superivsion.historiqueIncidents.ManagedBeans;

import eai.superivsion.historiqueIncidents.entities.IncApplicatif;
import eai.superivsion.historiqueIncidents.entities.IncInfra;
import eai.superivsion.historiqueIncidents.entities.IncInterface;
import eai.superivsion.historiqueIncidents.entities.IncLienGabExt;
import eai.superivsion.historiqueIncidents.jpaControllers.IncApplicatifJpaController;
import eai.superivsion.historiqueIncidents.jpaControllers.IncLienGabExtJpaController;
import eai.superivsion.historiqueIncidents.jpaControllers.exceptions.NonexistentEntityException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.Persistence;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.RowEditEvent;

import Util.Parsers;

/**
 *
 * @author Mohammed AGHOUI
 */
@ManagedBean(name = "ajoutIncLienGabExtBMCEController")
@SessionScoped
public class AjoutIncLienGabExtBMCEController {

    private String idGab;
    private String ville;
    private Integer ticketLienGabExt;
    private String duree;
    private Date dateDebut;
    private Date dateFin;
    private String commentaire;
    
    private List<IncLienGabExt> listInc;
    private List<IncLienGabExt> listInc2;
    private List<IncApplicatif> filteredIncs;
    private IncLienGabExt selectedInc;



    public AjoutIncLienGabExtBMCEController() {
        listInc = new ArrayList<>();
    }

    public AjoutIncLienGabExtBMCEController(String idGab, String ville, Integer ticketLienGabExt, String duree, Date dateDebut, Date dateFin, String commentaire) {
        this.idGab = idGab;
        this.ville = ville;
        this.ticketLienGabExt = ticketLienGabExt;
        this.duree = duree;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.commentaire = commentaire;
        listInc = new ArrayList<>();
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

    public List<IncLienGabExt> getListInc() {
    	
        return listInc;
    }

    public void setListInc(List<IncLienGabExt> listInc) {
        this.listInc = listInc;
    }

    

   
    public void ajouterInc() {
        IncLienGabExt inc;
        boolean existe = false;
        String messageText;
        IncLienGabExtJpaController Ijc = new IncLienGabExtJpaController(Persistence.createEntityManagerFactory("Test"));
        for (Object listInc1 : listInc) {
            if (((IncLienGabExt) listInc1).getTicketLienGabExt()== ticketLienGabExt) {
                existe = true;
                messageText = "Ticket :" + ticketLienGabExt + ", déjà existant dans la liste!";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, messageText, null);
                FacesContext.getCurrentInstance().addMessage(null, message);
                break;
            }
        }
        if (Ijc.findIncLienGabExtticket(ticketLienGabExt) != null) {
            existe = true;
            messageText = "Ticket :" + ticketLienGabExt + ", déjà existant dans la base de données!";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, messageText, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        if (!existe) {
            inc = new IncLienGabExt(idGab, ville, ticketLienGabExt, Util.Parsers.parseDureeMin(duree), dateDebut, dateFin, commentaire);
            listInc.add(inc);
        }

    }

    public void insertInc(IncLienGabExt inc) throws Exception {
        IncLienGabExtJpaController IJpaC = new IncLienGabExtJpaController(Persistence.createEntityManagerFactory("Test"));
        IJpaC.create(inc);
    }
    public String validerAjout() throws Exception {
        for (int i = 0; i < listInc.size(); i++) {
        	
            insertInc(listInc.get(i));
        }
        listInc.clear();
        return "";
      
    }
    

    public String supprimerInc(IncLienGabExt inc) throws NonexistentEntityException {
        System.out.println(inc);
		listInc.remove(inc);
		return "";
    }


	public List<IncLienGabExt> getListInc2() {
		IncLienGabExtJpaController inc = new IncLienGabExtJpaController(Persistence.createEntityManagerFactory("Test"));
		listInc2 = inc.findIncLienGabExtEntities();
		return listInc2;
	}

	public void setListInc2(List<IncLienGabExt> listInc2) {
		this.listInc2 = listInc2;
	}
	
	 public String modifier() throws NonexistentEntityException, Exception {	
		    
	    	IncLienGabExt  incApp = getSelectedInc();
	    	IncLienGabExtJpaController inc = new IncLienGabExtJpaController(Persistence.createEntityManagerFactory("Test"));
	    	
	    	incApp.setCommentaire(commentaire);
	    	incApp.setDateDebut(dateDebut);
	    	incApp.setDateFin(dateFin);
	    	incApp.setDuree(Parsers.parseDureeMin(duree));
	    	incApp.setIdGab(idGab);
	    	incApp.setVille(ville);
	    	//incApp.setTicketApp(Ticket);
	    	inc.edit(incApp);
	    	return "AffIncLienGabExtBMCE.xhtml";
	    }
	    
	    public String supprimer(IncLienGabExt incLienGabExt) throws NonexistentEntityException {
	    	 IncLienGabExtJpaController inc = new IncLienGabExtJpaController(Persistence.createEntityManagerFactory("Test"));
	    	 inc.destroy(incLienGabExt);	 
	         
	         return "";
	    }

		public List<IncApplicatif> getFilteredIncs() {
			return filteredIncs;
		}

		public void setFilteredIncs(List<IncApplicatif> filteredIncs) {
			this.filteredIncs = filteredIncs;
		}

		public IncLienGabExt getSelectedInc() {
			return selectedInc;
		}

		public void setSelectedInc(IncLienGabExt selectedInc) {
			this.selectedInc = selectedInc;
		}

}
