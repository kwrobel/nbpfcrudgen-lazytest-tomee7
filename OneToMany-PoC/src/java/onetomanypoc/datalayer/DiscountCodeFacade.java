/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onetomanypoc.datalayer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import onetomanypoc.entity.DiscountCode;
import onetomanypoc.entity.DiscountCode_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author kuw
 */
@Stateless
public class DiscountCodeFacade extends AbstractFacade<DiscountCode> {

    @PersistenceContext(unitName = "OneToMany-PoCPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DiscountCodeFacade() {
        super(DiscountCode.class);
    }

    public boolean isCustomerListEmpty(DiscountCode entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<DiscountCode> discountCode = cq.from(DiscountCode.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(discountCode, entity), cb.isNotEmpty(discountCode.get(DiscountCode_.customerList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }
    
}
