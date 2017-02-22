package onetomanypoc.uibean;

import onetomanypoc.entity.ProductCode;
import onetomanypoc.datalayer.ProductCodeFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "productCodeController")
@ViewScoped
public class ProductCodeController extends AbstractController<ProductCode> {

    public ProductCodeController() {
        // Inform the Abstract parent controller of the concrete ProductCode Entity
        super(ProductCode.class);
    }

    public boolean getIsProductListEmpty() {
        ProductCodeFacade ejbFacade = (ProductCodeFacade) this.getFacade();
        ProductCode entity = this.getSelected();
        if (entity != null) {
            return ejbFacade.isProductListEmpty(entity);
        } else {
            return false;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Product entities that are
     * retrieved from ProductCode?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Product page
     */
    public String navigateProductList() {
        ProductCode attachedSelected = this.getAttachedSelected();

        if (attachedSelected != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Product_items", this.getAttachedSelected().getProductList());
        }
        return "/app/product/index";
    }

}
