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
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import eai.superivsion.historiqueIncidents.entities.IncGabRat;
import eai.superivsion.historiqueIncidents.jpaControllers.exceptions.NonexistentEntityException;
import eai.superivsion.historiqueIncidents.jpaControllers.exceptions.PreexistingEntityException;

/**
 *
 * @author Mohammed AGHOUI
 */
public class IncGabRatJpaController implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IncGabRatJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(IncGabRat incGabRat) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(incGabRat);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findIncGabRat(incGabRat.getTicketGabRat()) != null) {
                throw new PreexistingEntityException("IncGabRat " + incGabRat + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(IncGabRat incGabRat) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            incGabRat = em.merge(incGabRat);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = incGabRat.getTicketGabRat();
                if (findIncGabRat(id) == null) {
                    throw new NonexistentEntityException("The incGabRat with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(IncGabRat incGabRat) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            
            if (!em.contains(incGabRat)) {
                incGabRat = em.merge(incGabRat);
            }
            em.remove(incGabRat);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<IncGabRat> findIncGabRatEntities() {
        return findIncGabRatEntities(true, -1, -1);
    }

    public List<IncGabRat> findIncGabRatEntities(int maxResults, int firstResult) {
        return findIncGabRatEntities(false, maxResults, firstResult);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	private List<IncGabRat> findIncGabRatEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(IncGabRat.class));
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

    public IncGabRat findIncGabRat(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(IncGabRat.class, id);
        } finally {
            em.close();
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public int getIncGabRatCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<IncGabRat> rt = cq.from(IncGabRat.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public IncGabRat findIncGabRatticket(int ticket) {
        EntityManager em = getEntityManager();
        IncGabRat resultat = null;
        try {
            StringBuilder sb = new StringBuilder();

            sb.append("SELECT u FROM IncGabRat u WHERE u.ticketGabRat = " + ticket);

            Query query = em.createQuery(sb.toString());

            // query.setParameter("ticketApp", ticket);
            if (query.getResultList() != null && query.getResultList().size() > 0) {
                resultat = (IncGabRat) query.getResultList().get(0);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultat;
    }

}
