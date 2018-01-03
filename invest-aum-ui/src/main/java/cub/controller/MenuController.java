package cub.controller;

import cub.entities.MenuTable;
import cub.facade.MenuTableFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

@ManagedBean(name = "menuController")
@ViewScoped
public class MenuController implements Serializable {

    private MenuModel model;
//    @EJB
//    private MenuTableFacade menuFacade;

    private String language;

    @PostConstruct
    public void init() {
        language ="TW";
//        model = new DefaultMenuModel();
//        //First submenu
//        List<MenuTable> firstList = menuFacade.findRoot();
//        for (MenuTable menu : firstList) {
//
//            DefaultSubMenu firstSubmenu = new DefaultSubMenu(menu.getMenuLabelTw());
//            List<MenuTable> secondList = menuFacade.findChild(menu);
//            for (MenuTable subMenu : secondList) {
//                List<MenuTable> thirdList = menuFacade.findChild(subMenu);
//                if (thirdList.isEmpty()) {
//                    DefaultMenuItem item = new DefaultMenuItem(subMenu.getMenuLabelTw());
//                    item.setUrl(subMenu.getMemuUrl());
//                    item.setIcon(subMenu.getMenuIcon());
//                    firstSubmenu.addElement(item);
//                } else {
//                    DefaultSubMenu secondSubmenu = new DefaultSubMenu(subMenu.getMenuLabelTw());
//                    for (MenuTable thirdMenu : thirdList) {
//                        DefaultMenuItem item = new DefaultMenuItem(thirdMenu.getMenuLabelTw());
//                        item.setUrl(thirdMenu.getMemuUrl());
//                        item.setIcon(thirdMenu.getMenuIcon());
//                        secondSubmenu.addElement(item);
//                    }
//                    firstSubmenu.addElement(secondSubmenu);
//                }
//
//            }
//
//            model.addElement(firstSubmenu);
//        }

    }

//    public void changeModel() {
//        if (!model.getElements().isEmpty()) {
//            model.getElements().clear();
//        }
//        List<MenuTable> firstList = menuFacade.findRoot();
//        for (MenuTable menu : firstList) {
//
//            String firstlabel = this.getLanguage().equalsIgnoreCase("TW") ? menu.getMenuLabelTw() : menu.getMenuLabelEn();
//            DefaultSubMenu firstSubmenu = new DefaultSubMenu(firstlabel);
//
//            List<MenuTable> secondList = menuFacade.findChild(menu);
//            for (MenuTable subMenu : secondList) {
//                String secondlabel = this.getLanguage().equalsIgnoreCase("TW") ? subMenu.getMenuLabelTw() : subMenu.getMenuLabelEn();
//                List<MenuTable> thirdList = menuFacade.findChild(subMenu);
//                if (thirdList.isEmpty()) {
//                    DefaultMenuItem item = new DefaultMenuItem(secondlabel);
//                    item.setUrl(subMenu.getMemuUrl());
//                    item.setIcon(subMenu.getMenuIcon());
//                    firstSubmenu.addElement(item);
//                } else {
//                    DefaultSubMenu secondSubmenu = new DefaultSubMenu(secondlabel);
//                    for (MenuTable thirdMenu : thirdList) {
//                        String thirdlabel = this.getLanguage().equalsIgnoreCase("TW") ? thirdMenu.getMenuLabelTw() : thirdMenu.getMenuLabelEn();
//                        DefaultMenuItem item = new DefaultMenuItem(thirdlabel);
//                        item.setUrl(thirdMenu.getMemuUrl());
//                        item.setIcon(thirdMenu.getMenuIcon());
//                        secondSubmenu.addElement(item);
//                    }
//                    firstSubmenu.addElement(secondSubmenu);
//                }
//
//            }
//
//            model.addElement(firstSubmenu);
//        }
//    }

//    public MenuModel getModel() {
//        return model;
//    }

    public void save() {
        addMessage("Success", "Data saved");
    }

    public void update() {
        addMessage("Success", "Data updated");
    }

    public void delete() {
        addMessage("Success", "Data deleted");
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
