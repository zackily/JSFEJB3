/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cub.facade;

import cub.entities.UdDataScopeMaster;
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
public class UdDataScopeMasterFacade extends AbstractFacade<UdDataScopeMaster> {

    @PersistenceContext(unitName = "cub_invest-commons-ui_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UdDataScopeMasterFacade() {
        super(UdDataScopeMaster.class);
    }

    public List<UdDataScopeMaster> findAllSort() {
        StringBuilder jpql = new StringBuilder(100);
        jpql.append("from UdDataScopeMaster m order by m.scopeCode asc");
        Query query = em.createQuery(jpql.toString());
        return query.getResultList();
    }
}
