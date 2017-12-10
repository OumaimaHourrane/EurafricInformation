/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eai.superivsion.historiqueIncidents.ManagedBeans;

import eai.superivsion.historiqueIncidents.entities.Utilisateur;
import eai.superivsion.historiqueIncidents.jpaControllers.UtilisateurJpaController;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.Persistence;

/**
 *
 * @author Mohammed AGHOUI
 */
@ManagedBean(name = "ajouterUtilisateurController")
@RequestScoped
public class AjouterUtilisateurController {
    private String mail;
    private String nom;
    private String prenom;
    private String role;
    private String password;
    private String passwordvalidation;

    public AjouterUtilisateurController() {
    }

    public void ajouterUtilisateur(){
        UtilisateurJpaController UJpaC = new UtilisateurJpaController(Persistence.createEntityManagerFactory("Test"));
        Utilisateur u ;
        u = new Utilisateur( mail, Util.Sec.sha256(password), nom, prenom, role);
        UJpaC.create(u);
        
    }
    
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordvalidation() {
        return passwordvalidation;
    }

    public void setPasswordvalidation(String passwordvalidation) {
        this.passwordvalidation = passwordvalidation;
    }
    
}
