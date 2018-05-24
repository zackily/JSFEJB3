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

import cub.entities.RuleOrderPage;

/**
 *
 * @author NT48810
 */
@Stateless
public class RuleOrderPageFacade extends AbstractFacade<RuleOrderPage> {

    @PersistenceContext(unitName = "cub_invest-commons-ui_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RuleOrderPageFacade() {
        super(RuleOrderPage.class);
    }

    public List<RuleOrderPage> findAllSort() {
        StringBuilder jpql = new StringBuilder(100);
        jpql.append("from RuleOrderPage r order by r.id.ruleNo r.id.orderPage asc");
        Query query = em.createQuery(jpql.toString());
        return query.getResultList();
    }

    public List<String> findItemNameByRuleNo(String ruleNo) {
        StringBuilder jpql = new StringBuilder(100);
        jpql.append("select rdi.ITEM_NAME from RD_OPTION_ITEM rdi ")
            .append("right join RULE_ORDER_PAGE rp on rdi.ITEM_CODE = ")
            .append("rp.ORDER_PAGE where rp.RULE_NO=? and rdi.CLASS_CODE=14");
        Query query = em.createNativeQuery(jpql.toString());
        query.setParameter(1, ruleNo);
        return query.getResultList();
    }

    public void removeByRuleNo(String ruleNo) {
        StringBuilder jpql = new StringBuilder(100);
        jpql.append("delete from RuleOrderPage rp where rp.id.ruleNo=:ruleNo");
        Query query = em.createQuery(jpql.toString());
        query.setParameter("ruleNo", ruleNo);
        query.executeUpdate();
    }

    public List<Short> findByRuleNo(String ruleNo) {
        StringBuilder jpql = new StringBuilder(100);
        jpql.append("select r.id.checkPage from RuleOrderPage r where r.id.ruleNo=:ruleNo");
        Query query = em.createQuery(jpql.toString());
        query.setParameter("ruleNo", ruleNo);
        return query.getResultList();
    }

}
