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

import eai.superivsion.historiqueIncidents.entities.IncInfra;
import eai.superivsion.historiqueIncidents.jpaControllers.exceptions.NonexistentEntityException;
import eai.superivsion.historiqueIncidents.jpaControllers.exceptions.PreexistingEntityException;

/**
 *
 * @author Mohammed AGHOUI
 */
public class IncInfraJpaController implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IncInfraJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

   
    public void create(IncInfra incInfra) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        System.out.println("-------"+incInfra.getEquipement());

        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(incInfra);
            em.getTransaction().commit();
            System.out.println("-------"+incInfra.getEquipement());

        } catch (Exception ex) {
            if (findIncInfra(incInfra.getTicketInfra()) != null) {
                throw new PreexistingEntityException("IncInfra " + incInfra + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


    public void edit(IncInfra incInfra) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            incInfra = em.merge(incInfra);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = incInfra.getTicketInfra();
                if (findIncInfra(id) == null) {
                    throw new NonexistentEntityException("The incInfra with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(IncInfra incInfra) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            

            if (!em.contains(incInfra)) {
                incInfra = em.merge(incInfra);
            }
            em.remove(incInfra);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<IncInfra> findIncInfraEntities() {
        return findIncInfraEntities(true, -1, -1);
    }

    public List<IncInfra> findIncInfraEntities(int maxResults, int firstResult) {
        return findIncInfraEntities(false, maxResults, firstResult);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	private List<IncInfra> findIncInfraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(IncInfra.class));
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

    public IncInfra findIncInfra(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(IncInfra.class, id);
        } finally {
            em.close();
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public int getIncInfraCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<IncInfra> rt = cq.from(IncInfra.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

        public IncInfra findIncInfraticket(int ticket) {
        EntityManager em = getEntityManager();
         IncInfra resultat = null;
        try {
            StringBuilder sb = new StringBuilder();
            
            sb.append("SELECT u FROM IncInfra u WHERE u.ticketInfra = "+ticket);
            
            System.out.println("" + sb);
            
            Query query = em.createQuery(sb.toString());
            
           // query.setParameter("ticketApp", ticket);
            if (query.getResultList() != null && query.getResultList().size() > 0) {
                resultat =   (IncInfra)query.getResultList().get(0);
            }            
           
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultat;
        
    }
    
}
