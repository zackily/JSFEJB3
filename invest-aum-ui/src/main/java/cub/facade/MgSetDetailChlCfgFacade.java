/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cub.facade;

import cub.entities.MgSetDetailChlCfg;
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
public class MgSetDetailChlCfgFacade extends AbstractFacade<MgSetDetailChlCfg> {

    @PersistenceContext(unitName = "AUMPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MgSetDetailChlCfgFacade() {
        super(MgSetDetailChlCfg.class);
    }

    public List<MgSetDetailChlCfg> findBySelectChannel(String act_code, String act_sub_code) {
        StringBuffer sql = new StringBuffer("SELECT msdcc FROM MgSetDetailChlCfg msdcc WHERE msdcc.mgActCode =:mgActCode and msdcc.mgActSubCode =:mgActSubCode ");

        Query q = em.createQuery(sql.toString());

        q.setParameter("mgActCode", act_code);
        q.setParameter("mgActSubCode", act_sub_code);

        return q.getResultList();
    }
}
