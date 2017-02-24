/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onetomanypoc.datalayer;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import onetomanypoc.entity.Product;
import onetomanypoc.entity.Product_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import onetomanypoc.entity.PurchaseOrder;
import onetomanypoc.entity.Manufacturer;
import onetomanypoc.entity.ProductCode;

/**
 *
 * @author kuw
 */
@Stateless
public class ProductFacade extends AbstractFacade<Product> {

    @PersistenceContext(unitName = "OneToMany-PoCPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductFacade() {
        super(Product.class);
    }

    public boolean isPurchaseOrderListEmpty(Product entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Product> product = cq.from(Product.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(product, entity), cb.isNotEmpty(product.get(Product_.purchaseOrderList)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public List<PurchaseOrder> findPurchaseOrderList(Product entity) {
        return this.getMergedEntity(entity).getPurchaseOrderList();
    }

    public boolean isManufacturerIdEmpty(Product entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Product> product = cq.from(Product.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(product, entity), cb.isNotNull(product.get(Product_.manufacturerId)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public Manufacturer findManufacturerId(Product entity) {
        return this.getMergedEntity(entity).getManufacturerId();
    }

    public boolean isProductCodeEmpty(Product entity) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Product> product = cq.from(Product.class);
        cq.select(cb.literal(1L)).distinct(true).where(cb.equal(product, entity), cb.isNotNull(product.get(Product_.productCode)));
        return em.createQuery(cq).getResultList().isEmpty();
    }

    public ProductCode findProductCode(Product entity) {
        return this.getMergedEntity(entity).getProductCode();
    }
    
}
