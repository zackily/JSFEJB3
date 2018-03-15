/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package cub.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import cub.entities.OrderInfo;

/**
 *
 * @author NT48810
 */
@Stateless
public class OrderInfoFacade extends AbstractFacade<OrderInfo> {

    @PersistenceContext(unitName = "cub_invest-commons-ui_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrderInfoFacade() {
        super(OrderInfo.class);
    }

    public String getOrderInfoSeq() {
        StringBuilder sql = new StringBuilder(100);
        sql.append("select to_char(ORDER_INFO_SEQ.NEXTVAL,'00000000') no from dual");
        Query query = em.createNativeQuery(sql.toString());
        return query.getSingleResult().toString();
    }

    public Short getChannelCode(String apid) {
        StringBuilder sql = new StringBuilder(100);
        sql.append("select channel_code from CHANNEL_APID_MAPPING where APID=?");
        Query query = em.createNativeQuery(sql.toString());
        query.setParameter(1, apid);
        try {
            return Short.valueOf(query.getSingleResult().toString());
        } catch (NoResultException e) {
            return 0;
        }
    }
}
