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

import cub.entities.TrMaster;

/**
 *
 * @author NT48810
 */
@Stateless
public class TrMasterFacade extends AbstractFacade<TrMaster> {

    @PersistenceContext(unitName = "cub_invest-commons-ui_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TrMasterFacade() {
        super(TrMaster.class);
    }

    public TrMaster findByTrCode(String trCode) {
        StringBuilder jpql = new StringBuilder(100);
        jpql.append("from TrMaster m where m.trCode =:trCode");
        Query query = em.createQuery(jpql.toString());
        query.setParameter("trCode", trCode);
        return (TrMaster) query.getSingleResult();
    }

    public List<TrMaster> findAllSort() {
        StringBuilder jpql = new StringBuilder(100);
        jpql.append("from TrMaster tr order by tr.trCode asc");
        Query query = em.createQuery(jpql.toString());
        return query.getResultList();
    }

    public String findNameByTrCode(String trCode) {
        StringBuilder jpql = new StringBuilder(100);
        jpql.append("select m.trDesc from TrMaster m where m.trCode =:trCode");
        Query query = em.createQuery(jpql.toString());
        query.setParameter("trCode", trCode);
        return (String) query.getSingleResult();
    }

    public List<String> findByCode(String code) {
        StringBuilder jpql = new StringBuilder(100);
        jpql.append("select tr.trCode from TrMaster tr where a.trCode like :code");
        Query query = em.createQuery(jpql.toString());
        query.setParameter("code", code + "%");
        return query.getResultList();
    }

}
