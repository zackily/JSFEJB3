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

import cub.entities.TrOptionItem;

/**
 *
 * @author NT48810
 */
@Stateless
public class TrOptionItemFacade extends AbstractFacade<TrOptionItem> {

    @PersistenceContext(unitName = "cub_invest-commons-ui_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TrOptionItemFacade() {
        super(TrOptionItem.class);
    }

    public List<TrOptionItem> findByCodeName(String trCode, String parameterName) {
        StringBuilder jpql = new StringBuilder(100);
        jpql.append("from TrOptionItem tr where tr.id.trCode=:trCode")
            .append(" and tr.id.parameterName=:parameterName");
        Query query = em.createQuery(jpql.toString());
        query.setParameter("trCode", trCode);
        query.setParameter("parameterName", parameterName);
        return query.getResultList();
    }

}
