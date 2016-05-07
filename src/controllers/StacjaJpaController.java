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
import database.Drony;
import database.Stacja;
import java.util.ArrayList;
import java.util.Collection;
import database.StanowiskaLadujace;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Piotrek
 */
public class StacjaJpaController implements Serializable {

    public StacjaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Stacja stacja) throws PreexistingEntityException, Exception {
        if (stacja.getDronyCollection() == null) {
            stacja.setDronyCollection(new ArrayList<Drony>());
        }
        if (stacja.getStanowiskaLadujaceCollection() == null) {
            stacja.setStanowiskaLadujaceCollection(new ArrayList<StanowiskaLadujace>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Drony> attachedDronyCollection = new ArrayList<Drony>();
            for (Drony dronyCollectionDronyToAttach : stacja.getDronyCollection()) {
                dronyCollectionDronyToAttach = em.getReference(dronyCollectionDronyToAttach.getClass(), dronyCollectionDronyToAttach.getId());
                attachedDronyCollection.add(dronyCollectionDronyToAttach);
            }
            stacja.setDronyCollection(attachedDronyCollection);
            Collection<StanowiskaLadujace> attachedStanowiskaLadujaceCollection = new ArrayList<StanowiskaLadujace>();
            for (StanowiskaLadujace stanowiskaLadujaceCollectionStanowiskaLadujaceToAttach : stacja.getStanowiskaLadujaceCollection()) {
                stanowiskaLadujaceCollectionStanowiskaLadujaceToAttach = em.getReference(stanowiskaLadujaceCollectionStanowiskaLadujaceToAttach.getClass(), stanowiskaLadujaceCollectionStanowiskaLadujaceToAttach.getId());
                attachedStanowiskaLadujaceCollection.add(stanowiskaLadujaceCollectionStanowiskaLadujaceToAttach);
            }
            stacja.setStanowiskaLadujaceCollection(attachedStanowiskaLadujaceCollection);
            em.persist(stacja);
            for (Drony dronyCollectionDrony : stacja.getDronyCollection()) {
                Stacja oldIDstacjiOfDronyCollectionDrony = dronyCollectionDrony.getIDstacji();
                dronyCollectionDrony.setIDstacji(stacja);
                dronyCollectionDrony = em.merge(dronyCollectionDrony);
                if (oldIDstacjiOfDronyCollectionDrony != null) {
                    oldIDstacjiOfDronyCollectionDrony.getDronyCollection().remove(dronyCollectionDrony);
                    oldIDstacjiOfDronyCollectionDrony = em.merge(oldIDstacjiOfDronyCollectionDrony);
                }
            }
            for (StanowiskaLadujace stanowiskaLadujaceCollectionStanowiskaLadujace : stacja.getStanowiskaLadujaceCollection()) {
                Stacja oldIDstacjiOfStanowiskaLadujaceCollectionStanowiskaLadujace = stanowiskaLadujaceCollectionStanowiskaLadujace.getIDstacji();
                stanowiskaLadujaceCollectionStanowiskaLadujace.setIDstacji(stacja);
                stanowiskaLadujaceCollectionStanowiskaLadujace = em.merge(stanowiskaLadujaceCollectionStanowiskaLadujace);
                if (oldIDstacjiOfStanowiskaLadujaceCollectionStanowiskaLadujace != null) {
                    oldIDstacjiOfStanowiskaLadujaceCollectionStanowiskaLadujace.getStanowiskaLadujaceCollection().remove(stanowiskaLadujaceCollectionStanowiskaLadujace);
                    oldIDstacjiOfStanowiskaLadujaceCollectionStanowiskaLadujace = em.merge(oldIDstacjiOfStanowiskaLadujaceCollectionStanowiskaLadujace);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findStacja(stacja.getId()) != null) {
                throw new PreexistingEntityException("Stacja " + stacja + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Stacja stacja) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Stacja persistentStacja = em.find(Stacja.class, stacja.getId());
            Collection<Drony> dronyCollectionOld = persistentStacja.getDronyCollection();
            Collection<Drony> dronyCollectionNew = stacja.getDronyCollection();
            Collection<StanowiskaLadujace> stanowiskaLadujaceCollectionOld = persistentStacja.getStanowiskaLadujaceCollection();
            Collection<StanowiskaLadujace> stanowiskaLadujaceCollectionNew = stacja.getStanowiskaLadujaceCollection();
            Collection<Drony> attachedDronyCollectionNew = new ArrayList<Drony>();
            for (Drony dronyCollectionNewDronyToAttach : dronyCollectionNew) {
                dronyCollectionNewDronyToAttach = em.getReference(dronyCollectionNewDronyToAttach.getClass(), dronyCollectionNewDronyToAttach.getId());
                attachedDronyCollectionNew.add(dronyCollectionNewDronyToAttach);
            }
            dronyCollectionNew = attachedDronyCollectionNew;
            stacja.setDronyCollection(dronyCollectionNew);
            Collection<StanowiskaLadujace> attachedStanowiskaLadujaceCollectionNew = new ArrayList<StanowiskaLadujace>();
            for (StanowiskaLadujace stanowiskaLadujaceCollectionNewStanowiskaLadujaceToAttach : stanowiskaLadujaceCollectionNew) {
                stanowiskaLadujaceCollectionNewStanowiskaLadujaceToAttach = em.getReference(stanowiskaLadujaceCollectionNewStanowiskaLadujaceToAttach.getClass(), stanowiskaLadujaceCollectionNewStanowiskaLadujaceToAttach.getId());
                attachedStanowiskaLadujaceCollectionNew.add(stanowiskaLadujaceCollectionNewStanowiskaLadujaceToAttach);
            }
            stanowiskaLadujaceCollectionNew = attachedStanowiskaLadujaceCollectionNew;
            stacja.setStanowiskaLadujaceCollection(stanowiskaLadujaceCollectionNew);
            stacja = em.merge(stacja);
            for (Drony dronyCollectionOldDrony : dronyCollectionOld) {
                if (!dronyCollectionNew.contains(dronyCollectionOldDrony)) {
                    dronyCollectionOldDrony.setIDstacji(null);
                    dronyCollectionOldDrony = em.merge(dronyCollectionOldDrony);
                }
            }
            for (Drony dronyCollectionNewDrony : dronyCollectionNew) {
                if (!dronyCollectionOld.contains(dronyCollectionNewDrony)) {
                    Stacja oldIDstacjiOfDronyCollectionNewDrony = dronyCollectionNewDrony.getIDstacji();
                    dronyCollectionNewDrony.setIDstacji(stacja);
                    dronyCollectionNewDrony = em.merge(dronyCollectionNewDrony);
                    if (oldIDstacjiOfDronyCollectionNewDrony != null && !oldIDstacjiOfDronyCollectionNewDrony.equals(stacja)) {
                        oldIDstacjiOfDronyCollectionNewDrony.getDronyCollection().remove(dronyCollectionNewDrony);
                        oldIDstacjiOfDronyCollectionNewDrony = em.merge(oldIDstacjiOfDronyCollectionNewDrony);
                    }
                }
            }
            for (StanowiskaLadujace stanowiskaLadujaceCollectionOldStanowiskaLadujace : stanowiskaLadujaceCollectionOld) {
                if (!stanowiskaLadujaceCollectionNew.contains(stanowiskaLadujaceCollectionOldStanowiskaLadujace)) {
                    stanowiskaLadujaceCollectionOldStanowiskaLadujace.setIDstacji(null);
                    stanowiskaLadujaceCollectionOldStanowiskaLadujace = em.merge(stanowiskaLadujaceCollectionOldStanowiskaLadujace);
                }
            }
            for (StanowiskaLadujace stanowiskaLadujaceCollectionNewStanowiskaLadujace : stanowiskaLadujaceCollectionNew) {
                if (!stanowiskaLadujaceCollectionOld.contains(stanowiskaLadujaceCollectionNewStanowiskaLadujace)) {
                    Stacja oldIDstacjiOfStanowiskaLadujaceCollectionNewStanowiskaLadujace = stanowiskaLadujaceCollectionNewStanowiskaLadujace.getIDstacji();
                    stanowiskaLadujaceCollectionNewStanowiskaLadujace.setIDstacji(stacja);
                    stanowiskaLadujaceCollectionNewStanowiskaLadujace = em.merge(stanowiskaLadujaceCollectionNewStanowiskaLadujace);
                    if (oldIDstacjiOfStanowiskaLadujaceCollectionNewStanowiskaLadujace != null && !oldIDstacjiOfStanowiskaLadujaceCollectionNewStanowiskaLadujace.equals(stacja)) {
                        oldIDstacjiOfStanowiskaLadujaceCollectionNewStanowiskaLadujace.getStanowiskaLadujaceCollection().remove(stanowiskaLadujaceCollectionNewStanowiskaLadujace);
                        oldIDstacjiOfStanowiskaLadujaceCollectionNewStanowiskaLadujace = em.merge(oldIDstacjiOfStanowiskaLadujaceCollectionNewStanowiskaLadujace);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = stacja.getId();
                if (findStacja(id) == null) {
                    throw new NonexistentEntityException("The stacja with id " + id + " no longer exists.");
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
            Stacja stacja;
            try {
                stacja = em.getReference(Stacja.class, id);
                stacja.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The stacja with id " + id + " no longer exists.", enfe);
            }
            Collection<Drony> dronyCollection = stacja.getDronyCollection();
            for (Drony dronyCollectionDrony : dronyCollection) {
                dronyCollectionDrony.setIDstacji(null);
                dronyCollectionDrony = em.merge(dronyCollectionDrony);
            }
            Collection<StanowiskaLadujace> stanowiskaLadujaceCollection = stacja.getStanowiskaLadujaceCollection();
            for (StanowiskaLadujace stanowiskaLadujaceCollectionStanowiskaLadujace : stanowiskaLadujaceCollection) {
                stanowiskaLadujaceCollectionStanowiskaLadujace.setIDstacji(null);
                stanowiskaLadujaceCollectionStanowiskaLadujace = em.merge(stanowiskaLadujaceCollectionStanowiskaLadujace);
            }
            em.remove(stacja);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Stacja> findStacjaEntities() {
        return findStacjaEntities(true, -1, -1);
    }

    public List<Stacja> findStacjaEntities(int maxResults, int firstResult) {
        return findStacjaEntities(false, maxResults, firstResult);
    }

    private List<Stacja> findStacjaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Stacja.class));
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

    public Stacja findStacja(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Stacja.class, id);
        } finally {
            em.close();
        }
    }

    public int getStacjaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Stacja> rt = cq.from(Stacja.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
