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
import database.Koordynaty;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Piotrek
 */
public class KoordynatyJpaController implements Serializable {

    public KoordynatyJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Koordynaty koordynaty) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Drony IDdrona = koordynaty.getIDdrona();
            if (IDdrona != null) {
                IDdrona = em.getReference(IDdrona.getClass(), IDdrona.getId());
                koordynaty.setIDdrona(IDdrona);
            }
            em.persist(koordynaty);
            if (IDdrona != null) {
                IDdrona.getKoordynatyCollection().add(koordynaty);
                IDdrona = em.merge(IDdrona);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findKoordynaty(koordynaty.getId()) != null) {
                throw new PreexistingEntityException("Koordynaty " + koordynaty + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Koordynaty koordynaty) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Koordynaty persistentKoordynaty = em.find(Koordynaty.class, koordynaty.getId());
            Drony IDdronaOld = persistentKoordynaty.getIDdrona();
            Drony IDdronaNew = koordynaty.getIDdrona();
            if (IDdronaNew != null) {
                IDdronaNew = em.getReference(IDdronaNew.getClass(), IDdronaNew.getId());
                koordynaty.setIDdrona(IDdronaNew);
            }
            koordynaty = em.merge(koordynaty);
            if (IDdronaOld != null && !IDdronaOld.equals(IDdronaNew)) {
                IDdronaOld.getKoordynatyCollection().remove(koordynaty);
                IDdronaOld = em.merge(IDdronaOld);
            }
            if (IDdronaNew != null && !IDdronaNew.equals(IDdronaOld)) {
                IDdronaNew.getKoordynatyCollection().add(koordynaty);
                IDdronaNew = em.merge(IDdronaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = koordynaty.getId();
                if (findKoordynaty(id) == null) {
                    throw new NonexistentEntityException("The koordynaty with id " + id + " no longer exists.");
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
            Koordynaty koordynaty;
            try {
                koordynaty = em.getReference(Koordynaty.class, id);
                koordynaty.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The koordynaty with id " + id + " no longer exists.", enfe);
            }
            Drony IDdrona = koordynaty.getIDdrona();
            if (IDdrona != null) {
                IDdrona.getKoordynatyCollection().remove(koordynaty);
                IDdrona = em.merge(IDdrona);
            }
            em.remove(koordynaty);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Koordynaty> findKoordynatyEntities() {
        return findKoordynatyEntities(true, -1, -1);
    }

    public List<Koordynaty> findKoordynatyEntities(int maxResults, int firstResult) {
        return findKoordynatyEntities(false, maxResults, firstResult);
    }

    private List<Koordynaty> findKoordynatyEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Koordynaty.class));
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

    public Koordynaty findKoordynaty(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Koordynaty.class, id);
        } finally {
            em.close();
        }
    }

    public int getKoordynatyCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Koordynaty> rt = cq.from(Koordynaty.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
