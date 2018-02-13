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

import cub.entities.UdMethodDetail;

/**
 *
 * @author NT48810
 */
@Stateless
public class UdMethodDetailFacade extends AbstractFacade<UdMethodDetail> {

    @PersistenceContext(unitName = "cub_invest-commons-ui_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UdMethodDetailFacade() {
        super(UdMethodDetail.class);
    }

    public List<UdMethodDetail> findAllSort() {
        StringBuilder jpql = new StringBuilder(100);
        jpql.append("from UdMethodDetail m order by m.methodName asc");
        Query query = em.createQuery(jpql.toString());
        return query.getResultList();
    }

    public List<UdMethodDetail> findByMethodName(String tempMethodName) {
        // TODO Auto-generated method stub
        return null;
    }
}
