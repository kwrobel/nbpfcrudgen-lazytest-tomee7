package onetomanypoc.uibean;

import onetomanypoc.entity.Product;
import onetomanypoc.datalayer.ProductFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "productController")
@ViewScoped
public class ProductController extends AbstractController<Product> {

    @Inject
    private ManufacturerController manufacturerIdController;
    @Inject
    private ProductCodeController productCodeController;

    public ProductController() {
        // Inform the Abstract parent controller of the concrete Product Entity
        super(Product.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        manufacturerIdController.setSelected(null);
        productCodeController.setSelected(null);
    }

    public boolean getIsPurchaseOrderListEmpty() {
        Product selected = this.getSelected();
        if (selected != null) {
            ProductFacade ejbFacade = (ProductFacade) this.getFacade();
            return ejbFacade.isPurchaseOrderListEmpty(selected);
        } else {
            return true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of PurchaseOrder entities
     * that are retrieved from Product?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for PurchaseOrder page
     */
    public String navigatePurchaseOrderList() {
        Product selected = this.getSelected();

        if (selected != null) {
            ProductFacade ejbFacade = (ProductFacade) this.getFacade();
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("PurchaseOrder_items", ejbFacade.findPurchaseOrderList(selected));
        }
        return "/app/purchaseOrder/index";
    }

    public boolean getIsManufacturerIdEmpty() {
        Product selected = this.getSelected();
        if (selected != null) {
            ProductFacade ejbFacade = (ProductFacade) this.getFacade();
            return ejbFacade.isManufacturerIdEmpty(selected);
        } else {
            return true;
        }
    }

    /**
     * Sets the "selected" attribute of the Manufacturer controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareManufacturerId(ActionEvent event) {
        Product selected = this.getSelected();
        if (selected != null && manufacturerIdController.getSelected() == null) {
            ProductFacade ejbFacade = (ProductFacade) this.getFacade();
            manufacturerIdController.setSelected(ejbFacade.findManufacturerId(selected));
        }
    }

    public boolean getIsProductCodeEmpty() {
        Product selected = this.getSelected();
        if (selected != null) {
            ProductFacade ejbFacade = (ProductFacade) this.getFacade();
            return ejbFacade.isProductCodeEmpty(selected);
        } else {
            return true;
        }
    }

    /**
     * Sets the "selected" attribute of the ProductCode controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareProductCode(ActionEvent event) {
        Product selected = this.getSelected();
        if (selected != null && productCodeController.getSelected() == null) {
            ProductFacade ejbFacade = (ProductFacade) this.getFacade();
            productCodeController.setSelected(ejbFacade.findProductCode(selected));
        }
    }

}
