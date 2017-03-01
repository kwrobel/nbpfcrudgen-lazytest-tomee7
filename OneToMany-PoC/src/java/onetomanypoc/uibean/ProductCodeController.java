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
        ProductCode selected = this.getSelected();
        if (selected != null) {
            ProductCodeFacade ejbFacade = (ProductCodeFacade) this.getFacade();
            return ejbFacade.isProductListEmpty(selected);
        } else {
            return true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Product entities that are
     * retrieved from ProductCode?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Product page
     */
    public String navigateProductList() {
        ProductCode selected = this.getSelected();
        if (selected != null) {
            ProductCodeFacade ejbFacade = (ProductCodeFacade) this.getFacade();
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Product_items", ejbFacade.findProductList(selected));
        }
        return "/app/product/index";
    }

}
