/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.exceptions.NonexistentEntityException;
import controllers.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import database.Stacja;
import database.StanowiskaLadujace;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Piotrek
 */
public class StanowiskaLadujaceJpaController implements Serializable {

    public StanowiskaLadujaceJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(StanowiskaLadujace stanowiskaLadujace) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Stacja IDstacji = stanowiskaLadujace.getIDstacji();
            if (IDstacji != null) {
                IDstacji = em.getReference(IDstacji.getClass(), IDstacji.getId());
                stanowiskaLadujace.setIDstacji(IDstacji);
            }
            em.persist(stanowiskaLadujace);
            if (IDstacji != null) {
                IDstacji.getStanowiskaLadujaceCollection().add(stanowiskaLadujace);
                IDstacji = em.merge(IDstacji);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findStanowiskaLadujace(stanowiskaLadujace.getId()) != null) {
                throw new PreexistingEntityException("StanowiskaLadujace " + stanowiskaLadujace + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(StanowiskaLadujace stanowiskaLadujace) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            StanowiskaLadujace persistentStanowiskaLadujace = em.find(StanowiskaLadujace.class, stanowiskaLadujace.getId());
            Stacja IDstacjiOld = persistentStanowiskaLadujace.getIDstacji();
            Stacja IDstacjiNew = stanowiskaLadujace.getIDstacji();
            if (IDstacjiNew != null) {
                IDstacjiNew = em.getReference(IDstacjiNew.getClass(), IDstacjiNew.getId());
                stanowiskaLadujace.setIDstacji(IDstacjiNew);
            }
            stanowiskaLadujace = em.merge(stanowiskaLadujace);
            if (IDstacjiOld != null && !IDstacjiOld.equals(IDstacjiNew)) {
                IDstacjiOld.getStanowiskaLadujaceCollection().remove(stanowiskaLadujace);
                IDstacjiOld = em.merge(IDstacjiOld);
            }
            if (IDstacjiNew != null && !IDstacjiNew.equals(IDstacjiOld)) {
                IDstacjiNew.getStanowiskaLadujaceCollection().add(stanowiskaLadujace);
                IDstacjiNew = em.merge(IDstacjiNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = stanowiskaLadujace.getId();
                if (findStanowiskaLadujace(id) == null) {
                    throw new NonexistentEntityException("The stanowiskaLadujace with id " + id + " no longer exists.");
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
            StanowiskaLadujace stanowiskaLadujace;
            try {
                stanowiskaLadujace = em.getReference(StanowiskaLadujace.class, id);
                stanowiskaLadujace.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The stanowiskaLadujace with id " + id + " no longer exists.", enfe);
            }
            Stacja IDstacji = stanowiskaLadujace.getIDstacji();
            if (IDstacji != null) {
                IDstacji.getStanowiskaLadujaceCollection().remove(stanowiskaLadujace);
                IDstacji = em.merge(IDstacji);
            }
            em.remove(stanowiskaLadujace);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<StanowiskaLadujace> findStanowiskaLadujaceEntities() {
        return findStanowiskaLadujaceEntities(true, -1, -1);
    }

    public List<StanowiskaLadujace> findStanowiskaLadujaceEntities(int maxResults, int firstResult) {
        return findStanowiskaLadujaceEntities(false, maxResults, firstResult);
    }

    private List<StanowiskaLadujace> findStanowiskaLadujaceEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(StanowiskaLadujace.class));
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

    public StanowiskaLadujace findStanowiskaLadujace(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(StanowiskaLadujace.class, id);
        } finally {
            em.close();
        }
    }

    public int getStanowiskaLadujaceCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<StanowiskaLadujace> rt = cq.from(StanowiskaLadujace.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
