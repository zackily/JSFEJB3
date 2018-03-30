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

import cub.entities.CheckResultDetail;

/**
 *
 * @author NT48810
 */
@Stateless
public class CheckResultDetailFacade extends AbstractFacade<CheckResultDetail> {

    @PersistenceContext(unitName = "cub_invest-commons-ui_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CheckResultDetailFacade() {
        super(CheckResultDetail.class);
    }

    public List<CheckResultDetail> findByOrderNo(String orderNo) {
        StringBuilder jpql = new StringBuilder(100);
        jpql.append("from CheckResultDetail c where c.id.orderNo =:orderNo");
        Query query = em.createQuery(jpql.toString());
        query.setParameter("orderNo", orderNo);
        return query.getResultList();
    }
}
