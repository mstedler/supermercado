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
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import supermercado.controller.exceptions.EntidadeInexistente;
import supermercado.model.Funcionario;

/**
 *
 * @author KillerWorkstation
 */
public class FuncionarioController implements Serializable {

    public FuncionarioController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void criar(Funcionario funcionario) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(funcionario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public void criar(Funcionario... funcionarios) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            for (Funcionario funcionario : funcionarios) {
                em.persist(funcionario);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Funcionario> buscarFuncionarios() {
        return buscarFuncionarios(true, -1, -1);
    }

    public List<Funcionario> buscarFuncionarios(int maxResults, int firstResult) {
        return buscarFuncionarios(false, maxResults, firstResult);
    }

    private List<Funcionario> buscarFuncionarios(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Funcionario.class));
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

    public Funcionario logar(String login, String senha) throws NoResultException{
         EntityManager em = getEntityManager();
        try {
           String HQL_QUERY = ""
                    + "         FROM Funcionario f "
                    + "         WHERE f.login = :login AND f.senha = :senha";

            return (Funcionario)em.createQuery(HQL_QUERY).setParameter("login", login).setParameter("senha", senha).getSingleResult();
        } finally {
            em.close();
        }
    }

    public int getFuncionarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Funcionario> rt = cq.from(Funcionario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
