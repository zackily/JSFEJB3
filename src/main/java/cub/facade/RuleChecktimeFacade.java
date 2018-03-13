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

import cub.entities.RuleChecktime;

/**
 *
 * @author NT48810
 */
@Stateless
public class RuleChecktimeFacade extends AbstractFacade<RuleChecktime> {

    @PersistenceContext(unitName = "cub_invest-commons-ui_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RuleChecktimeFacade() {
        super(RuleChecktime.class);
    }

    public List<RuleChecktime> findAllSort() {
        StringBuilder jpql = new StringBuilder(100);
        jpql.append("from RuleChecktime r order by r.ruleChecktimePK.ruleNo r.ruleChecktimePK.checkTime asc");
        Query query = em.createQuery(jpql.toString());
        return query.getResultList();
    }

    public List<String> findItemNameByRuleNo(String ruleNo) {
        StringBuilder jpql = new StringBuilder(100);
        jpql.append("select rdi.ITEM_NAME from RD_OPTION_ITEM rdi ")
            .append("right join RULE_CHECKTIME rp on rdi.ITEM_CODE = ")
            .append("rp.CHECK_TIME where rp.RULE_NO=? and rdi.CLASS_CODE=4");
        Query query = em.createNativeQuery(jpql.toString());
        query.setParameter(1, ruleNo);
        return query.getResultList();
    }

    public void removeByRuleNo(String ruleNo) {
        StringBuilder jpql = new StringBuilder(100);
        jpql.append("delete from RuleChecktime rp where rp.ruleChecktimePK.ruleNo=:ruleNo");
        Query query = em.createQuery(jpql.toString());
        query.setParameter("ruleNo", ruleNo);
        query.executeUpdate();
    }

    public List<Short> findByRuleNo(String ruleNo) {
        StringBuilder jpql = new StringBuilder(100);
        jpql.append("select r.ruleChecktimePK.checkTime from RuleChecktime r where r.ruleChecktimePK.ruleNo=:ruleNo");
        Query query = em.createQuery(jpql.toString());
        query.setParameter("ruleNo", ruleNo);
        return query.getResultList();
    }

}
