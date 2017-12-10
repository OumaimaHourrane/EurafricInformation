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

import eai.superivsion.historiqueIncidents.entities.IncGabExt;
import eai.superivsion.historiqueIncidents.jpaControllers.exceptions.NonexistentEntityException;
import eai.superivsion.historiqueIncidents.jpaControllers.exceptions.PreexistingEntityException;

/**
 *
 * @author Mohammed AGHOUI
 */
public class IncGabExtJpaController implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IncGabExtJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(IncGabExt incGabExt) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        System.out.println("------- : "+incGabExt.getIdGab());
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(incGabExt);
            em.getTransaction().commit();
            System.out.println("------- : "+incGabExt.getIdGab());

        } catch (Exception ex) {
            if (findIncGabExt(incGabExt.getTicketGabExt()) != null) {
                throw new PreexistingEntityException("IncGabExt " + incGabExt + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(IncGabExt incGabExt) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            incGabExt = em.merge(incGabExt);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = incGabExt.getTicketGabExt();
                if (findIncGabExt(id) == null) {
                    throw new NonexistentEntityException("The incGabExt with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(IncGabExt incGabExt) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            
            if (!em.contains(incGabExt)) {
                incGabExt = em.merge(incGabExt);
            }
            
            em.remove(incGabExt);
            em.getTransaction().commit();
            
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    @SuppressWarnings("unchecked")
	public List<IncGabExt> findIncGabExt() {
		
        EntityManager em = getEntityManager();
        
       try {

           StringBuilder sb = new StringBuilder();

           sb.append("SELECT c.ville , c.idGab, c.duree, c.dateDebut, c.commentaire FROM IncGabExt c ");
           Query query = em.createQuery(sb.toString());
          
           List<IncGabExt> list = query.getResultList();
          return list;

       } finally {
           em.close();
       }
   }
    public List<IncGabExt> findIncGabExtEntities() {
        return findIncGabExtEntities(true, 10, 10);
    }

    public List<IncGabExt> findIncGabExtEntities(int maxResults, int firstResult) {
        return findIncGabExtEntities(false, maxResults, firstResult);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	private List<IncGabExt> findIncGabExtEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
        	
        	CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery();
            
            cq.select(cq.from(IncGabExt.class));
            cq.orderBy(cb.desc(cq.from(IncGabExt.class).get("duree")));
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

    public IncGabExt findIncGabExt(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(IncGabExt.class, id);
        } finally {
            em.close();
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public int getIncGabExtCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<IncGabExt> rt = cq.from(IncGabExt.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
        public IncGabExt findIncGabExtticket(int ticket) {
        EntityManager em = getEntityManager();
        IncGabExt resultat = null;
        try {
            StringBuilder sb = new StringBuilder();

            sb.append("SELECT u FROM IncGabExt u WHERE u.ticketGabExt = " + ticket);

            Query query = em.createQuery(sb.toString());

            // query.setParameter("ticketApp", ticket);
            if (query.getResultList() != null && query.getResultList().size() > 0) {
                resultat = (IncGabExt) query.getResultList().get(0);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultat;
    }
}
