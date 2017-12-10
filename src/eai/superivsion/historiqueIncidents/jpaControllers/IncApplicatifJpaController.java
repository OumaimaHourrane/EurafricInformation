/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eai.superivsion.historiqueIncidents.jpaControllers;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import eai.superivsion.historiqueIncidents.entities.IncApplicatif;
import eai.superivsion.historiqueIncidents.jpaControllers.exceptions.NonexistentEntityException;
import eai.superivsion.historiqueIncidents.jpaControllers.exceptions.PreexistingEntityException;

/**
 *
 * @author Mohammed AGHOUI
 */

public class IncApplicatifJpaController implements Serializable {

	private static final long serialVersionUID = 1L;

	public IncApplicatifJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(IncApplicatif incApplicatif) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        System.out.println("------- : "+incApplicatif.getApplication());
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(incApplicatif);
            em.getTransaction().commit();
            System.out.println("------- : "+incApplicatif.getApplication());
        } catch (Exception ex) {
            if (findIncApplicatif(incApplicatif.getTicketApp()) != null) {
                throw new PreexistingEntityException("IncApplicatif " + incApplicatif + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(IncApplicatif incApplicatif) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            incApplicatif = em.merge(incApplicatif);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = incApplicatif.getTicketApp();
                if (findIncApplicatif(id) == null) {
                    throw new NonexistentEntityException("The incApplicatif with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(IncApplicatif incApplicatif) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            
            if (!em.contains(incApplicatif)) {
                incApplicatif = em.merge(incApplicatif);
            }

            em.remove(incApplicatif);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<IncApplicatif> findIncApplicatifEntities() {
        return findIncApplicatifEntities(true, -1, -1);
    }

    public List<IncApplicatif> findIncApplicatifEntities(int maxResults, int firstResult) {
        return findIncApplicatifEntities(false, maxResults, firstResult);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	private List<IncApplicatif> findIncApplicatifEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
        	CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery();
            cq.select(cq.from(IncApplicatif.class));
            cq.orderBy(cb.desc(cq.from(IncApplicatif.class).get("duree"))); 

            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public IncApplicatif findIncApplicatif(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(IncApplicatif.class, id);
        } finally {
            em.close();
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public int getIncApplicatifCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<IncApplicatif> rt = cq.from(IncApplicatif.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } 
        finally {
            em.close();
        }
    }
        public IncApplicatif findIncApplicatifticket(int ticket) {
        EntityManager em = getEntityManager();
         IncApplicatif resultat = null;
        try {
            StringBuilder sb = new StringBuilder();
            
            sb.append("SELECT u FROM IncApplicatif u WHERE u.ticketApp = "+ticket);
            System.out.println(" " + sb);
            Query query = em.createQuery(sb.toString());
            
           // query.setParameter("ticketApp", ticket);
            if (query.getResultList() != null && query.getResultList().size() > 0) {
                resultat =   (IncApplicatif)query.getResultList().get(0);
            }            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultat;   
    }
}
