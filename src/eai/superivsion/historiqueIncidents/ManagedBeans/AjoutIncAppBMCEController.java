/*


 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eai.superivsion.historiqueIncidents.ManagedBeans;

import eai.superivsion.historiqueIncidents.entities.IncApplicatif;
import eai.superivsion.historiqueIncidents.jpaControllers.IncApplicatifJpaController;
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

@ManagedBean(name = "ajoutIncAppBMCEController")
@SessionScoped
public class AjoutIncAppBMCEController {

    private String Application;
    private String Plateformes;
    private int Ticket;
    private Date DateIncident;
    private String HeureIncident;
    private Date DateReprise;
    private Date DateDebut;
    private Date DateFin;
    private String HeureReprise;
    private String Duree;
    private String Disponibilite;
    private String Commentaire;
    private boolean editable;
    private List<IncApplicatif> listInc;
    private List<IncApplicatif> listInc2;
    private List<IncApplicatif> filteredIncs;
    private IncApplicatif selectedInc;
    
    
    //Controller 
    public AjoutIncAppBMCEController() {
        listInc = new ArrayList<>();
    }

    public AjoutIncAppBMCEController(String Application, String Plateformes, int Ticket, Date DateIncident, String HeureIncident, Date DateReprise, String HeureReprise, String Duree, String Disponibilite, String Commentaire) {
        
    	this.Application = Application;
        this.Plateformes = Plateformes;
        this.Ticket = Ticket;
        this.DateIncident = DateIncident;
        this.HeureIncident = HeureIncident;
        this.DateReprise = DateReprise;
        this.HeureReprise = HeureReprise;
        this.Duree = Duree;
        this.Disponibilite = Disponibilite;
        this.Commentaire = Commentaire;
        
    }

    public String saveAction() throws NonexistentEntityException, Exception {
	    
		//get all existing value but set "editable" to false 
		for (IncApplicatif incapp : listInc2){
			IncApplicatifJpaController inc = new IncApplicatifJpaController(Persistence.createEntityManagerFactory("Test"));
			inc.edit(incapp);
			setEditable(false);
			System.out.println("incident edité : "+inc.getIncApplicatifCount());
		}
		//return to current page
		return null;
		
	}
	
	public String editAction(IncApplicatif incapp) {
	    
		setEditable(true);
		return null;
	}
    //Getter  & Setter
    
    public List<IncApplicatif> getListInc() {
        return listInc;
    }

    public void setListInc(List<IncApplicatif> listInc) {
        this.listInc = listInc;
    }
    
    
    public void AjoutIncAppBMCE() {
        System.out.println(DateIncident);
    }

    public String getApplication() {
        return Application;
    }

    public void setApplication(String Application) {
        this.Application = Application;
    }

    public String getPlateformes() {
        return Plateformes;
    }

    public void setPlateformes(String Plateformes) {
        this.Plateformes = Plateformes;
    }

    public int getTicket() {
        return Ticket;
    }

    public void setTicket(int Ticket) {
        this.Ticket = Ticket;
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

    public void ajouterInc() {
    	
        IncApplicatif inc;
        boolean existe = false;
        String messageText;
        IncApplicatifJpaController Ijc =  new IncApplicatifJpaController(Persistence.createEntityManagerFactory("Test"));
        for (Object listInc1 : listInc) {
            if (((IncApplicatif) listInc1).getTicketApp() == Ticket) {
                existe = true;
                messageText = "Ticket :"+ Ticket + ", déjà  existant dans la liste!";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, messageText, null);
                FacesContext.getCurrentInstance().addMessage(null, message);
                break;
            }
        }
        if(Ijc.findIncApplicatifticket(Ticket)!=null){
                existe = true;
                messageText = "Ticket :"+ Ticket + ", déjà  existant dans la base de données!";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, messageText, null);
                FacesContext.getCurrentInstance().addMessage(null, message);            
        }
        if (!existe) {
            inc = new IncApplicatif(Ticket, Application, Plateformes, Parsers.setDateTime(DateIncident, HeureIncident), Parsers.setDateTime(DateReprise, HeureReprise),
                    Parsers.parseDureeMin(Duree), Parsers.parseDisp(Disponibilite), Commentaire);
            listInc.add(inc);
        }

    }

    public void insertInc(IncApplicatif inc) throws Exception {
        IncApplicatifJpaController IJpaC = new IncApplicatifJpaController(Persistence.createEntityManagerFactory("Test"));
        IJpaC.create(inc);
    }
    
   
    public String validerAjout() throws Exception {
        for (int i = 0; i < listInc.size(); i++) {
        	
            insertInc(listInc.get(i));
        }
        listInc.clear();
        return "";
      
    }

    public String supprimerInc(IncApplicatif inc) throws NonexistentEntityException {
        System.out.println(inc);
		listInc.remove(inc);
		return "";
    }

	public List<IncApplicatif> getListInc2() {
		IncApplicatifJpaController inc = new IncApplicatifJpaController(Persistence.createEntityManagerFactory("Test"));
		listInc2 = inc.findIncApplicatifEntities();
		return listInc2;
	}

	public void setListInc2(List<IncApplicatif> listInc2) {
		this.listInc2 = listInc2;
	}

	public List<IncApplicatif> getFilteredIncs() {
		return filteredIncs;
	}

	public void setFilteredIncs(List<IncApplicatif> filteredIncs) {
		this.filteredIncs = filteredIncs;
	}
	
    
    public String modifier() throws NonexistentEntityException, Exception {	
    
    	IncApplicatif incApp = getSelectedInc();
    	IncApplicatifJpaController inc = new IncApplicatifJpaController(Persistence.createEntityManagerFactory("Test"));
    	
    	incApp.setApplication(Application);
    	incApp.setCommentaire(Commentaire);
    	incApp.setDateDebut(Parsers.setDateTime(DateIncident, HeureIncident));
    	incApp.setDateFin(Parsers.setDateTime(DateReprise, HeureReprise));
    	incApp.setDisponibilite(Parsers.parseDisp(Disponibilite));;
    	incApp.setDuree(Parsers.parseDureeMin(Duree));
    	incApp.setPlateforme(Plateformes);
    	//incApp.setTicketApp(Ticket);
    	inc.edit(incApp);
    	return "AffIncAppBMCE.xhtml";
    }
    
    
   	public String supprimer(IncApplicatif inc) throws NonexistentEntityException {
	    	System.out.println("-----------!!!!!!"+inc.getCommentaire());
	    	IncApplicatifJpaController incc = new IncApplicatifJpaController(Persistence.createEntityManagerFactory("Test"));
	    	incc.destroy(inc);
	    	System.out.println("-----------!!!!!!"+inc.getCommentaire());
		    return "";
	    }

		public Date getDateDebut() {
			return DateDebut;
		}

		public void setDateDebut(Date dateDebut) {
			DateDebut = dateDebut;
		}

		public Date getDateFin() {
			return DateFin;
		}

		public void setDateFin(Date dateFin) {
			DateFin = dateFin;
		}

		public boolean isEditable() {
			return editable;
		}

		public void setEditable(boolean editable) {
			this.editable = editable;
		}

		public IncApplicatif getSelectedInc() {
			return selectedInc;
		}

		public void setSelectedInc(IncApplicatif selectedInc) {
			this.selectedInc = selectedInc;
		}
	    
}
