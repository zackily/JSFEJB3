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
import javax.persistence.NoResultException;
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

    public List<RdOptionItem> findAllSort(short classCode) {
        StringBuilder jpql = new StringBuilder(100);
        jpql.append("from RdOptionItem r where r.rdOptionItemPK.classCode =:classCode order by r.rdOptionItemPK.classCode asc");
        Query query = em.createQuery(jpql.toString());
        query.setParameter("classCode", classCode);
        return query.getResultList();
    }

    public List<Object[]> findByClassCode(Short classCode) {
        StringBuilder jpql = new StringBuilder(100);
        jpql.append("select r.rdOptionItemPK.itemCode, r.itemName from RdOptionItem r where r.rdOptionItemPK.classCode =:classCode order by r.rdOptionItemPK.itemCode asc");
        Query query = em.createQuery(jpql.toString());
        query.setParameter("classCode", classCode);
        return query.getResultList();
    }
    
    public List<RdOptionItem> findAllSortByItemCode() {
        StringBuilder jpql = new StringBuilder(100);
        jpql.append("from RdOptionItem r order by r.rdOptionItemPK.itemCode asc");
        Query query = em.createQuery(jpql.toString());
        return query.getResultList();
    }

    public List<Object[]> findAllItemCodes(String columnName) {
        StringBuilder jpql = new StringBuilder(100);
        jpql.append("select distinct roi.ITEM_CODE, roi.ITEM_NAME")
            .append(" from RD_OPTION_ITEM roi right join UD_METHOD_DETAIL umd")
            .append(" on roi.COLUMN_NAME = umd.PARAMETER_NAME")
            .append(" where roi.COLUMN_NAME = ? order by roi.ITEM_CODE");
        Query query = em.createNativeQuery(jpql.toString());
        query.setParameter(1, columnName);
        return query.getResultList();
    }
}
