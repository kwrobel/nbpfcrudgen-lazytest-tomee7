package onetomanypoc.uibean;

import onetomanypoc.entity.PurchaseOrder;
import onetomanypoc.datalayer.PurchaseOrderFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "purchaseOrderController")
@ViewScoped
public class PurchaseOrderController extends AbstractController<PurchaseOrder> {

    @Inject
    private CustomerController customerIdController;
    @Inject
    private ProductController productIdController;

    public PurchaseOrderController() {
        // Inform the Abstract parent controller of the concrete PurchaseOrder Entity
        super(PurchaseOrder.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        customerIdController.setSelected(null);
        productIdController.setSelected(null);
    }

    public boolean getIsCustomerIdEmpty() {
        PurchaseOrder selected = this.getSelected();
        if (selected != null) {
            PurchaseOrderFacade ejbFacade = (PurchaseOrderFacade) this.getFacade();
            return ejbFacade.isCustomerIdEmpty(selected);
        } else {
            return true;
        }
    }

    /**
     * Sets the "selected" attribute of the Customer controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCustomerId(ActionEvent event) {
        PurchaseOrder selected = this.getSelected();
        if (selected != null && customerIdController.getSelected() == null) {
            PurchaseOrderFacade ejbFacade = (PurchaseOrderFacade) this.getFacade();
            customerIdController.setSelected(ejbFacade.findCustomerId(selected));
        }
    }

    public boolean getIsProductIdEmpty() {
        PurchaseOrder selected = this.getSelected();
        if (selected != null) {
            PurchaseOrderFacade ejbFacade = (PurchaseOrderFacade) this.getFacade();
            return ejbFacade.isProductIdEmpty(selected);
        } else {
            return true;
        }
    }

    /**
     * Sets the "selected" attribute of the Product controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareProductId(ActionEvent event) {
        PurchaseOrder selected = this.getSelected();
        if (selected != null && productIdController.getSelected() == null) {
            PurchaseOrderFacade ejbFacade = (PurchaseOrderFacade) this.getFacade();
            productIdController.setSelected(ejbFacade.findProductId(selected));
        }
    }

}
