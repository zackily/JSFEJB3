/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cub.facade;

import cub.entities.RdOptionItem;
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
public class RdOptionItemFacade extends AbstractFacade<RdOptionItem> {

    @PersistenceContext(unitName = "cub_invest-commons-ui_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RdOptionItemFacade() {
        super(RdOptionItem.class);
    }

    public List<RdOptionItem> findAllSort() {
        StringBuilder jpql = new StringBuilder(100);
        jpql.append("from RdOptionItem r where r.rdOptionItemPK.classCode = 9 order by r.rdOptionItemPK.classCode asc");
        Query query = em.createQuery(jpql.toString());
        return query.getResultList();
    }

    public List<RdOptionItem> findByClassCode(Short classCode) {
        StringBuilder jpql = new StringBuilder(100);
        jpql.append("from RdOptionItem r where r.rdOptionItemPK.classCode =:classCode order by r.rdOptionItemPK.classCode asc");
        Query query = em.createQuery(jpql.toString());
        query.setParameter("classCode", classCode);
        return query.getResultList();
    }
}
