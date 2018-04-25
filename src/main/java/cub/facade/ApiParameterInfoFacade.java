/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package cub.facade;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import cub.entities.ApiParameterInfo;

/**
 *
 * @author NT48810
 */
@Stateless
public class ApiParameterInfoFacade extends AbstractFacade<ApiParameterInfo> {

    @PersistenceContext(unitName = "cub_invest-commons-ui_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ApiParameterInfoFacade() {
        super(ApiParameterInfo.class);
    }

    public List<ApiParameterInfo> findByApiCode(String apiCode) {
        StringBuilder jpql = new StringBuilder(100);
        jpql.append("from ApiParameterInfo ap where ap.id.apiCode=:apiCode order by ap.id.apiCode");
        Query query = em.createQuery(jpql.toString());
        query.setParameter("apiCode", apiCode);
        return query.getResultList();
    }

    public void removeByApiCode(String apiCode) {
        StringBuilder jpql = new StringBuilder(100);
        jpql.append("delete from ApiParameterInfo ap where ap.id.apiCode=:apiCode");
        Query query = em.createQuery(jpql.toString());
        query.setParameter("apiCode", apiCode);
        query.executeUpdate();
    }
}
