/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.Compte;
import bean.OperationBanquaire;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import service.CompteFacade;
import service.OperationBanquaireFacade;

/**
 *
 * @author YOUNES
 */
@Named(value = "compteController")
@SessionScoped
public class CompteController implements Serializable {

    private Compte selected;
    private List<Compte> items;
    @EJB
    private CompteFacade ejbFacade;
    private OperationBanquaireFacade opfacade;
    private Double montant;

    public String debiter() {
        ejbFacade.debiter(selected, montant);
        selected = null;
        return "inicio";
    }

    public String crediter() {
        ejbFacade.crediter(selected, montant);
        selected = null;
        return "inicio";
    }

    public String save() {
        ejbFacade.save(selected);
        selected = null;
        return "inicio";
    }

    public OperationBanquaireFacade getOpfacade() {
        return opfacade;
    }

    public void setOpfacade(OperationBanquaireFacade opfacade) {
        this.opfacade = opfacade;
    }
    
    public List<OperationBanquaire> operations(){
    return opfacade.findByCompte(selected);
    }

    /**
     * Creates a new instance of CompteController
     */
    public CompteController() {
    }

    public Compte getSelected() {
        if (selected == null) {
            selected = new Compte();
        }
        return selected;
    }

    public void setSelected(Compte selected) {
        this.selected = selected;
    }

    public List<Compte> getItems() {
        items = ejbFacade.findAll();
        return items;
    }

    public void setItems(List<Compte> items) {
        this.items = items;
    }

    public CompteFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(CompteFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

}
