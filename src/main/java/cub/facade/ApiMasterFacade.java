/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cub.facade;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import cub.entities.ApiMaster;

/**
 *
 * @author NT48810
 */
@Stateless
public class ApiMasterFacade extends AbstractFacade<ApiMaster> {
    
    @PersistenceContext(unitName = "cub_invest-commons-ui_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public ApiMasterFacade() {
        super(ApiMaster.class);
    }

    public List<String> findByCode(String code) {
        StringBuilder jpql = new StringBuilder(100);
        jpql.append("select a.apiCode from ApiMaster a where a.apiCode like :code");
        Query query = em.createQuery(jpql.toString());
        query.setParameter("code", code + "%");
        return query.getResultList();
    }
    
    public List<ApiMaster> findByTrCode(String code) {
        StringBuilder jpql = new StringBuilder(100);
        jpql.append("from ApiMaster a where a.outputTrCode =:code");
        Query query = em.createQuery(jpql.toString());
        query.setParameter("code", code);
        return query.getResultList();
    }
    
}
