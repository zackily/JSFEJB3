/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cub.facade;

import cub.entities.DataScopeDetail;
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
public class DataScopeDetailFacade extends AbstractFacade<DataScopeDetail> {

    @PersistenceContext(unitName = "cub_invest-commons-ui_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DataScopeDetailFacade() {
        super(DataScopeDetail.class);
    }

    public List<DataScopeDetail> findByMasterCode(String code) {
        StringBuilder jpql = new StringBuilder(100);
        jpql.append("from DataScopeDetail d where d.dataScopeDetailPK.scopeCode =:code");
        Query query = em.createQuery(jpql.toString());
        query.setParameter("code", code);
        return query.getResultList();
    }

    public void removeByMaster(String scopeCode) {
        StringBuilder jpql = new StringBuilder(100);
        jpql.append("delete from DataScopeDetail d where d.dataScopeDetailPK.scopeCode =:scopeCode");
        Query query = em.createQuery(jpql.toString());
        query.setParameter("scopeCode", scopeCode);
        query.executeUpdate();
    }

}
