/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cub.facade;

import cub.entities.MgSetDetail;
import cub.entities.MgSetDetailRngCfg;
import cub.entities.MgSetDetailSecCfg;
import cub.invest.aum.Fund;
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
public class MgSetDetailRngCfgFacade extends AbstractFacade<MgSetDetailRngCfg> {

    @PersistenceContext(unitName = "AUMPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MgSetDetailRngCfgFacade() {
        super(MgSetDetailRngCfg.class);
    }

      public List<MgSetDetailRngCfg> findByRng(String act_code, String act_sub_code) {
        StringBuffer sql = new StringBuffer("SELECT msdrc FROM MgSetDetailRngCfg msdrc WHERE msdrc.mgActCode =:mgActCode and msdrc.mgActSubCode =:mgActSubCode ");

        Query q = em.createQuery(sql.toString());

        q.setParameter("mgActCode", act_code);
        q.setParameter("mgActSubCode", act_sub_code);

        return q.getResultList();
    }
      
       public int removeByMgSetDetail(MgSetDetail detail) {
        StringBuffer sql = new StringBuffer("DELETE FROM MgSetDetailRngCfg msdrc WHERE msdrc.mgActCode =:mgActCode and msdrc.mgActSubCode =:mgActSubCode  ");
        Query q = em.createQuery(sql.toString());
        q.setParameter("mgActCode", detail.getMgSetMasterId().getMgActMCode());
        q.setParameter("mgActSubCode", detail.getMgActDSeq());
        return q.executeUpdate();
    }
      
}
