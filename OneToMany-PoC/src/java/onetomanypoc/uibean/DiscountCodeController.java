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
        DiscountCode selected = this.getSelected();
        if (selected != null) {
            DiscountCodeFacade ejbFacade = (DiscountCodeFacade) this.getFacade();
            return ejbFacade.isCustomerListEmpty(selected);
        } else {
            return true;
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
        DiscountCode selected = this.getSelected();
        if (selected != null) {
            DiscountCodeFacade ejbFacade = (DiscountCodeFacade) this.getFacade();
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Customer_items", ejbFacade.findCustomerList(selected));
        }
        return "/app/customer/index";
    }

}
