/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cub.facade;

import cub.entities.RdDataColumn;
import cub.entities.RdDataColumnPK;
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
        query.setParameter("classCode", Short.valueOf(classCode));
        return query.getResultList();
    }

    public List<RdDataColumn> findAllSort() {
        StringBuilder jpql = new StringBuilder(100);
        jpql.append("from RdDataColumn r ")
                .append("order by r.rdDataColumnPK.tableName, r.rdDataColumnPK.columnName");
        Query query = em.createQuery(jpql.toString());
        return query.getResultList();
    }

    public List<String> findByCode(String code) {
        StringBuilder jpql = new StringBuilder(100);
        jpql.append("select r.rdDataColumnPK.classCode from RdDataColumn r where r.rdDataColumnPK.classCode like :code");
        Query query = em.createQuery(jpql.toString());
        query.setParameter("code", code + "%");
        return query.getResultList();
    }

    public boolean checkRuleNoExistByPK(RdDataColumnPK pk) {
        StringBuilder jpql = new StringBuilder(100);
        jpql.append("select * from UdColumnScopeMaster mm left join")
            .append(" (select m.classCode, d.tableName, d.columnName from DataScopeMaster m")
            .append(" left join DataScopeDetail d ON m.scopeCode=d.dataScopeDetailPK.scopeCode) n")
            .append(" on mm.classCode = n.classCode and mm.tableName = n.tableName and mm.columnName = n.columnName")
            .append(" where mm.classCode=:code and mm.tableName=:tableName and mm.columnName=:columnName");
        Query query = em.createQuery(jpql.toString());
        query.setParameter("code", pk.getClassCode());
        query.setParameter("tableName", pk.getTableName());
        query.setParameter("columnName", pk.getColumnName());
        return query.getResultList().size() > 0 ? true : false;
    }

}
