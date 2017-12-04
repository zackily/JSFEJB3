/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cub.facade;

import cub.entities.MgCustActList;
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
public class MgCustActListFacade extends AbstractFacade<MgCustActList> {

    @PersistenceContext(unitName = "AUMPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MgCustActListFacade() {
        super(MgCustActList.class);
    }

    public List<MgCustActList> findByQuery(String act_code, String act_sub_code, String custId) {
        StringBuffer sql = new StringBuffer("SELECT mcal FROM MgCustActList mcal WHERE 1 = 1 ");
        if (StringUtils.isNotEmpty(custId)) {
            sql.append(" and  mcal.custId =:custId ");
        }
        if (StringUtils.isNotEmpty(act_code)) {
            sql.append(" and mcal.actCode =:actCode ");
        }
        if (StringUtils.isNotEmpty(act_sub_code)) {
            sql.append(" and mcal.actSubCode =:actSubCode ");
        }
        sql.append(" order by mcal.actCode,mcal.actSubCode,mcal.custId ");
        Query q = em.createQuery(sql.toString());

        q.setMaxResults(10000);

        if (StringUtils.isNotEmpty(custId)) {
            q.setParameter("custId", custId);
        }
        if (StringUtils.isNotEmpty(act_code)) {
            q.setParameter("actCode", act_code);
        }
        if (StringUtils.isNotEmpty(act_sub_code)) {
            q.setParameter("actSubCode", act_sub_code);
        }

        return q.getResultList();
    }
}
