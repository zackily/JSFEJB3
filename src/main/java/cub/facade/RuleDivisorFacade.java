/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package cub.facade;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import cub.entities.RuleDivisor;


/**
 *
 * @author NT48810
 */
@Stateless
public class RuleDivisorFacade extends AbstractFacade<RuleDivisor> {

    @PersistenceContext(unitName = "cub_invest-commons-ui_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RuleDivisorFacade() {
        super(RuleDivisor.class);
    }

    public List<RuleDivisor> findByRuleNo(String ruleNo) {
        StringBuilder jpql = new StringBuilder(100);
        jpql.append("from RuleDivisor r where r.ruleDivisorPK.ruleNo =:ruleNo");
        Query query = em.createQuery(jpql.toString());
        query.setParameter("ruleNo", ruleNo);
        return query.getResultList();
    }

    public void removeByRuleNo(String ruleNo) {
        StringBuilder jpql = new StringBuilder(100);
        jpql.append("delete from RuleDivisor r where r.ruleDivisorPK.ruleNo =:ruleNo");
        Query query = em.createQuery(jpql.toString());
        query.setParameter("ruleNo", ruleNo);
        query.executeUpdate(); 
    }

}
