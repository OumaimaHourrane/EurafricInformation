/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eai.superivsion.historiqueIncidents.ManagedBeans;

import Util.Parsers;
import eai.superivsion.historiqueIncidents.entities.IncApplicatif;
import eai.superivsion.historiqueIncidents.entities.IncInfra;
import eai.superivsion.historiqueIncidents.entities.IncReseau;
import eai.superivsion.historiqueIncidents.jpaControllers.IncApplicatifJpaController;
import eai.superivsion.historiqueIncidents.jpaControllers.IncReseauJpaController;
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

/**
 *
 * @author Mohammed AGHOUI
 */
@ManagedBean(name = "ajoutIncReseauBMCEController")
@SessionScoped
public class AjoutIncReseauBMCEController {

    private int Ticket;
    private String Operateur;
    private String Cdf;
    private String Agence;
    private Date DatePro;
    private String HeurePro;
    private Date DateDebut;
    private String HeureDebut;
    private Date DateReprise;
    private String HeureReprise;
    private String Duree;
    private String TypeLiaison;
    private String Commentaire;
    private List<IncReseau> listInc;
    private List<IncReseau> listInc2;
    private List<IncApplicatif> filteredIncs;
    private IncReseau selectedInc;


    public AjoutIncReseauBMCEController() {
        listInc = new ArrayList<>();
    }

    public AjoutIncReseauBMCEController(int Ticket, String Operateur, String Cdf, String Agence, Date DatePro, String HeurePro, Date DateDebut, String HeureDebut, Date DateReprise, String HeureReprise, String Duree, String TypeLiaison, String Commentaire) {
        
       
        this.Cdf = Cdf;
        this.Duree = Duree;
        this.Ticket = Ticket;
        this.Agence = Agence;
        this.DatePro = DatePro;
        this.HeurePro = HeurePro; 
        this.Operateur = Operateur;
        this.DateDebut = DateDebut;
        this.HeureDebut = HeureDebut;
        this.DateReprise = DateReprise;
        this.TypeLiaison = TypeLiaison;
        this.Commentaire = Commentaire;
        this.HeureReprise = HeureReprise;
        listInc = new ArrayList<>();
    }

    public int getTicket() {
        return Ticket;
    }

    public void setTicket(int Ticket) {
        this.Ticket = Ticket;
    }

    public String getOperateur() {
        return Operateur;
    }

    public void setOperateur(String Operateur) {
        this.Operateur = Operateur;
    }

    public String getCdf() {
        return Cdf;
    }

    public void setCdf(String Cdf) {
        this.Cdf = Cdf;
    }

    public String getAgence() {
        return Agence;
    }

    public void setAgence(String Agence) {
        this.Agence = Agence;
    }

    public Date getDatePro() {
        return DatePro;
    }

    public void setDatePro(Date DatePro) {
        this.DatePro = DatePro;
    }

    public String getHeurePro() {
        return HeurePro;
    }

    public void setHeurePro(String HeurePro) {
        this.HeurePro = HeurePro;
    }

    public Date getDateDebut() {
        return DateDebut;
    }

    public void setDateDebut(Date DateDebut) {
        this.DateDebut = DateDebut;
    }

    public String getHeureDebut() {
        return HeureDebut;
    }

    public void setHeureDebut(String HeureDebut) {
        this.HeureDebut = HeureDebut;
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

    public String getTypeLiaison() {
        return TypeLiaison;
    }

    public void setTypeLiaison(String TypeLiaison) {
        this.TypeLiaison = TypeLiaison;
    }

    public String getCommentaire() {
        return Commentaire;
    }

    public void setCommentaire(String Commentaire) {
        this.Commentaire = Commentaire;
    }

    public List<IncReseau> getListInc() {
        return listInc;
    }

    public void setListInc(List<IncReseau> listInc) {
        this.listInc = listInc;
    }

    
    
    public void ajouterInc() {
        IncReseau inc;
        boolean existe = false;
        String messageText;
        IncReseauJpaController Ijc = new IncReseauJpaController(Persistence.createEntityManagerFactory("Test"));
        for (Object listInc1 : listInc) {
            if (((IncReseau) listInc1).getTicketReseau()== Ticket) {
                existe = true;
                messageText = "Ticket :" + Ticket + ", déjà existant dans la liste!";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, messageText, null);
                FacesContext.getCurrentInstance().addMessage(null, message);
                break;
            }
        }
        if (Ijc.findIncReseauticket(Ticket) != null) {
            existe = true;
            messageText = "Ticket :" + Ticket + ", déjà existant dans la base de données!";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, messageText, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        if (!existe) {
            inc = new IncReseau(Ticket, Operateur, Cdf, Agence,Parsers.setDateTime(DatePro, HeurePro), 
                    Parsers.setDateTime(DateDebut, HeureDebut),Parsers.setDateTime(DateReprise, HeureReprise),
                    Parsers.parseDureeMin(Duree), TypeLiaison, Commentaire);
            listInc.add(inc);
        }

    }

    public void insertInc(IncReseau inc) throws Exception {
        IncReseauJpaController IJpaC = new IncReseauJpaController(Persistence.createEntityManagerFactory("Test"));
        IJpaC.create(inc);
    }
    public String validerAjout() throws Exception {
        for (int i = 0; i < listInc.size(); i++) {
        	
            insertInc(listInc.get(i));
        }
        listInc.clear();
        return "";
      
    }
    

    public String supprimerInc(IncReseau inc) throws NonexistentEntityException {
        System.out.println(inc);
		listInc.remove(inc);
		return "";
    }



	public List<IncReseau> getListInc2() {
		IncReseauJpaController inc = new IncReseauJpaController(Persistence.createEntityManagerFactory("Test"));
		listInc2 = inc.findIncReseauEntities();
		return listInc2;
	}

	public void setListInc2(List<IncReseau> listInc2) {
		this.listInc2 = listInc2;
	}
	
	 public String modifier() throws NonexistentEntityException, Exception {	
		    
	    	IncReseau incApp = getSelectedInc();
	    	IncReseauJpaController inc = new IncReseauJpaController(Persistence.createEntityManagerFactory("Test"));
	    	
	    	incApp.setAgence(Agence);
	    	incApp.setCdf(Cdf);
	    	incApp.setDateDebut(Parsers.setDateTime(DateDebut, HeureDebut));
	    	incApp.setCommentaire(Commentaire);
	    	incApp.setDateFin(Parsers.setDateTime(DateReprise, HeureReprise));
	    	incApp.setDuree(Parsers.parseDureeMin(Duree));
	    	incApp.setDatePro(Parsers.setDateTime(DateReprise, HeureReprise));
	    	incApp.setOperateur(Operateur);
	    	incApp.setTypeLiaison(TypeLiaison);
	    	inc.edit(incApp);
	    	return "AffIncReseauBMCE.xhtml";
	    }
	    public String supprimer(IncReseau incReseau) throws NonexistentEntityException {
	    	 IncReseauJpaController inc = new IncReseauJpaController(Persistence.createEntityManagerFactory("Test"));
	    	 inc.destroy(incReseau);	 
	         
	         return "";
	    }

		public List<IncApplicatif> getFilteredIncs() {
			return filteredIncs;
		}

		public void setFilteredIncs(List<IncApplicatif> filteredIncs) {
			this.filteredIncs = filteredIncs;
		}

		public IncReseau getSelectedInc() {
			return selectedInc;
		}

		public void setSelectedInc(IncReseau selectedInc) {
			this.selectedInc = selectedInc;
		}

}
