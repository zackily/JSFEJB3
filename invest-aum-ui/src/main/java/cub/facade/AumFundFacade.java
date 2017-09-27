/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cub.facade;

import cub.entities.AumFund;
import cub.vo.AumFundVO;
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
public class AumFundFacade extends AbstractFacade<AumFund> {

    @PersistenceContext(unitName = "AUMPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AumFundFacade() {
        super(AumFund.class);
    }

    public List<AumFund> findByAumFundVO(AumFundVO vo) {
        StringBuffer sql = new StringBuffer("SELECT af FROM AumFund af WHERE 1=1 ");
        if (vo.getQueryStartDate() != null && vo.getQueryEndDate() != null) {
            sql.append(" and (af.fHldDate between :startDate and :endDate) ");
        }
        if (StringUtils.isNotEmpty(vo.getFundHldTstOrCust())) {
            sql.append(" and (af.fHldCustNo =:fundHldTstOrCust or af.fHldTstAcc =:fundHldTstOrCust ) ");
        }
        if (StringUtils.isNotEmpty(vo.getFundHldFundNo())) {
            sql.append(" and af.fHldFundNo =:fundHldFundNo ");
        }
//        sql.append(" order by af.")

        Query q = em.createQuery(sql.toString());
        if (vo.getQueryStartDate() != null && vo.getQueryEndDate() != null) {
            q.setParameter("startDate", new DateTime(vo.getQueryStartDate()).toString("yyyyMMdd"));
            q.setParameter("endDate", new DateTime(vo.getQueryEndDate()).toString("yyyyMMdd"));
        }
        if (StringUtils.isNotEmpty(vo.getFundHldTstOrCust())) {
            q.setParameter("fundHldTstOrCust", vo.getFundHldTstOrCust());
        }
        if (StringUtils.isNotEmpty(vo.getFundHldFundNo())) {
            q.setParameter("fHldFundNo", vo.getFundHldFundNo());
        }

        return q.getResultList();
    }
}
