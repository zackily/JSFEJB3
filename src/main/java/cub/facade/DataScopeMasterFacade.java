/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cub.facade;

import cub.entities.DataScopeMaster;
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
public class DataScopeMasterFacade extends AbstractFacade<DataScopeMaster> {

    @PersistenceContext(unitName = "cub_invest-commons-ui_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DataScopeMasterFacade() {
        super(DataScopeMaster.class);
    }

    public List<DataScopeMaster> findAllSort() {
        StringBuilder jpql = new StringBuilder(100);
        jpql.append("from DataScopeMaster m order by m.scopeCode asc");
        Query query = em.createQuery(jpql.toString());
        return query.getResultList();
    }

    public List<String> findByCode(String code) {
        StringBuilder jpql = new StringBuilder(100);
        jpql.append("select m.scopeCode from DataScopeMaster m where m.scopeCode like :code");
        Query query = em.createQuery(jpql.toString());
        query.setParameter("code", code + "%");
        return query.getResultList();
    }

    public boolean checkRuleNoExistByScopeCode(String scopeCode) {
        StringBuilder jpql = new StringBuilder(100);
        jpql.append("select rd.ruleDividendPK.ruleNo from RuleDividend rd where rd.scopeCode =:scopeCode")
                .append(" union select rds.ruleDivisorPK.ruleNo from RuleDivisor rds where rds.scopeCode =:scopeCode");
        Query query = em.createQuery(jpql.toString());
        query.setParameter("scopeCode", scopeCode);
        query.setParameter("scopeCode", scopeCode);
        return query.getResultList().size() > 0 ? true : false;
    }

}
