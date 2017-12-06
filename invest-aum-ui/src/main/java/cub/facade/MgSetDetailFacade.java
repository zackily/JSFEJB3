/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cub.facade;

import cub.entities.MgSetDetail;
import cub.entities.MgSetMaster;
import cub.enums.MgSetMasterStatus;
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
        if (m != null) {
            sql.append(" and msd.mgSetMasterId =:mgSetMasterId");
        }
        sql.append(" order by msd.mgActDSeq desc");
        Query q = em.createQuery(sql.toString());
        q.setMaxResults(1);
         if (m != null) {
            q.setParameter("mgSetMasterId", m);
        }
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
}
