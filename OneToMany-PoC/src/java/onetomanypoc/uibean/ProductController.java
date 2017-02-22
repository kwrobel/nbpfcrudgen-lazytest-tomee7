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
        ProductFacade ejbFacade = (ProductFacade) this.getFacade();
        Product entity = this.getSelected();
        if (entity != null) {
            return ejbFacade.isPurchaseOrderListEmpty(entity);
        } else {
            return false;
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
        Product attachedSelected = this.getAttachedSelected();

        if (attachedSelected != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("PurchaseOrder_items", this.getAttachedSelected().getPurchaseOrderList());
        }
        return "/app/purchaseOrder/index";
    }

    /**
     * Sets the "selected" attribute of the Manufacturer controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareManufacturerId(ActionEvent event) {
        if (this.getSelected() != null && manufacturerIdController.getSelected() == null) {
            manufacturerIdController.setSelected(this.getSelected().getManufacturerId());
        }
    }

    /**
     * Sets the "selected" attribute of the ProductCode controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareProductCode(ActionEvent event) {
        if (this.getSelected() != null && productCodeController.getSelected() == null) {
            productCodeController.setSelected(this.getSelected().getProductCode());
        }
    }
}
