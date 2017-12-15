/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cub.facade;

import cub.entities.MgSetDetail;
import cub.entities.MgSetMaster;
import cub.enums.MgSetMasterStatus;
import cub.vo.MgDetailVO;
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
public class MgSetDetailFacade extends AbstractFacade<MgSetDetail> {

    @PersistenceContext(unitName = "AUMPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MgSetDetailFacade() {
        super(MgSetDetail.class);
    }
     public MgSetDetail findByLastSeqMgSetDetail(MgSetMaster m) {
        StringBuffer sql = new StringBuffer("SELECT msd FROM MgSetDetail msd WHERE 1=1 ");
       // if (m != null) {
            sql.append(" and msd.mgSetMasterId =:mgSetMasterId and msd.mgActDCode =:mgActDCode");
     //   }
        sql.append(" order by msd.mgActDCode,msd.mgActDSeq desc");
        Query q = em.createQuery(sql.toString());
        q.setMaxResults(1);
      //   if (m != null) {
            q.setParameter("mgSetMasterId", m);
            q.setParameter("mgActDCode", (m != null && m.getMgActMCode() != null) ? m.getMgActMCode() : "");
     //   }
        try {
            return (MgSetDetail) q.getSingleResult();
        } catch (Exception e) {
//            e.printStackTrace();
            return null;
        }
    }
     
      public List<MgSetDetail> findByStatusNotInMgDetail(MgSetMasterStatus status) {
        StringBuffer sql = new StringBuffer("SELECT msd FROM MgSetDetail msd WHERE 1=1 ");
        if (status != null) {
            sql.append(" and (msd.status !=:status and msd.status is not null) ");
        }
        sql.append(" order by msd.id desc");
        Query q = em.createQuery(sql.toString());  
        if (status != null) {
            q.setParameter("status", status);
        }
        return q.getResultList();
    }
      
      public List<MgSetDetail> findByMgDetailVO(MgDetailVO vo) {
        StringBuffer sql = new StringBuffer("SELECT msd FROM MgSetDetail msd WHERE 1=1 ");
        if (StringUtils.isNotEmpty(vo.getMgActDCode())) {
            sql.append(" and msd.mgActDCode like :mgActDCode ");
        }
        if (StringUtils.isNotEmpty(vo.getMgActDName())) {
            sql.append(" and msd.mgActDName like :mgActDName ");
        }

         if (vo.getQueryStartDate() != null ) {
            sql.append(" and msd.mgActDStartDate >= :mgActDStartDate ");
        }
        
         if (vo.getQueryEndDate() != null ) {
            sql.append(" and msd.mgActDEndDate <= :mgActDEndDate ");
        }
         
        if (StringUtils.isNotEmpty(vo.getStatus())) {
            sql.append(" and msd.status =:status ");
        }
        
//        sql.append(" order by af.")

        Query q = em.createQuery(sql.toString());

        if (StringUtils.isNotEmpty(vo.getMgActDCode())) {
            q.setParameter("mgActDCode", "%"+vo.getMgActDCode()+"%");
        }
   
        if (StringUtils.isNotEmpty(vo.getMgActDName())) {           
            q.setParameter("mgActDName", "%"+vo.getMgActDName()+"%");
        }

         if (vo.getQueryStartDate() != null ) {
             q.setParameter("mgActDStartDate", new DateTime(vo.getQueryStartDate()).toString("yyyyMMdd"));
        }
        
         if (vo.getQueryEndDate() != null ) {
            q.setParameter("mgActDEndDate", new DateTime(vo.getQueryEndDate()).toString("yyyyMMdd"));
        }         
 
        if (StringUtils.isNotEmpty(vo.getStatus())) {
             q.setParameter("status", MgSetMasterStatus.valueOf(vo.getStatus()));
        }
        return q.getResultList();
    }
}
