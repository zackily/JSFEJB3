/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cub.facade;

import cub.entities.UdColumnScopeDetail;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author NT48810
 */
@Stateless
public class UdColumnScopeDetailFacade extends AbstractFacade<UdColumnScopeDetail> {
    
    @PersistenceContext(unitName = "cub_invest-commons-ui_war_1.0-SNAPSHOTPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public UdColumnScopeDetailFacade() {
        super(UdColumnScopeDetail.class);
    }
    
    public void removeByColumnCode(String columnCode) {
        StringBuilder jpql = new StringBuilder(100);
        jpql.append("delete from UdColumnScopeDetail d where d.udColumnScopeDetailPK.udColumnCode =:columnCode");
        Query query = em.createQuery(jpql.toString());
        query.setParameter("columnCode", columnCode);
        query.executeUpdate();
    }
}
