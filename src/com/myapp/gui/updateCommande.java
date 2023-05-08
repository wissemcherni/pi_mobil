/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.myapp.entities.Commande;
import com.myapp.services.service_commande;

public class updateCommande extends Form {

    private Commande commande;

    public updateCommande(Commande commande, Form previous) {
        this.commande = commande;

        setTitle("Modifier commande");
        setLayout(BoxLayout.y());

        TextField numPanier = new TextField(String.valueOf(commande.getNum_panier()), "Numéro de panier");
        TextField emetteur = new TextField(commande.getEmetteur(), "Emetteur");
        TextField depot = new TextField(Integer.toString(commande.getDepot()), "Dépôt");

        TextField statut = new TextField(commande.getStatut(), "Statut");
        TextField type = new TextField(commande.getType(), "Type");

        Button saveBtn = new Button("Enregistrer");
        saveBtn.addActionListener(e -> {
            commande.setNum_panier(Integer.parseInt(numPanier.getText()));
            commande.setEmetteur(emetteur.getText());
            commande.setDepot(Integer.parseInt(depot.getText()));

            commande.setStatut(statut.getText());
            commande.setType(type.getText());
            service_commande.getInstance().updateCommande(commande);
            previous.showBack();
        });

        add(new Label("Modifier la commande :"));
        add(numPanier);
        add(emetteur);
        add(depot);
        add(statut);
        add(type);
        add(saveBtn);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

}

