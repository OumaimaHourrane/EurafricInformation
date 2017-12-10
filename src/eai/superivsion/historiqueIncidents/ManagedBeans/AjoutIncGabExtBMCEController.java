/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates.
 * and open the template in the editor.
 */
package eai.superivsion.historiqueIncidents.ManagedBeans;

import Util.Parsers;
import eai.superivsion.historiqueIncidents.entities.IncApplicatif;
import eai.superivsion.historiqueIncidents.entities.IncGabExt;
import eai.superivsion.historiqueIncidents.jpaControllers.IncGabExtJpaController;
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
@ManagedBean(name = "ajoutIncGabExtBMCEController")
@SessionScoped
public class AjoutIncGabExtBMCEController {

    private String idGab;
    private String ville;
    private String adresseIp;
    private Integer ticketGabExt;
    private String duree;
    private Date dateDebut;
    private Date dateFin;
    private String etat;
    private IncGabExt selectedInc;
    int n;
    private String commentaire;
    private List<IncApplicatif> filteredIncs;

    
    private List<IncGabExt> listInc;
    private List<IncGabExt> listInc2;

    public AjoutIncGabExtBMCEController() {
        listInc = new ArrayList<>();
    }

    public AjoutIncGabExtBMCEController(String idGab, String ville, Integer ticketGabExt, String duree, Date dateDebut, Date dateFin, String commentaire) {
        this.idGab = idGab;
        this.ville = ville;
        this.ticketGabExt = ticketGabExt;
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

    public String getAdresseIp() {
        return adresseIp;
    }

    public void setAdresseIp(String adresseIp) {
        this.adresseIp = adresseIp;
    }

    public Integer getTicketGabExt() {
        return ticketGabExt;
    }

    public void setTicketGabExt(Integer ticketGabExt) {
        this.ticketGabExt = ticketGabExt;
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

    public List<IncGabExt> getListInc() {
        return listInc;
    }

    public void setListInc(List<IncGabExt> listInc) {
        this.listInc = listInc;
    }

    public void ajouterInc() {
        IncGabExt inc;
        boolean existe = false;
        String messageText;
        IncGabExtJpaController Ijc = new IncGabExtJpaController(Persistence.createEntityManagerFactory("Test"));
        for (Object listInc1 : listInc) {
            if (((IncGabExt) listInc1).getTicketGabExt()== ticketGabExt) {
                existe = true;
                messageText = "Ticket :" + ticketGabExt + ", déjà existant dans la liste!";
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, messageText, null);
                FacesContext.getCurrentInstance().addMessage(null, message);
                break;
            }
        }
        if (Ijc.findIncGabExtticket(ticketGabExt) != null) {
            existe = true;
            messageText = "Ticket :" + ticketGabExt + ", déjà existant dans la base de données!";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, messageText, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        if (!existe) {
            inc = new IncGabExt(idGab, adresseIp, ville, ticketGabExt, Parsers.parseDureeMin(duree), etat, dateDebut, dateFin, commentaire);
            listInc.add(inc);
        }

    }

    public void insertInc(IncGabExt inc) throws Exception {
        IncGabExtJpaController IJpaC = new IncGabExtJpaController(Persistence.createEntityManagerFactory("Test"));
        IJpaC.create(inc);
    }

    public String validerAjout() throws Exception {
        for (int i = 0; i < listInc.size(); i++) {
        	
            insertInc(listInc.get(i));
        }
        listInc.clear();
        return "";
      
    }
    

    public String supprimerInc(IncGabExt inc) throws NonexistentEntityException {
        System.out.println(inc);
		listInc.remove(inc);
		return "";
    }

   
   
    
    public String modifier() throws NonexistentEntityException, Exception {	
    	
    	IncGabExt incGE = getSelectedInc();
    	System.out.println("::::::::::"+incGE);
    	IncGabExtJpaController inc = new IncGabExtJpaController(Persistence.createEntityManagerFactory("Test"));
    	
    	incGE.setAdresseIp(adresseIp);
    	incGE.setCommentaire(commentaire);
    	incGE.setDateDebut(dateDebut);
    	incGE.setDateFin(dateFin);
    	incGE.setDuree(Parsers.parseDureeMin(duree));
    	incGE.setEtat(etat);
    	incGE.setIdGab(idGab);
    	//incGE.setTicketGabExt(ticketGabExt);
    	incGE.setVille(ville);

    	inc.edit(incGE);
    	return "AffIncGabExtBMCE.xhtml";
    }
   
    public String supprimer(IncGabExt incGabExt) throws NonexistentEntityException {
    	 System.out.println("-----------!!!!!!"+incGabExt.getCommentaire());
    	 IncGabExtJpaController inc = new IncGabExtJpaController(Persistence.createEntityManagerFactory("Test"));
    	 inc.destroy(incGabExt);
    	 System.out.println("-----------!!!!!!"+incGabExt.getCommentaire());
         return "";
    }

	public List<IncGabExt> getListInc2() {
		IncGabExtJpaController inc = new IncGabExtJpaController(Persistence.createEntityManagerFactory("Test"));
		listInc2 = inc.findIncGabExtEntities();
		return listInc2;
	}

	public void setListInc2(List<IncGabExt> listInc2) {
		this.listInc2 = listInc2;
	}

	public List<IncApplicatif> getFilteredIncs() {
		return filteredIncs;
	}

	public void setFilteredIncs(List<IncApplicatif> filteredIncs) {
		this.filteredIncs = filteredIncs;
	}

	public IncGabExt getSelectedInc() {
		return selectedInc;
	}

	public void setSelectedInc(IncGabExt selectedInc) {
		this.selectedInc = selectedInc;
	}
}
