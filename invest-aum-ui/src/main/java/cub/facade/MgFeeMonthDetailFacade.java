/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cub.facade;

import cub.entities.MgFeeMonthDetail;
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
public class MgFeeMonthDetailFacade extends AbstractFacade<MgFeeMonthDetail> {

    @PersistenceContext(unitName = "AUMPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MgFeeMonthDetailFacade() {
        super(MgFeeMonthDetail.class);
    }

    public List<MgFeeMonthDetail> fineByBaseMonth(String baseDate, String custId) {
        StringBuilder hql = new StringBuilder(100);
        hql.append("SELECT m from MgFeeMonthDetail m where m.baseDate =:baseDate")
                .append(" and m.custId =:custId");
        Query sqlQuery = em.createQuery(hql.toString());
        sqlQuery.setParameter("baseDate", baseDate);
        sqlQuery.setParameter("custId", custId);
        return sqlQuery.getResultList();
    }

}
