/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myapp.gui;

import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.myapp.entities.Livraison;
import com.myapp.services.service_livraison;
import java.util.ArrayList;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;

import com.codename1.ui.Label;

/**
 *
 * @author wissem
 */
public class ListLivraisons extends Form {
    private Livraison selectedLivraison;

    public ListLivraisons(Form previous) {
        setTitle("Liste des livraisons");
        setLayout(BoxLayout.y());

        ArrayList<Livraison> livraisons = service_livraison.getInstance().getAllLivraisons();
        for (Livraison l : livraisons) {
            addElement(l);
        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    public void addElement(Livraison livraison) {
        Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Label numCommande = new Label("Numéro de commande : " + livraison.getNum_commande());
        Label nomLivreur = new Label("Nom du livreur : " + livraison.getNom_livreur());
        //Label date = new Label("Date de livraison : " + livraison.getDATE());
        c.addAll(numCommande, nomLivreur /*date*/ );

        // create update button
        Button updateButton = new Button("Modifier");
        /*updateButton.addActionListener(e -> {
            if (selectedLivraison != null) {
                new updateLivraison(this, selectedLivraison).show();
            }
        });*/
        c.add(updateButton);

        // create delete button
        Button deleteButton = new Button("Supprimer");
        /*deleteButton.addActionListener(e -> {
            service_livraison.getInstance().deleteLivraison(livraison.getId_livraison());
            c.remove();
        });*/
        c.add(deleteButton);

        add(c);
    }
}
