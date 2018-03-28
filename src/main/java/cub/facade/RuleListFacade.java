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
import cub.vo.RcmmRequestObject;

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

    public List<RuleList> joinRuleList(RcmmRequestObject request, Short channelCode) {
        StringBuilder sql = new StringBuilder(200);
        sql.append("select A.RULE_NO, A.RULE_CHN_NAME, A.RTN_MESSAGE,")
            .append(" A.CLIENT_AGGREGATE, A.CHECK_COLUMN, A.DIVIDEND_AGGREGATE,")
            .append(" A.DIVISOR_SOURCE, A.DIVISOR_VALUE, A.LIMIT_CONDITION,")
            .append(" A.LIMIT_RATE, A.LIMIT_REACTION")
            .append(" from RULE_LIST A join RULE_PRODUCT B")
            .append(" on B.RULE_NO=A.RULE_NO and B.PRD_CODE=?")
            .append(" join RULE_TRADE_TYPE C")
            .append(" on C.RULE_NO=A.RULE_NO and C.TRADE_TYPE=?")
            .append(" join RULE_CHANNEL D")
            .append(" on D.RULE_NO=A.RULE_NO and D.CHANNEL_CODE=?")
            .append(" join RULE_CHECKTIME E")
            .append(" on E.RULE_NO=A.RULE_NO and E.CHECK_TIME=?")
            .append(" WHERE A.START_DATE <=?")
            .append(" and A.END_DATE>=?")
            .append(" and IS_LOCK=1");
        Query query = em.createNativeQuery(sql.toString());
        query.setParameter(1, request.getPrdCode());
        query.setParameter(2, request.getTradeType());
        query.setParameter(3, channelCode);
        query.setParameter(4, request.getCheckTiming());
        query.setParameter(5, request.getOrderDttm());
        query.setParameter(6, request.getOrderDttm());
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
