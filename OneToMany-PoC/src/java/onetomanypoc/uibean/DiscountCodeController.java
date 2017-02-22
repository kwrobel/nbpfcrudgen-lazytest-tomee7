package onetomanypoc.uibean;

import onetomanypoc.entity.DiscountCode;
import onetomanypoc.datalayer.DiscountCodeFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "discountCodeController")
@ViewScoped
public class DiscountCodeController extends AbstractController<DiscountCode> {

    public DiscountCodeController() {
        // Inform the Abstract parent controller of the concrete DiscountCode Entity
        super(DiscountCode.class);
    }

    public boolean getIsCustomerListEmpty() {
        DiscountCodeFacade ejbFacade = (DiscountCodeFacade) this.getFacade();
        DiscountCode entity = this.getSelected();
        if (entity != null) {
            return ejbFacade.isCustomerListEmpty(entity);
        } else {
            return false;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Customer entities that
     * are retrieved from DiscountCode?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for Customer page
     */
    public String navigateCustomerList() {
        DiscountCode attachedSelected = this.getAttachedSelected();

        if (attachedSelected != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Customer_items", this.getAttachedSelected().getCustomerList());
        }
        return "/app/customer/index";
    }

}
