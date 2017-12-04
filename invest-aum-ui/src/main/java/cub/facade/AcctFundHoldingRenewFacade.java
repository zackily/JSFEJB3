/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cub.facade;

import cub.entities.AcctFundHoldingRenew;
import cub.entities.EricMgFeeMonthDetail;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;

/**
 *
 * @author NT48810
 */
@Stateless
public class AcctFundHoldingRenewFacade extends AbstractFacade<AcctFundHoldingRenew> {

    @PersistenceContext(unitName = "AUMPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AcctFundHoldingRenewFacade() {
        super(AcctFundHoldingRenew.class);
    }

    public List<AcctFundHoldingRenew> findByQuery(String custId, Date startDate, Date endDate) {
        StringBuffer sql = new StringBuffer("SELECT afhr FROM AcctFundHoldingRenew afhr WHERE 1 = 1 ");
        if (StringUtils.isNotEmpty(custId)) {
            sql.append(" and  afhr.custId =:custId ");
        }
        if (startDate != null) {
            sql.append(" and afhr.navDt >=:startDate ");
        }
        if (endDate != null) {
            sql.append(" and afhr.navDt <=:endDate ");
        }
        sql.append(" order by afhr.custId,afhr.navDt ");
        Query q = em.createQuery(sql.toString());

        q.setMaxResults(10000);
        
         if (StringUtils.isNotEmpty(custId)) {
             q.setParameter("custId", custId);
        }
        if (startDate != null) {
            q.setParameter("startDate", new DateTime(startDate).toString("yyyyMM"));
        }
        if (endDate != null) {
            q.setParameter("endDate", new DateTime(endDate).toString("yyyyMM"));
        }
        
        return q.getResultList();
    }
}
