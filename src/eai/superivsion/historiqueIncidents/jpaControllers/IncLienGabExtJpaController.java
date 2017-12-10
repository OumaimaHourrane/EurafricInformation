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
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import eai.superivsion.historiqueIncidents.entities.IncLienGabExt;
import eai.superivsion.historiqueIncidents.jpaControllers.exceptions.NonexistentEntityException;
import eai.superivsion.historiqueIncidents.jpaControllers.exceptions.PreexistingEntityException;

/**
 *
 * @author Mohammed AGHOUI
 */
public class IncLienGabExtJpaController implements Serializable {

    public IncLienGabExtJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(IncLienGabExt incLienGabExt) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(incLienGabExt);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findIncLienGabExt(incLienGabExt.getTicketLienGabExt()) != null) {
                throw new PreexistingEntityException("IncLienGabExt " + incLienGabExt + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(IncLienGabExt incLienGabExt) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            incLienGabExt = em.merge(incLienGabExt);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = incLienGabExt.getTicketLienGabExt();
                if (findIncLienGabExt(id) == null) {
                    throw new NonexistentEntityException("The incLienGabExt with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(IncLienGabExt incLienGabExt ) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
       
            if (!em.contains(incLienGabExt)) {
                incLienGabExt = em.merge(incLienGabExt);
            }
            em.remove(incLienGabExt);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<IncLienGabExt> findIncLienGabExtEntities() {
        return findIncLienGabExtEntities(true, -1, -1);
    }

    public List<IncLienGabExt> findIncLienGabExtEntities(int maxResults, int firstResult) {
        return findIncLienGabExtEntities(false, maxResults, firstResult);
    }

    private List<IncLienGabExt> findIncLienGabExtEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(IncLienGabExt.class));
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

    public IncLienGabExt findIncLienGabExt(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(IncLienGabExt.class, id);
        } finally {
            em.close();
        }
    }

    public int getIncLienGabExtCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<IncLienGabExt> rt = cq.from(IncLienGabExt.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public IncLienGabExt findIncLienGabExtticket(int ticket) {
        EntityManager em = getEntityManager();
        IncLienGabExt resultat = null;
        try {
            StringBuilder sb = new StringBuilder();

            sb.append("SELECT u FROM IncLienGabExt u WHERE u.ticketLienGabExt = " + ticket);

            System.out.println("" + sb);

            Query query = em.createQuery(sb.toString());

            // query.setParameter("ticketApp", ticket);
            if (query.getResultList() != null && query.getResultList().size() > 0) {
                resultat = (IncLienGabExt) query.getResultList().get(0);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultat;

    }

}
