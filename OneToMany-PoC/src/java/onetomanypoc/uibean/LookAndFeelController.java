/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onetomanypoc.uibean;

import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author kuw
 */
@Named(value = "lookAndFeelController")
@SessionScoped
public class LookAndFeelController implements Serializable {
    
    private String selectedTheme = "bootstrap";
    private List<String> availableThemes;

    public String getSelectedTheme() {
        return selectedTheme;
    }

    /**
     * Creates a new instance of LookAndFeelController
     */
    public LookAndFeelController() {
    }
    
    @PostConstruct
    public void init() {
        populateThemeList();
    }
    
    private void populateThemeList() {
        //TODO: Grab the list of themes dynamically from the all-theme.jar
    }
    
}
