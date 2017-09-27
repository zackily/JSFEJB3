/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cub.facade;

import cub.entities.MgFeeMonth;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author NT48810
 */
@Stateless
public class MgFeeMonthFacade extends AbstractFacade<MgFeeMonth> {

    @PersistenceContext(unitName = "AUMPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MgFeeMonthFacade() {
        super(MgFeeMonth.class);
    }
    
}
