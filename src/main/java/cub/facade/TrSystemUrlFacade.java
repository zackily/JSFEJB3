/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package cub.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cub.entities.TrSystemUrl;


/**
 *
 * @author NT48810
 */
@Stateless
public class TrSystemUrlFacade extends AbstractFacade<TrSystemUrl> {

    @PersistenceContext(unitName = "cub_invest-commons-ui_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TrSystemUrlFacade() {
        super(TrSystemUrl.class);
    }

}
