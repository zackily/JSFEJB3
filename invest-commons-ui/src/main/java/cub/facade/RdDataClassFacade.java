/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cub.facade;

import cub.entities.RdDataClass;
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
public class RdDataClassFacade extends AbstractFacade<RdDataClass> {

    @PersistenceContext(unitName = "cub_invest-commons-ui_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RdDataClassFacade() {
        super(RdDataClass.class);
    }

    public List<RdDataClass> findAllSort() {
        StringBuilder jpql = new StringBuilder(100);
        jpql.append("from RdDataClass r order by r.classCode asc");
        Query query = em.createQuery(jpql.toString());
        return query.getResultList();
    }

    public String getClassNameByClassCode(short classCode) {
        StringBuilder jpql = new StringBuilder(100);
        jpql.append("select r.className from RdDataClass r where r.classCode=:classCode");
        Query query = em.createQuery(jpql.toString());
        query.setParameter("classCode", classCode);
        return null == query.getSingleResult() ? "" : query.getSingleResult().toString();
    }

}
