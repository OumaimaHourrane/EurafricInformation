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

import eai.superivsion.historiqueIncidents.entities.IncInterface;
import eai.superivsion.historiqueIncidents.jpaControllers.exceptions.NonexistentEntityException;
import eai.superivsion.historiqueIncidents.jpaControllers.exceptions.PreexistingEntityException;

/**
 *
 * @author Mohammed AGHOUI
 */
public class IncInterfaceJpaController implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IncInterfaceJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(IncInterface incInterface) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(incInterface);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findIncInterface(incInterface.getTicketInt()) != null) {
                throw new PreexistingEntityException("IncInterface " + incInterface + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(IncInterface incInterface) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            incInterface = em.merge(incInterface);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = incInterface.getTicketInt();
                if (findIncInterface(id) == null) {
                    throw new NonexistentEntityException("The incInterface with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(IncInterface incInterface) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
           
            if (!em.contains(incInterface)) {
                incInterface = em.merge(incInterface);
            }
            em.remove(incInterface);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<IncInterface> findIncInterfaceEntities() {
        return findIncInterfaceEntities(true, -1, -1);
    }

    public List<IncInterface> findIncInterfaceEntities(int maxResults, int firstResult) {
        return findIncInterfaceEntities(false, maxResults, firstResult);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	private List<IncInterface> findIncInterfaceEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
        	CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery();
            cq.select(cq.from(IncInterface.class));
            cq.orderBy(cb.desc(cq.from(IncInterface.class).get("nbrInc"))); 
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
    public List<IncInterface> findIncInterfaceEntitiess() {
        return findIncInterfaceEntities(true, -1, -1);
    }

    public List<IncInterface> findIncInterfaceEntitiess(int maxResults, int firstResult) {
        return findIncInterfaceEntitiess(false, maxResults, firstResult);
    }

    
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<IncInterface> findIncInterfaceEntitiess(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
        	CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery();
            cq.select(cq.from(IncInterface.class));
            cq.orderBy(cb.desc(cq.from(IncInterface.class).get("duree"))); 
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
	public List<IncInterface> findIncInterfaceEntitiesss() {
        return findIncInterfaceEntities(true, -1, -1);
    }

    public List<IncInterface> findIncInterfaceEntitiesss(int maxResults, int firstResult) {
        return findIncInterfaceEntitiesss(false, maxResults, firstResult);
    }

    
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List<IncInterface> findIncInterfaceEntitiesss(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
        	CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery();
            cq.select(cq.from(IncInterface.class));
            cq.orderBy(cb.desc(cq.from(IncInterface.class).get("cumule"))); 
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

    public IncInterface findIncInterface(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(IncInterface.class, id);
        } finally {
            em.close();
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public int getIncInterfaceCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<IncInterface> rt = cq.from(IncInterface.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
           public IncInterface findIncInterfaceticket(int ticket) {
        EntityManager em = getEntityManager();
        IncInterface resultat = null;
        try {
            StringBuilder sb = new StringBuilder();

            sb.append("SELECT u FROM IncInterface u WHERE u.ticketInt = " + ticket);

            Query query = em.createQuery(sb.toString());

            // query.setParameter("ticketApp", ticket);
            if (query.getResultList() != null && query.getResultList().size() > 0) {
                resultat = (IncInterface) query.getResultList().get(0);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultat;
        
    }
}
