/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cub.facade;

import cub.invest.aum.Fund;
import cub.invest.aum.InvCorp;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author NT48810
 */
@Stateless
public class InvCorpFacade extends AbstractFacade<InvCorp> {

    @PersistenceContext(unitName = "AUMPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InvCorpFacade() {
        super(InvCorp.class);
    }
  
    
//     public List<Fund> findBy(Fund obj){
//         StringBuffer sql = new StringBuffer("SELECT ic FROM InvCorp ic WHERE 1=1 ");
//        if (StringUtils.isNotEmpty(obj.getFundID())) {
//            sql.append(" and ic =:fundStatus ");
//        }
//      
//        sql.append(" order by f.fundID");
//
//        Query q = em.createQuery(sql.toString());
//        if (StringUtils.isNotEmpty(status)) {
//            q.setParameter("fundID", status);            
//        }      
//
//        return q.getResultList();
//    }
}
