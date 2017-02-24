package onetomanypoc.uibean;

import onetomanypoc.entity.MicroMarket;
import onetomanypoc.datalayer.MicroMarketFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "microMarketController")
@ViewScoped
public class MicroMarketController extends AbstractController<MicroMarket> {

    public MicroMarketController() {
        // Inform the Abstract parent controller of the concrete MicroMarket Entity
        super(MicroMarket.class);
    }

    public boolean getIsCustomerListEmpty() {
        MicroMarket selected = this.getSelected();
        if (selected != null) {
            MicroMarketFacade ejbFacade = (MicroMarketFacade) this.getFacade();
            return ejbFacade.isCustomerListEmpty(selected);
        } else {
            return true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Customer entities that
     * are retrieved from MicroMarket?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for Customer page
     */
    public String navigateCustomerList() {
        MicroMarket selected = this.getSelected();

        if (selected != null) {
            MicroMarketFacade ejbFacade = (MicroMarketFacade) this.getFacade();
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Customer_items", ejbFacade.findCustomerList(selected));
        }
        return "/app/customer/index";
    }

}
