/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package cub.facade;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import cub.entities.TrParameterInfo;

/**
 *
 * @author NT48810
 */
@Stateless
public class TrParameterInfoFacade extends AbstractFacade<TrParameterInfo> {

    @PersistenceContext(unitName = "cub_invest-commons-ui_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TrParameterInfoFacade() {
        super(TrParameterInfo.class);
    }

    public List<TrParameterInfo> findByTrCode(String trCode) {
        StringBuilder jpql = new StringBuilder(100);
        jpql.append("from TrParameterInfo tr where tr.id.trCode=:trCode");
        Query query = em.createQuery(jpql.toString());
        query.setParameter("trCode", trCode);
        return query.getResultList();
    }

    public String findDescByParaName(String newName) {
        StringBuilder jpql = new StringBuilder(100);
        jpql.append("select tr.parameterDesc from TrParameterInfo tr where tr.id.parameterName=:newName");
        Query query = em.createQuery(jpql.toString());
        query.setParameter("newName", newName);
        try {
            return query.getSingleResult().toString();
        } catch (NoResultException e) {
            return null;
        }
    }

    public String findNameByCodeDesc(String parameterName, String parameterDesc) {
        StringBuilder jpql = new StringBuilder(100);
        jpql.append("select tr.id.parameterName from TrParameterInfo tr where tr.id.trCode=:parameterName and tr.parameterDesc=:parameterDesc");
        Query query = em.createQuery(jpql.toString());
        query.setParameter("parameterName", parameterName);
        query.setParameter("parameterDesc", parameterDesc);
        try {
            return query.getSingleResult().toString();
        } catch (NoResultException e) {
            return null;
        }
    }

}
