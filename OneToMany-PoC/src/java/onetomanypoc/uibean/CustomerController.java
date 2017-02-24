package onetomanypoc.uibean;

import onetomanypoc.entity.Customer;
import onetomanypoc.datalayer.CustomerFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import onetomanypoc.entity.DiscountCode;
import onetomanypoc.entity.MicroMarket;

@Named(value = "customerController")
@ViewScoped
public class CustomerController extends AbstractController<Customer> {

    @Inject
    private DiscountCodeController discountCodeController;
    @Inject
    private MicroMarketController zipController;

    public CustomerController() {
        // Inform the Abstract parent controller of the concrete Customer Entity
        super(Customer.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        discountCodeController.setSelected(null);
        zipController.setSelected(null);
    }

    public DiscountCode getDiscountCode() {
        Customer selected = this.getSelected();
        if (selected != null) {
            CustomerFacade ejbFacade = (CustomerFacade) this.getFacade();
            return ejbFacade.findDiscountCode(selected);
        } else {
            return null;
        }
    }

    public MicroMarket getZip() {
        Customer selected = this.getSelected();
        if (selected != null) {
            CustomerFacade ejbFacade = (CustomerFacade) this.getFacade();
            return ejbFacade.findZip(selected);
        } else {
            return null;
        }
    }
    
    public boolean getIsPurchaseOrderListEmpty() {
        Customer selected = this.getSelected();
        if (selected != null) {
            CustomerFacade ejbFacade = (CustomerFacade) this.getFacade();
            return ejbFacade.isPurchaseOrderListEmpty(selected);
        } else {
            return true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of PurchaseOrder entities
     * that are retrieved from Customer?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for PurchaseOrder page
     */
    public String navigatePurchaseOrderList() {
        Customer selected = this.getSelected();

        if (selected != null) {
            CustomerFacade ejbFacade = (CustomerFacade) this.getFacade();
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("PurchaseOrder_items", ejbFacade.findPurchaseOrderList(selected));
        }
        return "/app/purchaseOrder/index";
    }

    public boolean getIsDiscountCodeEmpty() {
        Customer selected = this.getSelected();
        if (selected != null) {
            CustomerFacade ejbFacade = (CustomerFacade) this.getFacade();
            return ejbFacade.isDiscountCodeEmpty(selected);
        } else {
            return true;
        }
    }

    /**
     * Sets the "selected" attribute of the DiscountCode controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareDiscountCode(ActionEvent event) {
        Customer selected = this.getSelected();
        if (selected != null && discountCodeController.getSelected() == null) {
            CustomerFacade ejbFacade = (CustomerFacade) this.getFacade();
            discountCodeController.setSelected(ejbFacade.findDiscountCode(selected));
        }
    }

    public boolean getIsZipEmpty() {
        Customer selected = this.getSelected();
        if (selected != null) {
            CustomerFacade ejbFacade = (CustomerFacade) this.getFacade();
            return ejbFacade.isZipEmpty(selected);
        } else {
            return true;
        }
    }

    /**
     * Sets the "selected" attribute of the MicroMarket controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareZip(ActionEvent event) {
        Customer selected = this.getSelected();
        if (selected != null && zipController.getSelected() == null) {
            CustomerFacade ejbFacade = (CustomerFacade) this.getFacade();
            zipController.setSelected(ejbFacade.findZip(selected));
        }
    }

}
