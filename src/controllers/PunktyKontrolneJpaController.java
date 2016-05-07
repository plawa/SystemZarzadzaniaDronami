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
import database.PunktyKontrolne;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Piotrek
 */
public class PunktyKontrolneJpaController implements Serializable {

    public PunktyKontrolneJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PunktyKontrolne punktyKontrolne) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Drony IDdrona = punktyKontrolne.getIDdrona();
            if (IDdrona != null) {
                IDdrona = em.getReference(IDdrona.getClass(), IDdrona.getId());
                punktyKontrolne.setIDdrona(IDdrona);
            }
            em.persist(punktyKontrolne);
            if (IDdrona != null) {
                IDdrona.getPunktyKontrolneCollection().add(punktyKontrolne);
                IDdrona = em.merge(IDdrona);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPunktyKontrolne(punktyKontrolne.getId()) != null) {
                throw new PreexistingEntityException("PunktyKontrolne " + punktyKontrolne + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PunktyKontrolne punktyKontrolne) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PunktyKontrolne persistentPunktyKontrolne = em.find(PunktyKontrolne.class, punktyKontrolne.getId());
            Drony IDdronaOld = persistentPunktyKontrolne.getIDdrona();
            Drony IDdronaNew = punktyKontrolne.getIDdrona();
            if (IDdronaNew != null) {
                IDdronaNew = em.getReference(IDdronaNew.getClass(), IDdronaNew.getId());
                punktyKontrolne.setIDdrona(IDdronaNew);
            }
            punktyKontrolne = em.merge(punktyKontrolne);
            if (IDdronaOld != null && !IDdronaOld.equals(IDdronaNew)) {
                IDdronaOld.getPunktyKontrolneCollection().remove(punktyKontrolne);
                IDdronaOld = em.merge(IDdronaOld);
            }
            if (IDdronaNew != null && !IDdronaNew.equals(IDdronaOld)) {
                IDdronaNew.getPunktyKontrolneCollection().add(punktyKontrolne);
                IDdronaNew = em.merge(IDdronaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = punktyKontrolne.getId();
                if (findPunktyKontrolne(id) == null) {
                    throw new NonexistentEntityException("The punktyKontrolne with id " + id + " no longer exists.");
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
            PunktyKontrolne punktyKontrolne;
            try {
                punktyKontrolne = em.getReference(PunktyKontrolne.class, id);
                punktyKontrolne.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The punktyKontrolne with id " + id + " no longer exists.", enfe);
            }
            Drony IDdrona = punktyKontrolne.getIDdrona();
            if (IDdrona != null) {
                IDdrona.getPunktyKontrolneCollection().remove(punktyKontrolne);
                IDdrona = em.merge(IDdrona);
            }
            em.remove(punktyKontrolne);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PunktyKontrolne> findPunktyKontrolneEntities() {
        return findPunktyKontrolneEntities(true, -1, -1);
    }

    public List<PunktyKontrolne> findPunktyKontrolneEntities(int maxResults, int firstResult) {
        return findPunktyKontrolneEntities(false, maxResults, firstResult);
    }

    private List<PunktyKontrolne> findPunktyKontrolneEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PunktyKontrolne.class));
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

    public PunktyKontrolne findPunktyKontrolne(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PunktyKontrolne.class, id);
        } finally {
            em.close();
        }
    }

    public int getPunktyKontrolneCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PunktyKontrolne> rt = cq.from(PunktyKontrolne.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
