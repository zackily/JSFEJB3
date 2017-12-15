/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cub.facade;

import cub.entities.MgSetMaster;
import cub.enums.MgSetMasterStatus;
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

    public MgSetMaster findByLastIdMgMaster(String status) {
        StringBuffer sql = new StringBuffer("SELECT msm FROM MgSetMaster msm WHERE 1=1 ");
        if (StringUtils.isNotEmpty(status)) {
            sql.append(" and msm.status =:status ");
        }
        sql.append(" order by msm.id desc");
        Query q = em.createQuery(sql.toString());
        q.setMaxResults(1);
        if (StringUtils.isNotEmpty(status)) {
            q.setParameter("status", status);
        }
        try {
            return (MgSetMaster) q.getSingleResult();
        } catch (Exception e) {
//            e.printStackTrace();
            return null;
        }
    }
    
    public List<MgSetMaster> findByStatusInMgMaster(MgSetMasterStatus status) {
        StringBuffer sql = new StringBuffer("SELECT msm FROM MgSetMaster msm WHERE 1=1 ");
        if (status != null) {
            sql.append(" and (msm.status =:status and msm.status is not null) ");
        }
        sql.append(" order by msm.id desc");
        Query q = em.createQuery(sql.toString());  
        if (status != null) {
            q.setParameter("status", status);
        }
        return q.getResultList();
    }
    
     public List<MgSetMaster> findByStatusNotInMgMaster(MgSetMasterStatus status) {
        StringBuffer sql = new StringBuffer("SELECT msm FROM MgSetMaster msm WHERE 1=1 ");
        if (status != null) {
            sql.append(" and (msm.status !=:status and msm.status is not null) ");
        }
        sql.append(" order by msm.id desc");
        Query q = em.createQuery(sql.toString());  
        if (status != null) {
            q.setParameter("status", status);
        }
        return q.getResultList();
    }

    public List<MgSetMaster> findByMgMasterVO(MgMasterVO vo) {
        StringBuffer sql = new StringBuffer("SELECT msm FROM MgSetMaster msm WHERE 1=1 ");
        if (StringUtils.isNotEmpty(vo.getMgActMCode())) {
            sql.append(" and msm.mgActMCode like :mgActMCode ");
        }
        if (StringUtils.isNotEmpty(vo.getMgActMName())) {
            sql.append(" and msm.mgActMName like :mgActMName ");
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
        
        if (StringUtils.isNotEmpty(vo.getStatus())) {
            sql.append(" and msm.status =:status ");
        }
        
//        sql.append(" order by af.")

        Query q = em.createQuery(sql.toString());

        if (StringUtils.isNotEmpty(vo.getMgActMCode())) {
            q.setParameter("mgActMCode", "%"+vo.getMgActMCode()+"%");

        }
        if (StringUtils.isNotEmpty(vo.getMgActMName())) {
            q.setParameter("mgActMName", "%"+vo.getMgActMName()+"%");
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
        if (StringUtils.isNotEmpty(vo.getStatus())) {
             q.setParameter("status", MgSetMasterStatus.valueOf(vo.getStatus()));
        }
        return q.getResultList();
    }
}
