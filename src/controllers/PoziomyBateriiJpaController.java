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
import database.PoziomyBaterii;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Piotrek
 */
public class PoziomyBateriiJpaController implements Serializable {

    public PoziomyBateriiJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PoziomyBaterii poziomyBaterii) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Drony IDdrona = poziomyBaterii.getIDdrona();
            if (IDdrona != null) {
                IDdrona = em.getReference(IDdrona.getClass(), IDdrona.getId());
                poziomyBaterii.setIDdrona(IDdrona);
            }
            em.persist(poziomyBaterii);
            if (IDdrona != null) {
                IDdrona.getPoziomyBateriiCollection().add(poziomyBaterii);
                IDdrona = em.merge(IDdrona);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPoziomyBaterii(poziomyBaterii.getId()) != null) {
                throw new PreexistingEntityException("PoziomyBaterii " + poziomyBaterii + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PoziomyBaterii poziomyBaterii) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PoziomyBaterii persistentPoziomyBaterii = em.find(PoziomyBaterii.class, poziomyBaterii.getId());
            Drony IDdronaOld = persistentPoziomyBaterii.getIDdrona();
            Drony IDdronaNew = poziomyBaterii.getIDdrona();
            if (IDdronaNew != null) {
                IDdronaNew = em.getReference(IDdronaNew.getClass(), IDdronaNew.getId());
                poziomyBaterii.setIDdrona(IDdronaNew);
            }
            poziomyBaterii = em.merge(poziomyBaterii);
            if (IDdronaOld != null && !IDdronaOld.equals(IDdronaNew)) {
                IDdronaOld.getPoziomyBateriiCollection().remove(poziomyBaterii);
                IDdronaOld = em.merge(IDdronaOld);
            }
            if (IDdronaNew != null && !IDdronaNew.equals(IDdronaOld)) {
                IDdronaNew.getPoziomyBateriiCollection().add(poziomyBaterii);
                IDdronaNew = em.merge(IDdronaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = poziomyBaterii.getId();
                if (findPoziomyBaterii(id) == null) {
                    throw new NonexistentEntityException("The poziomyBaterii with id " + id + " no longer exists.");
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
            PoziomyBaterii poziomyBaterii;
            try {
                poziomyBaterii = em.getReference(PoziomyBaterii.class, id);
                poziomyBaterii.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The poziomyBaterii with id " + id + " no longer exists.", enfe);
            }
            Drony IDdrona = poziomyBaterii.getIDdrona();
            if (IDdrona != null) {
                IDdrona.getPoziomyBateriiCollection().remove(poziomyBaterii);
                IDdrona = em.merge(IDdrona);
            }
            em.remove(poziomyBaterii);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PoziomyBaterii> findPoziomyBateriiEntities() {
        return findPoziomyBateriiEntities(true, -1, -1);
    }

    public List<PoziomyBaterii> findPoziomyBateriiEntities(int maxResults, int firstResult) {
        return findPoziomyBateriiEntities(false, maxResults, firstResult);
    }

    private List<PoziomyBaterii> findPoziomyBateriiEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PoziomyBaterii.class));
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

    public PoziomyBaterii findPoziomyBaterii(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PoziomyBaterii.class, id);
        } finally {
            em.close();
        }
    }

    public int getPoziomyBateriiCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PoziomyBaterii> rt = cq.from(PoziomyBaterii.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
