/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eai.superivsion.historiqueIncidents.ManagedBeans;

import Util.Parsers;
import eai.superivsion.historiqueIncidents.entities.IncInfra;
import eai.superivsion.historiqueIncidents.jpaControllers.IncInfraJpaController;
import eai.superivsion.historiqueIncidents.jpaControllers.exceptions.NonexistentEntityException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.Persistence;


/**
 *
 * @author Mohammed AGHOUI
 */
@ManagedBean(name = "ajoutIncInfraBMCEController")
@SessionScoped
public class AjoutIncInfraBMCEController {

    private IncInfra selectedInc;

    private String Equipement;
    private int Ticket;
    private Date DateIncident;
    private String HeureIncident;
    private Date DateReprise;
    private String HeureReprise;
    private String Duree;
    private String Disponibilite;
    private String Commentaire;
    private List<IncInfra> listInc;
    
    private List<IncInfra> listInc2;

    private List<IncInfra> filteredIncs;

    public AjoutIncInfraBMCEController() {
        listInc = new ArrayList<>();
    }

    

    public AjoutIncInfraBMCEController( String equipement, int Ticket, Date DateIncident, String HeureIncident, Date DateReprise, String HeureReprise, String Duree, String Disponibilite, String Commentaire) {
        
        this.Equipement = equipement;
        this.Ticket = Ticket;
        this.DateIncident = DateIncident;
        this.HeureIncident = HeureIncident;
        this.DateReprise = DateReprise;
        this.HeureReprise = HeureReprise;
        this.Duree = Duree;
        this.Disponibilite = Disponibilite;
        this.Commentaire = Commentaire;
        listInc = new ArrayList<>();
    }

    public void AjoutIncInfraBMCE() {
        System.out.println(DateIncident);
    }

    public int getTicket() {
        return Ticket;
    }

    public void setTicket(int Ticket) {
        this.Ticket = Ticket;
    }

    public String getEquipement() {
        return Equipement;
    }

    public void setEquipement(String Equipement) {
        this.Equipement = Equipement;
    }

    public Date getDateIncident() {
        return DateIncident;
    }

    public void setDateIncident(Date DateIncident) {
        this.DateIncident = DateIncident;
    }

    public String getHeureIncident() {
        return HeureIncident;
    }

    public void setHeureIncident(String HeureIncident) {
        this.HeureIncident = HeureIncident;
    }

    public Date getDateReprise() {
        return DateReprise;
    }

    public void setDateReprise(Date DateReprise) {
        this.DateReprise = DateReprise;
    }

    public String getHeureReprise() {
        return HeureReprise;
    }

    public void setHeureReprise(String HeureReprise) {
        this.HeureReprise = HeureReprise;
    }

    public String getDuree() {
        return Duree;
    }

    public void setDuree(String Duree) {
        this.Duree = Duree;
    }

    public String getDisponibilite() {
        return Disponibilite;
    }

    public void setDisponibilite(String Disponibilite) {
        this.Disponibilite = Disponibilite;
    }

    public String getCommentaire() {
        return Commentaire;
    }

    public void setCommentaire(String Commentaire) {
        this.Commentaire = Commentaire;
    }

    public List<IncInfra> getListInc() {
        return listInc;
    }

    public void setListInc(List<IncInfra> listInc) {
        this.listInc = listInc;
    }

    public void ajouterInc() {

    	System.out.println("ajout ------------------------------");
        IncInfra inc;
        boolean existe = false;
        String messageText;
        IncInfraJpaController Ijc = new IncInfraJpaController(Persistence.createEntityManagerFactory("Test"));
        for (Object listInc1 : listInc) {
            if (((IncInfra) listInc1).getTicketInfra()== Ticket) {
            	
                existe = true;
                messageText = "Ticket :" + Ticket + ", déjà existant dans la liste!";
               // messageText = "Ticket :" + Ticket + ", déjà existant dans la liste!";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, messageText, null);
                FacesContext.getCurrentInstance().addMessage(null, message);
                break;
            }
        }
        if (Ijc.findIncInfraticket(Ticket) != null) {
            existe = true;
            messageText = "Ticket :" + Ticket + ", déjà existant dans la base de données!";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, messageText, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        if (!existe) {
            inc = new IncInfra(Ticket, Equipement, Parsers.setDateTime(DateIncident, HeureIncident), 
                    Parsers.setDateTime(DateReprise, HeureReprise),Parsers.parseDureeMin(Duree), Parsers.parseDisp(Disponibilite), Commentaire);
            listInc.add(inc);
        }

    }

    public void insertInc(IncInfra inc) throws Exception {
        IncInfraJpaController IJpaC = new IncInfraJpaController(Persistence.createEntityManagerFactory("Test"));
        IJpaC.create(inc);
    }

    public String validerAjout() throws Exception {
        for (int i = 0; i < listInc.size(); i++) {
        	
            insertInc(listInc.get(i));
        }
        listInc.clear();
        return "";
      
    }
    

    public String supprimerInc(IncInfra inc) throws NonexistentEntityException {
        System.out.println(inc);
		listInc.remove(inc);
		return "";
    }

	public List<IncInfra> getListInc2() {
		IncInfraJpaController inc = new IncInfraJpaController(Persistence.createEntityManagerFactory("Test"));
		listInc2 = inc.findIncInfraEntities();
		return listInc2;
	}

	public void setListInc2(List<IncInfra> listInc2) {
		this.listInc2 = listInc2;
	}
	
	public String modifier() throws NonexistentEntityException, Exception {	
	    
    	IncInfra incApp = getSelectedInc();
    	IncInfraJpaController inc = new IncInfraJpaController(Persistence.createEntityManagerFactory("Test"));
    	

    	incApp.setCommentaire(Commentaire);
    	incApp.setDateDebut(Parsers.setDateTime(DateIncident, HeureIncident));
    	incApp.setDateFin(Parsers.setDateTime(DateReprise, HeureReprise));
    	incApp.setDisponibilite(Parsers.parseDisp(Disponibilite));;
    	incApp.setDuree(Parsers.parseDureeMin(Duree));
    	incApp.setEquipement(Equipement);
    	//incApp.setTicketApp(Ticket);
    	inc.edit(incApp);
    	return "AffIncInfraBMCE.xhtml";
    }
    

	    public String supprimer(IncInfra incInfra) throws NonexistentEntityException {
	    	 IncInfraJpaController inc = new IncInfraJpaController(Persistence.createEntityManagerFactory("Test"));
	    	 inc.destroy(incInfra);	 
	         
	         return "";
	    }

		public List<IncInfra> getFilteredIncs() {
			return filteredIncs;
		}

		public void setFilteredIncs(List<IncInfra> filteredIncs) {
			this.filteredIncs = filteredIncs;
		}



		public IncInfra getSelectedInc() {
			return selectedInc;
		}



		public void setSelectedInc(IncInfra selectedInc) {
			this.selectedInc = selectedInc;
		}
	
}
