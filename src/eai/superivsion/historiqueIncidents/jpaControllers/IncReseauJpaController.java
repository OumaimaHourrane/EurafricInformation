/*
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

import eai.superivsion.historiqueIncidents.entities.IncReseau;
import eai.superivsion.historiqueIncidents.jpaControllers.exceptions.NonexistentEntityException;
import eai.superivsion.historiqueIncidents.jpaControllers.exceptions.PreexistingEntityException;

/**
 *
 * @author Mohammed AGHOUI
 */
public class IncReseauJpaController implements Serializable {

    public IncReseauJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(IncReseau incReseau) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(incReseau);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findIncReseau(incReseau.getTicketReseau()) != null) {
                throw new PreexistingEntityException("IncReseau " + incReseau + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(IncReseau incReseau) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            incReseau = em.merge(incReseau);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = incReseau.getTicketReseau();
                if (findIncReseau(id) == null) {
                    throw new NonexistentEntityException("The incReseau with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(IncReseau incReseau) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            
            if (!em.contains(incReseau)) {
                incReseau = em.merge(incReseau);
            }
            em.remove(incReseau);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<IncReseau> findIncReseauEntities() {
        return findIncReseauEntities(true, -1, -1);
    }

    public List<IncReseau> findIncReseauEntities(int maxResults, int firstResult) {
        return findIncReseauEntities(false, maxResults, firstResult);
    }

    private List<IncReseau> findIncReseauEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(IncReseau.class));
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

    public IncReseau findIncReseau(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(IncReseau.class, id);
        } finally {
            em.close();
        }
    }

    public int getIncReseauCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<IncReseau> rt = cq.from(IncReseau.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    @SuppressWarnings("unchecked")
	public List<IncReseau> findIncReseauOp(String operateur) {
        EntityManager em = getEntityManager();
        List<IncReseau> resultat = null;
        try {
            StringBuilder sb = new StringBuilder();

            sb.append("SELECT i FROM IncReseau i WHERE i.operateur = :operateur ");

            Query query = em.createQuery(sb.toString());

            query.setParameter("operateur", operateur);
            
                resultat =  query.getResultList();
            

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultat;

    }
    
    public IncReseau findIncReseauticket(int ticket) {
        EntityManager em = getEntityManager();
        IncReseau resultat = null;
        try {
            StringBuilder sb = new StringBuilder();

            sb.append("SELECT u FROM IncInterface u WHERE u.ticketReseau = " + ticket);

            Query query = em.createQuery(sb.toString());

            // query.setParameter("ticketApp", ticket);
            if (query.getResultList() != null && query.getResultList().size() > 0) {
                resultat = (IncReseau) query.getResultList().get(0);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultat;

    }
}
