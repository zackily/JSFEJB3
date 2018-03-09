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

import cub.entities.RuleList;

/**
 *
 * @author NT48810
 */
@Stateless
public class RuleListFacade extends AbstractFacade<RuleList> {

    @PersistenceContext(unitName = "cub_invest-commons-ui_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RuleListFacade() {
        super(RuleList.class);
    }

    public List<RuleList> findAllSort() {
        StringBuilder jpql = new StringBuilder(100);
        jpql.append("from RuleList r order by r.ruleNo asc");
        Query query = em.createQuery(jpql.toString());
        return query.getResultList();
    }

    public List<String> findByRuleNo(String ruleNo) {
        StringBuilder jpql = new StringBuilder(100);
        jpql.append("select r.ruleNo from RuleList r where r.ruleNo like :ruleNo");
        Query query = em.createQuery(jpql.toString());
        query.setParameter("ruleNo", ruleNo + "%");
        return query.getResultList();
    }

    public void removeCascade(RuleList currentItem) {
        remove(currentItem);
        removeRuleProductByRuleNo(currentItem.getRuleNo());
        removeRuleTradeTypeByRuleNo(currentItem.getRuleNo());
        removeRuleChannelByRuleNo(currentItem.getRuleNo());
    }
    
    private void removeRuleProductByRuleNo(String ruleNo) {
        StringBuilder jpql = new StringBuilder(100);
        jpql.append("delete from RuleProduct r where r.ruleProductPK.ruleNo =:ruleNo");
        Query query = em.createQuery(jpql.toString());
        query.setParameter("ruleNo", ruleNo);
        query.executeUpdate();
    }

    private void removeRuleTradeTypeByRuleNo(String ruleNo) {
        StringBuilder jpql = new StringBuilder(100);
        jpql.append("delete from RuleTradeType r where r.ruleTradeTypePK.ruleNo =:ruleNo");
        Query query = em.createQuery(jpql.toString());
        query.setParameter("ruleNo", ruleNo);
        query.executeUpdate(); 
    }
    
    private void removeRuleChannelByRuleNo(String ruleNo) {
        StringBuilder jpql = new StringBuilder(100);
        jpql.append("delete from RuleChannel r where r.ruleChannelPK.ruleNo =:ruleNo");
        Query query = em.createQuery(jpql.toString());
        query.setParameter("ruleNo", ruleNo);
        query.executeUpdate(); 
    }
}
