/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cub.facade;

import cub.entities.WorkSeq;
import cub.enums.SeqTypeEnum;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author NT48810
 */
@Stateless
public class WorkSeqFacade extends AbstractFacade<WorkSeq> {

    @PersistenceContext(unitName = "cub_invest-commons-ui_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public WorkSeqFacade() {
        super(WorkSeq.class);
    }

    public String getWorkSeq(String code) {
        //getSeqType
        String seqType = SeqTypeEnum.valueOf(code).getCode();
        //checkSeqTypeExist?return SeqType+leftpad(SeqNo+1):return SeqType+leftpad(SeqNo=1)
        StringBuilder jpql = new StringBuilder(100);
        jpql.append("select w.seqNo from WorkSeq w where w.seqType=:seqType");
        Query query = em.createQuery(jpql.toString());
        query.setParameter("seqType", seqType);
        Object obj = null;
        try {
            obj = query.getSingleResult();
        } catch (NoResultException e) {
        }
        String no = null == obj ? "1" : (String) obj;
        return seqType + StringUtils.leftPad(no, 5, "0");
    }

    public void addWorkSeq(String code) {
        String seqType = SeqTypeEnum.valueOf(code).getCode();
        StringBuilder jpql = new StringBuilder(100);
        jpql.append("update WorkSeq w set w.seqNo=(select w.seqNo from WorkSeq w where w.seqType=:seqType)+1");
        Query query = em.createQuery(jpql.toString());
        query.setParameter("seqType", seqType);
        Object obj = null;
        try {
            obj = query.getSingleResult();
        } catch (NoResultException e) {
        }
        String newSeq = null == obj ? "1" : (String) obj;
        
    }
}
