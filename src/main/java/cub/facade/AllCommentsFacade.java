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

/**
 *
 * @author NT48810
 */
@Stateless
public class AllCommentsFacade {

    @PersistenceContext(unitName = "cub_invest-commons-ui_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public List<Object[]> getAllTabComments(String owner) {
        StringBuilder sql = new StringBuilder(100);
        sql.append("select at.TABLE_NAME, at.COMMENTS from ALL_TAB_COMMENTS at ")
            .append("where at.TABLE_NAME not like 'BIN$%' and at.OWNER ='")
            .append(owner).append("'");
        Query query = em.createNativeQuery(sql.toString());
        return query.getResultList();
    }

    public List<Object[]> getAllColComments(String owner, String tableName) {
        StringBuilder sql = new StringBuilder(100);
        sql.append("select ac.COLUMN_NAME, ac.COMMENTS from ALL_COL_COMMENTS ac ")
            .append("where ac.TABLE_NAME ='")
            .append(tableName).append("' and ac.OWNER ='")
            .append(owner).append("'");
        Query query = em.createNativeQuery(sql.toString());
        return query.getResultList();
    }

}
