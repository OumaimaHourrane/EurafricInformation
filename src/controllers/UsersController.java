package controllers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import controllers.exceptions.NonexistentEntityException;
import Model.Users;

public class UsersController {
	
	private EntityManagerFactory emf = null;

	
	public UsersController(EntityManagerFactory emf) {
		super();
		this.emf = emf;
	}

	public EntityManagerFactory getEmf() {
		return emf;
	}

	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}
	public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
	
	public List<Users> findUsersEntities() {
        return findUsersEntities(true, -1, -1);
    }

    public List<Users> findUsersEntities(int maxResults, int firstResult) {
        return findUsersEntities(false, maxResults, firstResult);
    }

    @SuppressWarnings("unchecked")
	private List<Users> findUsersEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            @SuppressWarnings("rawtypes")
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Users.class));
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

    public void edit(Users users) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
           
            users = em.merge(users);
            
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = users.getId();
                if (findUsers(id) == null) {
                    throw new NonexistentEntityException("The users with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Users findUsers(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Users.class, id);
        } finally {
            em.close();
        }
    }

    public void create(Users users) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
         
           
            em.persist(users);
            
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        
    }
	@SuppressWarnings("unchecked")
	public List<Users> findUsers() {
		EntityManager em = getEntityManager();
       try {

           StringBuilder sb = new StringBuilder();
           sb.append("SELECT u FROM Users u");
           Query query = em.createQuery(sb.toString());
          
           List<Users> u = query.getResultList();
           System.out.println("-------------: " + u.size());
           return u;

       } finally {
           em.close();
       }
   }
	

	public Users findUser(String email) {
		
        EntityManager em = getEntityManager();
        
       try {

           StringBuilder sb = new StringBuilder();

           sb.append("SELECT u FROM Users u WHERE u.email =:email");

           Query query = em.createQuery(sb.toString());

           query.setParameter("email", email);

           @SuppressWarnings("unchecked")
		List<Users> resultat = query.getResultList();

           if (resultat.size() > 0) {
               return resultat.get(0);
           } else {
               return null;
           }

       } finally {
           em.close();
       }
   }
	
	
}
