/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cub.facade;

import cub.entities.RdDataColumn;
import cub.vo.QueryUdColumnScopeDetailVO;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author NT48810
 */
@Stateless
public class RdDataColumnFacade extends AbstractFacade<RdDataColumn> {

    @PersistenceContext(unitName = "cub_invest-commons-ui_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RdDataColumnFacade() {
        super(RdDataColumn.class);
    }

    public String getFieldCNNameMenu(QueryUdColumnScopeDetailVO vo) {
        StringBuilder jpql = new StringBuilder(100);
        jpql.append("select r.columnChnName from RdDataColumn r where r.rdDataColumnPK.classCode =:classCode ")
                .append("and r.rdDataColumnPK.tableName =:tableName and r.rdDataColumnPK.columnName =:columnName");
        Query query = em.createQuery(jpql.toString());
        query.setParameter("classCode", vo.getClassCode());
        query.setParameter("tableName", vo.getTableName());
        query.setParameter("columnName", vo.getColumnName());
        try {
            return query.getSingleResult().toString();
        } catch (NoResultException e) {
            return "";
        }
    }

    public List<RdDataColumn> getColumnByClassCode(String classCode) {
        StringBuilder jpql = new StringBuilder(100);
        jpql.append("from RdDataColumn r where r.rdDataColumnPK.classCode =:classCode ")
                .append("order by r.rdDataColumnPK.tableName, r.rdDataColumnPK.columnName");
        Query query = em.createQuery(jpql.toString());
        query.setParameter("classCode", classCode);
        return query.getResultList();
    }
}
