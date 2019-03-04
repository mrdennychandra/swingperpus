/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import entity.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author lenovo
 */
public class NotaDetailJpaController implements Serializable {

    public NotaDetailJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(NotaDetail notaDetail) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(notaDetail);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(NotaDetail notaDetail) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            notaDetail = em.merge(notaDetail);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = notaDetail.getId();
                if (findNotaDetail(id) == null) {
                    throw new NonexistentEntityException("The notaDetail with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            NotaDetail notaDetail;
            try {
                notaDetail = em.getReference(NotaDetail.class, id);
                notaDetail.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The notaDetail with id " + id + " no longer exists.", enfe);
            }
            em.remove(notaDetail);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<NotaDetail> findNotaDetailEntities() {
        return findNotaDetailEntities(true, -1, -1);
    }

    public List<NotaDetail> findNotaDetailEntities(int maxResults, int firstResult) {
        return findNotaDetailEntities(false, maxResults, firstResult);
    }

    private List<NotaDetail> findNotaDetailEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(NotaDetail.class));
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

    public NotaDetail findNotaDetail(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(NotaDetail.class, id);
        } finally {
            em.close();
        }
    }

    public int getNotaDetailCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<NotaDetail> rt = cq.from(NotaDetail.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<NotaDetail> findNotaDetailByCustomer(String customer){
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNativeQuery("SELECT d.* FROM NotaDetail d INNER JOIN Nota n ON n.id = d.fk_nota WHERE n.customername=?",NotaDetail.class);
            q.setParameter(1, customer);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
}
