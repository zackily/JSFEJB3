/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cub.facade;

import cub.entities.UdMethodMaster;
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
public class UdMethodMasterFacade extends AbstractFacade<UdMethodMaster> {

    @PersistenceContext(unitName = "cub_invest-commons-ui_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UdMethodMasterFacade() {
        super(UdMethodMaster.class);
    }

    public List<UdMethodMaster> findAllSort() {
        StringBuilder jpql = new StringBuilder(100);
        jpql.append("from UdMethodMaster m order by m.methodName asc");
        Query query = em.createQuery(jpql.toString());
        return query.getResultList();
    }
}
