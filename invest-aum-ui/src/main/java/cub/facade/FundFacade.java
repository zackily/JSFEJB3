/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cub.facade;

import cub.invest.aum.Fund;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author NT48810
 */
@Stateless
public class FundFacade extends AbstractFacade<Fund> {

    @PersistenceContext(unitName = "AUMPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FundFacade() {
        super(Fund.class);
    }

    public Fund findByFundId(String fundID) {
        StringBuffer sql = new StringBuffer("SELECT f FROM Fund f WHERE 1=1 ");
        if (StringUtils.isNotEmpty(fundID)) {
            sql.append(" and f.fundID =:fundID ");
        }

//        sql.append(" order by f.fundID");

        Query q = em.createQuery(sql.toString());
        if (StringUtils.isNotEmpty(fundID)) {
            q.setParameter("fundID",fundID);
        }
        try {
            return (Fund) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Fund> findByFundStatus(String status) {
        StringBuffer sql = new StringBuffer("SELECT f FROM Fund f WHERE 1=1 ");
        if (StringUtils.isNotEmpty(status)) {
            sql.append(" and f.fundStatus =:fundStatus ");
        }

        sql.append(" order by f.fundID");

        Query q = em.createQuery(sql.toString());
        if (StringUtils.isNotEmpty(status)) {
            q.setParameter("fundID", status);
        }

        return q.getResultList();
    }

    public List<Fund> findByInvCorpId(String status, String invcorp) {
        StringBuffer sql = new StringBuffer("SELECT f FROM Fund f WHERE 1=1 ");
        if (StringUtils.isNotEmpty(status)) {
            sql.append(" and f.fundStatus =:fundStatus ");
        }

        if (StringUtils.isNotEmpty(invcorp)) {
            sql.append(" and f.fundID like :invcorp ");
        }

        sql.append(" order by f.fundID ");

        Query q = em.createQuery(sql.toString());
        if (StringUtils.isNotEmpty(status)) {
            q.setParameter("fundStatus", status);
        }
        if (StringUtils.isNotEmpty(invcorp)) {
            q.setParameter("invcorp", invcorp + "%");
        }
        return q.getResultList();
    }

}
