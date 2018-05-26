/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supermercado.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import supermercado.controller.exceptions.EntidadeInexistente;
import supermercado.model.ItemEstoque;
import supermercado.model.Venda;
import java.util.HashMap;
import supermercado.model.ItemVenda;

/**
 *
 * @author KillerWorkstation
 */
public class VendaController implements Serializable {

    public VendaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Venda venda) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(venda);
            for (ItemVenda itemVenda : venda.getItensVenda()) {
                ItemEstoque itemEstoque = itemVenda.getProduto().getItemEstoque();
                itemEstoque.setQuantidade(itemEstoque.getQuantidade() - itemVenda.getQuantidade());
                itemVenda.setVenda(venda);
                em.persist(itemVenda);
                em.merge(itemEstoque);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Venda venda) throws EntidadeInexistente, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            venda = em.merge(venda);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = venda.getId();
                if (findVenda(id) == null) {
                    throw new EntidadeInexistente("The venda with id " + id + " no longer exists.");
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
            Venda venda;
            try {
                venda = em.getReference(Venda.class, id);
                venda.getId();
            } catch (EntityNotFoundException enfe) {
                throw new EntidadeInexistente("The venda with id " + id + " no longer exists.", enfe);
            }
            em.remove(venda);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Venda> findVendaEntities() {
        return findVendaEntities(true, -1, -1);
    }

    public List<Venda> findVendaEntities(int maxResults, int firstResult) {
        return findVendaEntities(false, maxResults, firstResult);
    }

    private List<Venda> findVendaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Venda.class));
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

    public List<Venda> findVendasByDateAndCaixa(Date data, int caixa) {
        EntityManager em = getEntityManager();
        try {
           String HQL_QUERY = ""
                    + "         FROM Venda v "
                    + "         WHERE CAST(v.dataVenda as date) = CAST(:data as date)"
                    + "         AND v.caixa = :caixa";

            return em.createQuery(HQL_QUERY).setParameter("data", data).setParameter("caixa", caixa).getResultList();
        } finally {
            em.close();
        }
    }

    public List<Venda> findVendasByDate(Date data) {
        EntityManager em = getEntityManager();
        try {
           String HQL_QUERY = ""
                    + "         FROM Venda v "
                    + "         WHERE CAST(v.dataVenda as date) = CAST(:data as date)";

            return em.createQuery(HQL_QUERY).setParameter("data", data).getResultList();
        } finally {
            em.close();
        }
    }

    public HashMap<Integer, Double> getQuantidadeProdutoByVendasDate(Date data) {
        EntityManager em = getEntityManager();
        try {
            String HQL_QUERY = "SELECT SUM(v.quantidade) as quantidade, v.produto.id"
                    + "         FROM ItemVenda v INNER JOIN Venda venda"
                    + "         ON v.venda.id = venda.id"
                    + "         WHERE CAST(venda.dataVenda as date) = CAST(:data as date)"
                    + "         GROUP BY produto_id";

            List<Object[]> list = em.createQuery(HQL_QUERY).setParameter("data", data).getResultList();
            HashMap<Integer, Double> map = new HashMap<Integer, Double>();
            for (Object[] x : list) {
                map.put((Integer) x[1], (Double) x[0]);
            }
            return map;
        } finally {
            em.close();
        }
    }

    public Venda findVenda(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Venda.class, id);
        } finally {
            em.close();
        }
    }

    public int getVendaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Venda> rt = cq.from(Venda.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
