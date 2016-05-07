/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.exceptions.IllegalOrphanException;
import controllers.exceptions.NonexistentEntityException;
import controllers.exceptions.PreexistingEntityException;
import database.Drony;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import database.ParametryDronow;
import database.Stacja;
import database.PoziomyBaterii;
import java.util.ArrayList;
import java.util.Collection;
import database.Koordynaty;
import database.PunktyKontrolne;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Piotrek
 */
public class DronyJpaController implements Serializable {

    public DronyJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Drony drony) throws PreexistingEntityException, Exception {
        if (drony.getPoziomyBateriiCollection() == null) {
            drony.setPoziomyBateriiCollection(new ArrayList<PoziomyBaterii>());
        }
        if (drony.getKoordynatyCollection() == null) {
            drony.setKoordynatyCollection(new ArrayList<Koordynaty>());
        }
        if (drony.getPunktyKontrolneCollection() == null) {
            drony.setPunktyKontrolneCollection(new ArrayList<PunktyKontrolne>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ParametryDronow parametryDronow = drony.getParametryDronow();
            if (parametryDronow != null) {
                parametryDronow = em.getReference(parametryDronow.getClass(), parametryDronow.getId());
                drony.setParametryDronow(parametryDronow);
            }
            Stacja IDstacji = drony.getIDstacji();
            if (IDstacji != null) {
                IDstacji = em.getReference(IDstacji.getClass(), IDstacji.getId());
                drony.setIDstacji(IDstacji);
            }
            Collection<PoziomyBaterii> attachedPoziomyBateriiCollection = new ArrayList<PoziomyBaterii>();
            for (PoziomyBaterii poziomyBateriiCollectionPoziomyBateriiToAttach : drony.getPoziomyBateriiCollection()) {
                poziomyBateriiCollectionPoziomyBateriiToAttach = em.getReference(poziomyBateriiCollectionPoziomyBateriiToAttach.getClass(), poziomyBateriiCollectionPoziomyBateriiToAttach.getId());
                attachedPoziomyBateriiCollection.add(poziomyBateriiCollectionPoziomyBateriiToAttach);
            }
            drony.setPoziomyBateriiCollection(attachedPoziomyBateriiCollection);
            Collection<Koordynaty> attachedKoordynatyCollection = new ArrayList<Koordynaty>();
            for (Koordynaty koordynatyCollectionKoordynatyToAttach : drony.getKoordynatyCollection()) {
                koordynatyCollectionKoordynatyToAttach = em.getReference(koordynatyCollectionKoordynatyToAttach.getClass(), koordynatyCollectionKoordynatyToAttach.getId());
                attachedKoordynatyCollection.add(koordynatyCollectionKoordynatyToAttach);
            }
            drony.setKoordynatyCollection(attachedKoordynatyCollection);
            Collection<PunktyKontrolne> attachedPunktyKontrolneCollection = new ArrayList<PunktyKontrolne>();
            for (PunktyKontrolne punktyKontrolneCollectionPunktyKontrolneToAttach : drony.getPunktyKontrolneCollection()) {
                punktyKontrolneCollectionPunktyKontrolneToAttach = em.getReference(punktyKontrolneCollectionPunktyKontrolneToAttach.getClass(), punktyKontrolneCollectionPunktyKontrolneToAttach.getId());
                attachedPunktyKontrolneCollection.add(punktyKontrolneCollectionPunktyKontrolneToAttach);
            }
            drony.setPunktyKontrolneCollection(attachedPunktyKontrolneCollection);
            em.persist(drony);
            if (parametryDronow != null) {
                Drony oldDronyOfParametryDronow = parametryDronow.getDrony();
                if (oldDronyOfParametryDronow != null) {
                    oldDronyOfParametryDronow.setParametryDronow(null);
                    oldDronyOfParametryDronow = em.merge(oldDronyOfParametryDronow);
                }
                parametryDronow.setDrony(drony);
                parametryDronow = em.merge(parametryDronow);
            }
            if (IDstacji != null) {
                IDstacji.getDronyCollection().add(drony);
                IDstacji = em.merge(IDstacji);
            }
            for (PoziomyBaterii poziomyBateriiCollectionPoziomyBaterii : drony.getPoziomyBateriiCollection()) {
                Drony oldIDdronaOfPoziomyBateriiCollectionPoziomyBaterii = poziomyBateriiCollectionPoziomyBaterii.getIDdrona();
                poziomyBateriiCollectionPoziomyBaterii.setIDdrona(drony);
                poziomyBateriiCollectionPoziomyBaterii = em.merge(poziomyBateriiCollectionPoziomyBaterii);
                if (oldIDdronaOfPoziomyBateriiCollectionPoziomyBaterii != null) {
                    oldIDdronaOfPoziomyBateriiCollectionPoziomyBaterii.getPoziomyBateriiCollection().remove(poziomyBateriiCollectionPoziomyBaterii);
                    oldIDdronaOfPoziomyBateriiCollectionPoziomyBaterii = em.merge(oldIDdronaOfPoziomyBateriiCollectionPoziomyBaterii);
                }
            }
            for (Koordynaty koordynatyCollectionKoordynaty : drony.getKoordynatyCollection()) {
                Drony oldIDdronaOfKoordynatyCollectionKoordynaty = koordynatyCollectionKoordynaty.getIDdrona();
                koordynatyCollectionKoordynaty.setIDdrona(drony);
                koordynatyCollectionKoordynaty = em.merge(koordynatyCollectionKoordynaty);
                if (oldIDdronaOfKoordynatyCollectionKoordynaty != null) {
                    oldIDdronaOfKoordynatyCollectionKoordynaty.getKoordynatyCollection().remove(koordynatyCollectionKoordynaty);
                    oldIDdronaOfKoordynatyCollectionKoordynaty = em.merge(oldIDdronaOfKoordynatyCollectionKoordynaty);
                }
            }
            for (PunktyKontrolne punktyKontrolneCollectionPunktyKontrolne : drony.getPunktyKontrolneCollection()) {
                Drony oldIDdronaOfPunktyKontrolneCollectionPunktyKontrolne = punktyKontrolneCollectionPunktyKontrolne.getIDdrona();
                punktyKontrolneCollectionPunktyKontrolne.setIDdrona(drony);
                punktyKontrolneCollectionPunktyKontrolne = em.merge(punktyKontrolneCollectionPunktyKontrolne);
                if (oldIDdronaOfPunktyKontrolneCollectionPunktyKontrolne != null) {
                    oldIDdronaOfPunktyKontrolneCollectionPunktyKontrolne.getPunktyKontrolneCollection().remove(punktyKontrolneCollectionPunktyKontrolne);
                    oldIDdronaOfPunktyKontrolneCollectionPunktyKontrolne = em.merge(oldIDdronaOfPunktyKontrolneCollectionPunktyKontrolne);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDrony(drony.getId()) != null) {
                throw new PreexistingEntityException("Drony " + drony + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Drony drony) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Drony persistentDrony = em.find(Drony.class, drony.getId());
            ParametryDronow parametryDronowOld = persistentDrony.getParametryDronow();
            ParametryDronow parametryDronowNew = drony.getParametryDronow();
            Stacja IDstacjiOld = persistentDrony.getIDstacji();
            Stacja IDstacjiNew = drony.getIDstacji();
            Collection<PoziomyBaterii> poziomyBateriiCollectionOld = persistentDrony.getPoziomyBateriiCollection();
            Collection<PoziomyBaterii> poziomyBateriiCollectionNew = drony.getPoziomyBateriiCollection();
            Collection<Koordynaty> koordynatyCollectionOld = persistentDrony.getKoordynatyCollection();
            Collection<Koordynaty> koordynatyCollectionNew = drony.getKoordynatyCollection();
            Collection<PunktyKontrolne> punktyKontrolneCollectionOld = persistentDrony.getPunktyKontrolneCollection();
            Collection<PunktyKontrolne> punktyKontrolneCollectionNew = drony.getPunktyKontrolneCollection();
            List<String> illegalOrphanMessages = null;
            if (parametryDronowOld != null && !parametryDronowOld.equals(parametryDronowNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain ParametryDronow " + parametryDronowOld + " since its drony field is not nullable.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (parametryDronowNew != null) {
                parametryDronowNew = em.getReference(parametryDronowNew.getClass(), parametryDronowNew.getId());
                drony.setParametryDronow(parametryDronowNew);
            }
            if (IDstacjiNew != null) {
                IDstacjiNew = em.getReference(IDstacjiNew.getClass(), IDstacjiNew.getId());
                drony.setIDstacji(IDstacjiNew);
            }
            Collection<PoziomyBaterii> attachedPoziomyBateriiCollectionNew = new ArrayList<PoziomyBaterii>();
            for (PoziomyBaterii poziomyBateriiCollectionNewPoziomyBateriiToAttach : poziomyBateriiCollectionNew) {
                poziomyBateriiCollectionNewPoziomyBateriiToAttach = em.getReference(poziomyBateriiCollectionNewPoziomyBateriiToAttach.getClass(), poziomyBateriiCollectionNewPoziomyBateriiToAttach.getId());
                attachedPoziomyBateriiCollectionNew.add(poziomyBateriiCollectionNewPoziomyBateriiToAttach);
            }
            poziomyBateriiCollectionNew = attachedPoziomyBateriiCollectionNew;
            drony.setPoziomyBateriiCollection(poziomyBateriiCollectionNew);
            Collection<Koordynaty> attachedKoordynatyCollectionNew = new ArrayList<Koordynaty>();
            for (Koordynaty koordynatyCollectionNewKoordynatyToAttach : koordynatyCollectionNew) {
                koordynatyCollectionNewKoordynatyToAttach = em.getReference(koordynatyCollectionNewKoordynatyToAttach.getClass(), koordynatyCollectionNewKoordynatyToAttach.getId());
                attachedKoordynatyCollectionNew.add(koordynatyCollectionNewKoordynatyToAttach);
            }
            koordynatyCollectionNew = attachedKoordynatyCollectionNew;
            drony.setKoordynatyCollection(koordynatyCollectionNew);
            Collection<PunktyKontrolne> attachedPunktyKontrolneCollectionNew = new ArrayList<PunktyKontrolne>();
            for (PunktyKontrolne punktyKontrolneCollectionNewPunktyKontrolneToAttach : punktyKontrolneCollectionNew) {
                punktyKontrolneCollectionNewPunktyKontrolneToAttach = em.getReference(punktyKontrolneCollectionNewPunktyKontrolneToAttach.getClass(), punktyKontrolneCollectionNewPunktyKontrolneToAttach.getId());
                attachedPunktyKontrolneCollectionNew.add(punktyKontrolneCollectionNewPunktyKontrolneToAttach);
            }
            punktyKontrolneCollectionNew = attachedPunktyKontrolneCollectionNew;
            drony.setPunktyKontrolneCollection(punktyKontrolneCollectionNew);
            drony = em.merge(drony);
            if (parametryDronowNew != null && !parametryDronowNew.equals(parametryDronowOld)) {
                Drony oldDronyOfParametryDronow = parametryDronowNew.getDrony();
                if (oldDronyOfParametryDronow != null) {
                    oldDronyOfParametryDronow.setParametryDronow(null);
                    oldDronyOfParametryDronow = em.merge(oldDronyOfParametryDronow);
                }
                parametryDronowNew.setDrony(drony);
                parametryDronowNew = em.merge(parametryDronowNew);
            }
            if (IDstacjiOld != null && !IDstacjiOld.equals(IDstacjiNew)) {
                IDstacjiOld.getDronyCollection().remove(drony);
                IDstacjiOld = em.merge(IDstacjiOld);
            }
            if (IDstacjiNew != null && !IDstacjiNew.equals(IDstacjiOld)) {
                IDstacjiNew.getDronyCollection().add(drony);
                IDstacjiNew = em.merge(IDstacjiNew);
            }
            for (PoziomyBaterii poziomyBateriiCollectionOldPoziomyBaterii : poziomyBateriiCollectionOld) {
                if (!poziomyBateriiCollectionNew.contains(poziomyBateriiCollectionOldPoziomyBaterii)) {
                    poziomyBateriiCollectionOldPoziomyBaterii.setIDdrona(null);
                    poziomyBateriiCollectionOldPoziomyBaterii = em.merge(poziomyBateriiCollectionOldPoziomyBaterii);
                }
            }
            for (PoziomyBaterii poziomyBateriiCollectionNewPoziomyBaterii : poziomyBateriiCollectionNew) {
                if (!poziomyBateriiCollectionOld.contains(poziomyBateriiCollectionNewPoziomyBaterii)) {
                    Drony oldIDdronaOfPoziomyBateriiCollectionNewPoziomyBaterii = poziomyBateriiCollectionNewPoziomyBaterii.getIDdrona();
                    poziomyBateriiCollectionNewPoziomyBaterii.setIDdrona(drony);
                    poziomyBateriiCollectionNewPoziomyBaterii = em.merge(poziomyBateriiCollectionNewPoziomyBaterii);
                    if (oldIDdronaOfPoziomyBateriiCollectionNewPoziomyBaterii != null && !oldIDdronaOfPoziomyBateriiCollectionNewPoziomyBaterii.equals(drony)) {
                        oldIDdronaOfPoziomyBateriiCollectionNewPoziomyBaterii.getPoziomyBateriiCollection().remove(poziomyBateriiCollectionNewPoziomyBaterii);
                        oldIDdronaOfPoziomyBateriiCollectionNewPoziomyBaterii = em.merge(oldIDdronaOfPoziomyBateriiCollectionNewPoziomyBaterii);
                    }
                }
            }
            for (Koordynaty koordynatyCollectionOldKoordynaty : koordynatyCollectionOld) {
                if (!koordynatyCollectionNew.contains(koordynatyCollectionOldKoordynaty)) {
                    koordynatyCollectionOldKoordynaty.setIDdrona(null);
                    koordynatyCollectionOldKoordynaty = em.merge(koordynatyCollectionOldKoordynaty);
                }
            }
            for (Koordynaty koordynatyCollectionNewKoordynaty : koordynatyCollectionNew) {
                if (!koordynatyCollectionOld.contains(koordynatyCollectionNewKoordynaty)) {
                    Drony oldIDdronaOfKoordynatyCollectionNewKoordynaty = koordynatyCollectionNewKoordynaty.getIDdrona();
                    koordynatyCollectionNewKoordynaty.setIDdrona(drony);
                    koordynatyCollectionNewKoordynaty = em.merge(koordynatyCollectionNewKoordynaty);
                    if (oldIDdronaOfKoordynatyCollectionNewKoordynaty != null && !oldIDdronaOfKoordynatyCollectionNewKoordynaty.equals(drony)) {
                        oldIDdronaOfKoordynatyCollectionNewKoordynaty.getKoordynatyCollection().remove(koordynatyCollectionNewKoordynaty);
                        oldIDdronaOfKoordynatyCollectionNewKoordynaty = em.merge(oldIDdronaOfKoordynatyCollectionNewKoordynaty);
                    }
                }
            }
            for (PunktyKontrolne punktyKontrolneCollectionOldPunktyKontrolne : punktyKontrolneCollectionOld) {
                if (!punktyKontrolneCollectionNew.contains(punktyKontrolneCollectionOldPunktyKontrolne)) {
                    punktyKontrolneCollectionOldPunktyKontrolne.setIDdrona(null);
                    punktyKontrolneCollectionOldPunktyKontrolne = em.merge(punktyKontrolneCollectionOldPunktyKontrolne);
                }
            }
            for (PunktyKontrolne punktyKontrolneCollectionNewPunktyKontrolne : punktyKontrolneCollectionNew) {
                if (!punktyKontrolneCollectionOld.contains(punktyKontrolneCollectionNewPunktyKontrolne)) {
                    Drony oldIDdronaOfPunktyKontrolneCollectionNewPunktyKontrolne = punktyKontrolneCollectionNewPunktyKontrolne.getIDdrona();
                    punktyKontrolneCollectionNewPunktyKontrolne.setIDdrona(drony);
                    punktyKontrolneCollectionNewPunktyKontrolne = em.merge(punktyKontrolneCollectionNewPunktyKontrolne);
                    if (oldIDdronaOfPunktyKontrolneCollectionNewPunktyKontrolne != null && !oldIDdronaOfPunktyKontrolneCollectionNewPunktyKontrolne.equals(drony)) {
                        oldIDdronaOfPunktyKontrolneCollectionNewPunktyKontrolne.getPunktyKontrolneCollection().remove(punktyKontrolneCollectionNewPunktyKontrolne);
                        oldIDdronaOfPunktyKontrolneCollectionNewPunktyKontrolne = em.merge(oldIDdronaOfPunktyKontrolneCollectionNewPunktyKontrolne);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = drony.getId();
                if (findDrony(id) == null) {
                    throw new NonexistentEntityException("The drony with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Drony drony;
            try {
                drony = em.getReference(Drony.class, id);
                drony.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The drony with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            ParametryDronow parametryDronowOrphanCheck = drony.getParametryDronow();
            if (parametryDronowOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Drony (" + drony + ") cannot be destroyed since the ParametryDronow " + parametryDronowOrphanCheck + " in its parametryDronow field has a non-nullable drony field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Stacja IDstacji = drony.getIDstacji();
            if (IDstacji != null) {
                IDstacji.getDronyCollection().remove(drony);
                IDstacji = em.merge(IDstacji);
            }
            Collection<PoziomyBaterii> poziomyBateriiCollection = drony.getPoziomyBateriiCollection();
            for (PoziomyBaterii poziomyBateriiCollectionPoziomyBaterii : poziomyBateriiCollection) {
                poziomyBateriiCollectionPoziomyBaterii.setIDdrona(null);
                poziomyBateriiCollectionPoziomyBaterii = em.merge(poziomyBateriiCollectionPoziomyBaterii);
            }
            Collection<Koordynaty> koordynatyCollection = drony.getKoordynatyCollection();
            for (Koordynaty koordynatyCollectionKoordynaty : koordynatyCollection) {
                koordynatyCollectionKoordynaty.setIDdrona(null);
                koordynatyCollectionKoordynaty = em.merge(koordynatyCollectionKoordynaty);
            }
            Collection<PunktyKontrolne> punktyKontrolneCollection = drony.getPunktyKontrolneCollection();
            for (PunktyKontrolne punktyKontrolneCollectionPunktyKontrolne : punktyKontrolneCollection) {
                punktyKontrolneCollectionPunktyKontrolne.setIDdrona(null);
                punktyKontrolneCollectionPunktyKontrolne = em.merge(punktyKontrolneCollectionPunktyKontrolne);
            }
            em.remove(drony);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Drony> findDronyEntities() {
        return findDronyEntities(true, -1, -1);
    }

    public List<Drony> findDronyEntities(int maxResults, int firstResult) {
        return findDronyEntities(false, maxResults, firstResult);
    }

    private List<Drony> findDronyEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Drony.class));
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

    public Drony findDrony(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Drony.class, id);
        } finally {
            em.close();
        }
    }

    public int getDronyCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Drony> rt = cq.from(Drony.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
