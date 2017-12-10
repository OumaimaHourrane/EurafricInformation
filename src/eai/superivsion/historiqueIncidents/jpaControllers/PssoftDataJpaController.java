package eai.superivsion.historiqueIncidents.jpaControllers;

import eai.superivsion.historiqueIncidents.entities.*;
import eai.superivsion.historiqueIncidents.jpaControllers.exceptions.NonexistentEntityException;
import eai.superivsion.historiqueIncidents.jpaControllers.exceptions.PreexistingEntityException;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.primefaces.component.fileupload.FileUpload;

import Model.Users;

/**
 *
 * @author Oumaima
 */
public class PssoftDataJpaController implements Serializable {

    public PssoftDataJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PssoftData pssoftData) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(pssoftData);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPssoftData(pssoftData.getTicket()) != null) {
                throw new PreexistingEntityException("PssoftData " + pssoftData + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PssoftData pssoftData) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            pssoftData = em.merge(pssoftData);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pssoftData.getTicket();
                if (findPssoftData(id) == null) {
                    throw new NonexistentEntityException("The pssoftData with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void upload(String path){
    	EntityManager em = getEntityManager();
        try {

            StringBuilder sb = new StringBuilder();
            sb.append("COPY PssoftData p FROM "+path);
            em.createQuery(sb.toString());
            
        } finally {
            em.close();
        }
    	
    }
    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PssoftData pssoftData;
            try {
                pssoftData = em.getReference(PssoftData.class, id);
                pssoftData.getTicket();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pssoftData with id " + id + " no longer exists.", enfe);
            }
            em.remove(pssoftData);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PssoftData> findPssoftDataEntities() {
        return findPssoftDataEntities(true, -1, -1);
    }

    public List<PssoftData> findPssoftDataEntities(int maxResults, int firstResult) {
        return findPssoftDataEntities(false, maxResults, firstResult);
    }

    private List<PssoftData> findPssoftDataEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PssoftData.class));
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

    public PssoftData findPssoftData(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PssoftData.class, id);
        } finally {
            em.close();
        }
    }

    public int getPssoftDataCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PssoftData> rt = cq.from(PssoftData.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
