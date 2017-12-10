/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eai.superivsion.historiqueIncidents.ManagedBeans;

import Util.Parsers;
import eai.superivsion.historiqueIncidents.entities.IncApplicatif;

import eai.superivsion.historiqueIncidents.entities.IncInterface;
import eai.superivsion.historiqueIncidents.jpaControllers.IncInterfaceJpaController;
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
@ManagedBean(name = "ajoutIncInterfaceBMCEController")
@SessionScoped
public class AjoutIncInterfaceBMCEController {

    
    private Integer ticketInt;
    private String cdf;
    private String agence;
    private String interface1;
    private int nbrInc;
    private String disponibilite;
    private Date dateDebut;
    private String heureIncident;
    private String duree;
    private String cumule;
    private String operateur;
    private List<IncInterface> listInc;
    private List<IncInterface> listInc2;
    private List<IncApplicatif> filteredIncs;
    private IncInterface selectedInc;

    
    public String getHeureIncident() {
        return heureIncident;
    }

    public void setHeureIncident(String heureIncident) {
        this.heureIncident = heureIncident;
    }


    public AjoutIncInterfaceBMCEController() {
        listInc = new ArrayList<>();
    }

    public AjoutIncInterfaceBMCEController(Integer ticketInt, String cdf, String agence, String interface1, int nbrInc, String disponibilite, Date dateDebut, String duree, String cumule, String operateur) {
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
        listInc = new ArrayList<>();
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

    public String getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(String disponibilite) {
        this.disponibilite = disponibilite;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public String getCumule() {
        return cumule;
    }

    public void setCumule(String cumule) {
        this.cumule = cumule;
    }

    public String getOperateur() {
        return operateur;
    }

    public void setOperateur(String operateur) {
        this.operateur = operateur;
    }

    public List<IncInterface> getListInc() {
        return listInc;
    }

    public void setListInc(List<IncInterface> listInc) {
        this.listInc = listInc;
    }



    
    
    public void ajouterInc() {
        IncInterface inc;
        boolean existe = false;
        String messageText;
        IncInterfaceJpaController Ijc = new IncInterfaceJpaController(Persistence.createEntityManagerFactory("Test"));
        for (Object listInc1 : listInc) {
            if (((IncInterface) listInc1).getTicketInt()== ticketInt) {
                existe = true;
                messageText = "Ticket :" + ticketInt + ", déjà existant dans la liste!";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, messageText, null);
                FacesContext.getCurrentInstance().addMessage(null, message);
                break;
            }
        }
        if (Ijc.findIncInterfaceticket(ticketInt) != null) {
            existe = true;
            messageText = "Ticket :" + ticketInt + ", déjà existant dans la base de données!";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, messageText, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        if (!existe) {
            inc = new IncInterface(ticketInt, cdf, agence, interface1, nbrInc, Parsers.parseDisp(disponibilite), Parsers.setDateTime(dateDebut, heureIncident), Parsers.parseDureeMin(duree), Parsers.parseDureeMin(cumule), operateur);
            listInc.add(inc);
        }

    }

    public void insertInc(IncInterface inc) throws Exception {
        IncInterfaceJpaController IJpaC = new IncInterfaceJpaController(Persistence.createEntityManagerFactory("Test"));
        IJpaC.create(inc);
    }
    public String validerAjout() throws Exception {
        for (int i = 0; i < listInc.size(); i++) {
        	
            insertInc(listInc.get(i));
        }
        listInc.clear();
        return "";
      
    }
    

    public String supprimerInc(IncInterface inc) throws NonexistentEntityException {
        System.out.println(inc);
		listInc.remove(inc);
		return "";
    }


   
	public List<IncInterface> getListInc2() {
		IncInterfaceJpaController inc= new IncInterfaceJpaController(Persistence.createEntityManagerFactory("Test"));
		listInc2 = inc.findIncInterfaceEntities();
		return listInc2;
	}

	public void setListInc2(List<IncInterface> listInc2) {
		this.listInc2 = listInc2;
	}

	public List<IncApplicatif> getFilteredIncs() {
		return filteredIncs;
	}

	public void setFilteredIncs(List<IncApplicatif> filteredIncs) {
		this.filteredIncs = filteredIncs;
	}
	public String modifier() throws NonexistentEntityException, Exception {	
	    
    	IncInterface incInt = getSelectedInc();
    	IncInterfaceJpaController inc = new IncInterfaceJpaController(Persistence.createEntityManagerFactory("Test"));
    	
    
    	incInt.setAgence(agence);
    	incInt.setCdf(cdf);
    	incInt.setCumule(Parsers.parseDureeMin(cumule));
    	incInt.setDateDebut(dateDebut);
    	incInt.setDisponibilite(Parsers.parseDisp(disponibilite));
    	incInt.setDuree(Parsers.parseDureeMin(duree));
    	incInt.setInterface1(interface1);
    	incInt.setNbrInc(nbrInc);
    	incInt.setOperateur(operateur);
    	
    	//incApp.setTicketApp(Ticket);
    	inc.edit(incInt);
    	return "AffIncInterfaceBMCE.xhtml";
    }
	    public String supprimer(IncInterface incInterface) throws NonexistentEntityException {
	    	 IncInterfaceJpaController inc = new IncInterfaceJpaController(Persistence.createEntityManagerFactory("Test"));
	    	 inc.destroy(incInterface);
	         return "";
	    }

		public IncInterface getSelectedInc() {
			return selectedInc;
		}

		public void setSelectedInc(IncInterface selectedInc) {
			this.selectedInc = selectedInc;
		}

}
