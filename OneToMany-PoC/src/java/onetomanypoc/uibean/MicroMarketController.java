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
        MicroMarketFacade ejbFacade = (MicroMarketFacade) this.getFacade();
        MicroMarket entity = this.getSelected();
        if (entity != null) {
            return ejbFacade.isCustomerListEmpty(entity);
        } else {
            return false;
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
        MicroMarket attachedSelected = this.getAttachedSelected();

        if (attachedSelected != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Customer_items", this.getAttachedSelected().getCustomerList());
        }
        return "/app/customer/index";
    }

}
