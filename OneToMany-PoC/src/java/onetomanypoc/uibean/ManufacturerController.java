package onetomanypoc.uibean;

import onetomanypoc.entity.Manufacturer;
import onetomanypoc.datalayer.ManufacturerFacade;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "manufacturerController")
@ViewScoped
public class ManufacturerController extends AbstractController<Manufacturer> {

    public ManufacturerController() {
        // Inform the Abstract parent controller of the concrete Manufacturer Entity
        super(Manufacturer.class);
    }

    public boolean getIsProductListEmpty() {
        Manufacturer selected = this.getSelected();
        if (selected != null) {
            ManufacturerFacade ejbFacade = (ManufacturerFacade) this.getFacade();
            return ejbFacade.isProductListEmpty(selected);
        } else {
            return true;
        }
    }

    /**
     * Sets the "items" attribute with a collection of Product entities that are
     * retrieved from Manufacturer?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Product page
     */
    public String navigateProductList() {
        Manufacturer selected = this.getSelected();
        if (selected != null) {
            ManufacturerFacade ejbFacade = (ManufacturerFacade) this.getFacade();
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Product_items", ejbFacade.findProductList(selected));
        }
        return "/app/product/index";
    }

}
