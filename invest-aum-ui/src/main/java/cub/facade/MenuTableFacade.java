/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cub.facade;

import cub.entities.AumFund;
import cub.entities.MenuTable;
import cub.vo.AumFundVO;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;

/**
 *
 * @author NT48810
 */
@Stateless
public class MenuTableFacade extends AbstractFacade<MenuTable> {

    @PersistenceContext(unitName = "AUMPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MenuTableFacade() {
        super(MenuTable.class);
    }

   public List<MenuTable> findRoot(){
        StringBuffer sql = new StringBuffer("SELECT mt FROM MenuTable mt WHERE mt.isRoot='Y' order by mt.sequence"); 
        Query q = em.createQuery(sql.toString());
        return q.getResultList();
   }
   
   public List<MenuTable> findChild(MenuTable parent){
        StringBuffer sql = new StringBuffer("SELECT mt FROM MenuTable mt WHERE mt.isRoot ='N' and mt.parentId =:parentId order by mt.sequence"); 
        Query q = em.createQuery(sql.toString());
        q.setParameter("parentId", parent);
        return q.getResultList();
   }
}
