/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cub.facade;

import cub.entities.MgFeeMonth;
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
public class MgFeeMonthFacade extends AbstractFacade<MgFeeMonth> {

    @PersistenceContext(unitName = "AUMPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MgFeeMonthFacade() {
        super(MgFeeMonth.class);
    }

    public List<MgFeeMonth> fineByBaseMonth(String startMonth, String endMonth, String custId) {
        StringBuilder hql = new StringBuilder(100);
        hql.append("SELECT m from MgFeeMonth m where m.baseMonth between :startMonth")
                .append(" and :endMonth and m.custId =:custId");
        Query sqlQuery = em.createQuery(hql.toString());
        sqlQuery.setParameter("startMonth", startMonth);
        sqlQuery.setParameter("endMonth", endMonth);
        sqlQuery.setParameter("custId", custId);
        return sqlQuery.getResultList();
    }

}
