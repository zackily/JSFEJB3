/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cub.facade;

import cub.entities.UdColumnScopeMaster;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author NT48810
 */
@Stateless
public class UdColumnScopeMasterFacade extends AbstractFacade<UdColumnScopeMaster> {

    @PersistenceContext(unitName = "cub_invest-commons-ui_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UdColumnScopeMasterFacade() {
        super(UdColumnScopeMaster.class);
    }

    public List<String> findByCode(String code) {
        StringBuilder jpql = new StringBuilder(100);
        jpql.append("select u.udColumnCode from UdColumnScopeMaster u where u.udColumnCode like :code");
        Query query = em.createQuery(jpql.toString());
        query.setParameter("code", code + "%");
        return query.getResultList();
    }
    
    public String findByColumnCode(String code) {
        StringBuilder jpql = new StringBuilder(100);
        jpql.append("select u.udColumnName from UdColumnScopeMaster u where u.udColumnCode =:code");
        Query query = em.createQuery(jpql.toString());
        query.setParameter("code", code);
        return null != query.getSingleResult() ? query.getSingleResult().toString() : "";
    }
    
    public List<UdColumnScopeMaster> findAllSort(){
        StringBuilder jpql = new StringBuilder(100);
        jpql.append("from UdColumnScopeMaster u order by u.udColumnCode asc");
        Query query = em.createQuery(jpql.toString());
        return query.getResultList();
    }
}
