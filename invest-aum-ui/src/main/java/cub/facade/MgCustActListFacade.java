/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cub.facade;

import cub.entities.MgCustActList;
import cub.entities.MgSetDetail;
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

    public MgCustActList findBySaveOrUpdate(MgCustActList obj) {
        MgCustActList newObj = findByQuery(obj);
        if (newObj == null) {
            return save(obj);
        } else {
            newObj.setActStartDt(obj.getActStartDt());
            newObj.setActEndDt(obj.getActEndDt());
            newObj.setActDataDt(obj.getActDataDt());
            newObj.setUpdateDttm(new Date());
            return save(newObj);
        }
    }

    public int removeByMgSetDetail(MgSetDetail detail) {
        StringBuffer sql = new StringBuffer("DELETE FROM MgCustActList mcal WHERE mcal.actCode =:actCode and mcal.actSubCode =:actSubCode  ");
        Query q = em.createQuery(sql.toString());
        q.setParameter("actCode", detail.getMgSetMasterId().getMgActMCode());
        q.setParameter("actSubCode", detail.getMgActDSeq());
        return q.executeUpdate();
    }

    public MgCustActList findByQuery(MgCustActList obj) {
        StringBuffer sql = new StringBuffer("SELECT mcal FROM MgCustActList mcal WHERE 1=1 ");
        if (obj != null && StringUtils.isNotEmpty(obj.getCustId())) {
            sql.append(" and  mcal.custId =:custId ");
        }

        if (obj != null && StringUtils.isNotEmpty(obj.getActCode())) {
            sql.append(" and  mcal.actCode =:actCode ");
        }
        if (obj != null && StringUtils.isNotEmpty(obj.getActSubCode())) {
            sql.append(" and  mcal.actSubCode =:actSubCode ");
        }
        Query q = em.createQuery(sql.toString());

        if (obj != null && StringUtils.isNotEmpty(obj.getCustId())) {
            q.setParameter("actCode", obj.getCustId());
        }
        if (obj != null && StringUtils.isNotEmpty(obj.getActCode())) {
            q.setParameter("custId", obj.getActCode());
        }
        if (obj != null && StringUtils.isNotEmpty(obj.getActSubCode())) {
            q.setParameter("actSubCode", obj.getActSubCode());
        }
        try {
            return (MgCustActList) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
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
