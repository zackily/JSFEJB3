/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package cub.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author NT48810
 */
@Stateless
public class AllCommentsFacade {

    @PersistenceContext(unitName = "cub_invest-commons-ui_war_1.0-FUND")
    private EntityManager emFund;
    @PersistenceContext(unitName = "cub_invest-commons-ui_war_1.0-GOLD")
    private EntityManager emGold;
    private static final  String IVTLXIVP01FUND = "IVTLXIVP01FUND";
    private static final  String IVTLXIVP01GOLD = "IVTLXIVP01GOLD";

    public String[] getAllTabComments(String owner) {
        StringBuilder sql = new StringBuilder(100);
        sql.append("select TABLE_NAME, COMMENTS from ALL_TAB_COMMENTS ")
            .append("where OWNER='")
            .append(owner)
            .append("' and TABLE_NAME not like 'BIN$%'");
        Query query = getCurrentEntityManager(owner).createQuery(sql.toString());
        try {
            return (String[]) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public String[] getAllColComments(String owner) {
        StringBuilder sql = new StringBuilder(100);
        sql.append("select TABLE_NAME, COMMENTS from ALL_COL_COMMENTS ")
            .append("where OWNER='")
            .append(owner)
            .append("' and TABLE_NAME not like 'BIN$%'");
        Query query = getCurrentEntityManager(owner).createQuery(sql.toString());
        try {
            return (String[]) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    private EntityManager getCurrentEntityManager(String owner) {
        switch (owner) {
            case IVTLXIVP01FUND:
                return emFund;
            case IVTLXIVP01GOLD:
                return emGold;
            default:
                return emFund;
        }
    }
}
