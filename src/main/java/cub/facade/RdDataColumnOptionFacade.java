/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package cub.facade;

import cub.entities.RdDataColumnOption;
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
public class RdDataColumnOptionFacade extends AbstractFacade<RdDataColumnOption> {

    @PersistenceContext(unitName = "cub_invest-commons-ui_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RdDataColumnOptionFacade() {
        super(RdDataColumnOption.class);
    }

    public List<RdDataColumnOption> findByMasterCode(String udColumnCode) {
        StringBuilder jpql = new StringBuilder(100);
        jpql.append("from RdDataColumnOption rdo where rdo.rdDataColumnOptionPK.optionCode in (")
            .append("select udd.udColumnScopeDetailPK.dataCode from UdColumnScopeDetail udd")
            .append(" where udd.udColumnScopeDetailPK.udColumnCode =:udColumnCode)")
            .append(" order by rdo.rdDataColumnOptionPK.optionCode asc");
        Query query = em.createQuery(jpql.toString());
        query.setParameter("udColumnCode", udColumnCode);
        return query.getResultList();
    }

    public String getOpNameByCode(String optionCode) {
        StringBuilder jpql = new StringBuilder(100);
        jpql.append("select r.optionName from RdDataColumnOption r")
            .append(" where r.rdDataColumnOptionPK.optionCode =:optionCode");
        Query query = em.createQuery(jpql.toString());
        query.setParameter("optionCode", optionCode);
        try {
            return query.getSingleResult().toString();
        } catch (NoResultException e) {
            return null;
        }
    }
}
