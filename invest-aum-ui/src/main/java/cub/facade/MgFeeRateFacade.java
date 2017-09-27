/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cub.facade;

import cub.entities.MgFeeRate;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author NT48810
 */
@Stateless
public class MgFeeRateFacade extends AbstractFacade<MgFeeRate> {

    @PersistenceContext(unitName = "AUMPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MgFeeRateFacade() {
        super(MgFeeRate.class);
    }
    
}
