/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package cub.facade;

import cub.entities.UdDataScopeDetail;

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
public class UdDataScopeDetailFacade extends AbstractFacade<UdDataScopeDetail> {

    @PersistenceContext(unitName = "cub_invest-commons-ui_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UdDataScopeDetailFacade() {
        super(UdDataScopeDetail.class);
    }

    /*
     * exist?true:false
     */
    public Boolean checkExistByUdColumnCode(String code) {
        StringBuilder jpql = new StringBuilder(100);
        jpql.append("from UdDataScopeDetail d where d.udColumnCode=:code");
        Query query = em.createQuery(jpql.toString());
        query.setParameter("code", code);
        return query.getResultList().size() > 0 ? true : false;
    }

    public List<UdDataScopeDetail> findByScopeCode(String code) {
        StringBuilder jpql = new StringBuilder(100);
        jpql.append("from UdDataScopeDetail d where d.id.scopeCode=:code");
        Query query = em.createQuery(jpql.toString());
        query.setParameter("code", code);
        return query.getResultList();
    }

    public void removeByMaster(String scopeCode) {
        StringBuilder jpql = new StringBuilder(100);
        jpql.append("delete from UdDataScopeDetail d where d.id.scopeCode =:scopeCode");
        Query query = em.createQuery(jpql.toString());
        query.setParameter("scopeCode", scopeCode);
        query.executeUpdate();
    }
}
