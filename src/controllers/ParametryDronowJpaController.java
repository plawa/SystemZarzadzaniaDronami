/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.exceptions.IllegalOrphanException;
import controllers.exceptions.NonexistentEntityException;
import controllers.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import database.Drony;
import database.ParametryDronow;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Piotrek
 */
public class ParametryDronowJpaController implements Serializable {

    public ParametryDronowJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ParametryDronow parametryDronow) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        Drony dronyOrphanCheck = parametryDronow.getDrony();
        if (dronyOrphanCheck != null) {
            ParametryDronow oldParametryDronowOfDrony = dronyOrphanCheck.getParametryDronow();
            if (oldParametryDronowOfDrony != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Drony " + dronyOrphanCheck + " already has an item of type ParametryDronow whose drony column cannot be null. Please make another selection for the drony field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Drony drony = parametryDronow.getDrony();
            if (drony != null) {
                drony = em.getReference(drony.getClass(), drony.getId());
                parametryDronow.setDrony(drony);
            }
            em.persist(parametryDronow);
            if (drony != null) {
                drony.setParametryDronow(parametryDronow);
                drony = em.merge(drony);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findParametryDronow(parametryDronow.getId()) != null) {
                throw new PreexistingEntityException("ParametryDronow " + parametryDronow + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ParametryDronow parametryDronow) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ParametryDronow persistentParametryDronow = em.find(ParametryDronow.class, parametryDronow.getId());
            Drony dronyOld = persistentParametryDronow.getDrony();
            Drony dronyNew = parametryDronow.getDrony();
            List<String> illegalOrphanMessages = null;
            if (dronyNew != null && !dronyNew.equals(dronyOld)) {
                ParametryDronow oldParametryDronowOfDrony = dronyNew.getParametryDronow();
                if (oldParametryDronowOfDrony != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Drony " + dronyNew + " already has an item of type ParametryDronow whose drony column cannot be null. Please make another selection for the drony field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (dronyNew != null) {
                dronyNew = em.getReference(dronyNew.getClass(), dronyNew.getId());
                parametryDronow.setDrony(dronyNew);
            }
            parametryDronow = em.merge(parametryDronow);
            if (dronyOld != null && !dronyOld.equals(dronyNew)) {
                dronyOld.setParametryDronow(null);
                dronyOld = em.merge(dronyOld);
            }
            if (dronyNew != null && !dronyNew.equals(dronyOld)) {
                dronyNew.setParametryDronow(parametryDronow);
                dronyNew = em.merge(dronyNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = parametryDronow.getId();
                if (findParametryDronow(id) == null) {
                    throw new NonexistentEntityException("The parametryDronow with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ParametryDronow parametryDronow;
            try {
                parametryDronow = em.getReference(ParametryDronow.class, id);
                parametryDronow.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The parametryDronow with id " + id + " no longer exists.", enfe);
            }
            Drony drony = parametryDronow.getDrony();
            if (drony != null) {
                drony.setParametryDronow(null);
                drony = em.merge(drony);
            }
            em.remove(parametryDronow);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ParametryDronow> findParametryDronowEntities() {
        return findParametryDronowEntities(true, -1, -1);
    }

    public List<ParametryDronow> findParametryDronowEntities(int maxResults, int firstResult) {
        return findParametryDronowEntities(false, maxResults, firstResult);
    }

    private List<ParametryDronow> findParametryDronowEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ParametryDronow.class));
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

    public ParametryDronow findParametryDronow(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ParametryDronow.class, id);
        } finally {
            em.close();
        }
    }

    public int getParametryDronowCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ParametryDronow> rt = cq.from(ParametryDronow.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
