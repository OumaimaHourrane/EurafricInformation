/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eai.superivsion.historiqueIncidents.ManagedBeans;

import Util.Sec;
import eai.superivsion.historiqueIncidents.entities.Utilisateur;
import eai.superivsion.historiqueIncidents.jpaControllers.UtilisateurJpaController;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.Persistence;

import javax.servlet.http.HttpSession;

/**
 *
 * @author Mohammed AGHOUI
 */
@ManagedBean(name = "autentificationController")
@RequestScoped
public class AuthentificationController {

    private String mail;
    private String password;

    //@ManagedProperty(value = "#{utilisateur}")
    private UtilisateurController user;

    /**
     * Creates a new instance of AutentificationController
     */
    public AuthentificationController() {

    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String authentifier() {

        UtilisateurJpaController UJpaC = new UtilisateurJpaController(Persistence.createEntityManagerFactory("Test"));
        Utilisateur utilisateur = UJpaC.findUtilisateur(mail);
        FacesMessage facesMsg;
        if (utilisateur != null && utilisateur.getPassword().equals(Sec.sha256(password))) {
            user = new UtilisateurController(utilisateur);
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            session.setAttribute("user", user);
            System.out.println("--------------------------------------!!!!!!!!!!!!!!!!!!!----------------------------");
            return "AjoutIncAppBMCE.xhtml";

        } else {
            facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mail ou mot de passe incorrect", null);
            FacesContext.getCurrentInstance().addMessage(null, facesMsg);
            return "";
        }

    }

    public String deconnecter() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        session.invalidate();
        System.out.println("deconnection, session : " + session);
        return "logout";
    }

}
