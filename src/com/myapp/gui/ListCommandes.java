package com.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import java.util.ArrayList;
import com.myapp.entities.Commande;
import com.myapp.services.service_commande;

/**
 * Liste des commandes
 */
public class ListCommandes extends Form {
    private Commande selectedCommande;

    public ListCommandes(Form previous) {
        setTitle("List commandes ");
        setLayout(BoxLayout.y());

        ArrayList<Commande> commandes = service_commande.getInstance().getAllCommandes();
        for (Commande c : commandes ) {
            addElement(c);
        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

  public void addElement(Commande commande) {
    Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    Label numPanier = new Label("Numéro de panier : " + commande.getNum_panier());
    Label emetteur = new Label("Emetteur : " + commande.getEmetteur());
    Label depot = new Label("Dépôt : " + commande.getDepot());
    Label statut = new Label("Statut : " + commande.getStatut());
    Label type = new Label("Type : " + commande.getType());
    c.addAll(numPanier, emetteur, depot, statut, type);
    
    // create update button
    Button updateButton = new Button("Modifier");
    /*updateButton.addActionListener(e -> {
    if (selectedCommande != null) {
Commande selectedCommande = // get the selected Commande object from the list
new updateCommande(this, selectedCommande).show();

    }
});*/
    c.add(updateButton);
    
    // create delete button
    Button deleteButton = new Button("Supprimer");
    /*deleteButton.addActionListener(e -> {
        service_commande.getInstance().deleteCommande(commande.getId_commande());
        c.remove();
    });*/
    c.add(deleteButton);
    
    add(c);
}

}
            
