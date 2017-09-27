/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cub.facade;

import cub.entities.MgSetMaster;
import cub.vo.MgMasterVO;
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
public class MgSetMasterFacade extends AbstractFacade<MgSetMaster> {

    @PersistenceContext(unitName = "AUMPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MgSetMasterFacade() {
        super(MgSetMaster.class);
    }

    public List<MgSetMaster> findByMgMasterVO(MgMasterVO vo) {
        StringBuffer sql = new StringBuffer("SELECT msm FROM MgSetMaster msm WHERE 1=1 ");
        if (StringUtils.isNotEmpty(vo.getMgActMCode())) {
            sql.append(" and msm.mgActMCode =:mgActMCode ");
        }
        if (StringUtils.isNotEmpty(vo.getMgActMName())) {
            sql.append(" and msm.mgActMName =:mgActMName ");
        }
        if (StringUtils.isNotEmpty(vo.getMgActMType())) {
            sql.append(" and msm.mgActMType =:mgActMType ");
        }

        if (StringUtils.isNotEmpty(vo.getMgActMRateSet())) {
            sql.append(" and msm.mgActMRateSet =:mgActMRateSet ");
        }
        if (StringUtils.isNotEmpty(vo.getMgActMSaleChnl())) {
            sql.append(" and msm.mgActMSaleChnl =:mgActMSaleChnl ");
        }

        if (StringUtils.isNotEmpty(vo.getMgActMChargeObj())) {
            sql.append(" and msm.mgActMChargeObj =:mgActMChargeObj ");
        }
//        sql.append(" order by af.")

        Query q = em.createQuery(sql.toString());

        if (StringUtils.isNotEmpty(vo.getMgActMCode())) {
            q.setParameter("mgActMCode", vo.getMgActMCode());

        }
        if (StringUtils.isNotEmpty(vo.getMgActMName())) {
            q.setParameter("mgActMName", vo.getMgActMName());
        }
        if (StringUtils.isNotEmpty(vo.getMgActMType())) {
            q.setParameter("mgActMType", vo.getMgActMType());
        }

        if (StringUtils.isNotEmpty(vo.getMgActMRateSet())) {
            q.setParameter("mgActMRateSet", vo.getMgActMRateSet());
        }
        if (StringUtils.isNotEmpty(vo.getMgActMSaleChnl())) {
            q.setParameter("mgActMSaleChnl", vo.getMgActMSaleChnl());
        }

        if (StringUtils.isNotEmpty(vo.getMgActMChargeObj())) {
            q.setParameter("mgActMChargeObj", vo.getMgActMChargeObj());
        }

        return q.getResultList();
    }
}
