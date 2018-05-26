/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermercado.controller;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import supermercado.controller.exceptions.EntidadeInexistente;
import supermercado.model.ItemEstoque;

/**
 *
 * @author KillerWorkstation
 */
public class ItemEstoqueController implements Serializable {

    public ItemEstoqueController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ItemEstoque itemEstoque) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(itemEstoque);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void create(ItemEstoque... itensEstoque) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            for (ItemEstoque itemEstoque : itensEstoque) {
                em.persist(itemEstoque);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ItemEstoque itemEstoque) throws EntidadeInexistente, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            itemEstoque = em.merge(itemEstoque);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = itemEstoque.getId();
                if (findItemEstoque(id) == null) {
                    throw new EntidadeInexistente("The itemEstoque with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws EntidadeInexistente {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ItemEstoque itemEstoque;
            try {
                itemEstoque = em.getReference(ItemEstoque.class, id);
                itemEstoque.getId();
            } catch (EntityNotFoundException enfe) {
                throw new EntidadeInexistente("The itemEstoque with id " + id + " no longer exists.", enfe);
            }
            em.remove(itemEstoque);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ItemEstoque> findItemEstoqueEntities() {
        return findItemEstoqueEntities(true, -1, -1);
    }

    public List<ItemEstoque> findItemEstoqueEntities(int maxResults, int firstResult) {
        return findItemEstoqueEntities(false, maxResults, firstResult);
    }

    private List<ItemEstoque> findItemEstoqueEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ItemEstoque.class));
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

    public ItemEstoque findItemEstoque(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ItemEstoque.class, id);
        } finally {
            em.close();
        }
    }

    public int getItemEstoqueCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ItemEstoque> rt = cq.from(ItemEstoque.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
