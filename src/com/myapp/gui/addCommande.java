package com.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.myapp.services.service_commande;
import com.myapp.entities.Commande;
import java.util.HashMap;
import java.util.Map;

public class addCommande extends Form {

    public addCommande(Form previous) {
        setTitle("Ajouter une nouvelle commande");
        setLayout(BoxLayout.y());
        TextField tfNumPanier = new TextField("", "Numéro de panier");
        TextField tfEmetteur = new TextField("", "Emetteur");
        TextField tfDepot = new TextField("", "Dépôt");
        TextField tfStatut = new TextField("", "Statut");
        TextField tfType = new TextField("", "Type");
        Button btnValider = new Button("Ajouter la commande");
        Button btnListe = new Button("Liste des commandes");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNumPanier.getText().length() == 0) || (tfEmetteur.getText().length() == 0) || (tfDepot.getText().length() == 0) || (tfStatut.getText().length() == 0) || (tfType.getText().length() == 0)) {
                    Dialog.show("Alerte", "Veuillez remplir tous les champs", new Command("OK"));
                } else {
                    try {
                        int depot = Integer.parseInt(tfDepot.getText().toString());
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("numPanier", tfNumPanier.getText().toString());
                        map.put("emetteur", tfEmetteur.getText().toString());
                        map.put("depot", depot);
                        map.put("statut", tfStatut.getText().toString());
                        map.put("type", tfType.getText().toString());
                       Commande commande = new Commande();
commande.setNum_panier(Integer.parseInt((String) map.get("numPanier")));
commande.setEmetteur((String) map.get("emetteur"));
commande.setDepot((int) map.get("depot"));
commande.setStatut((String) map.get("statut"));
commande.setType((String) map.get("type"));

if (service_commande.getInstance().addCommande(commande))  {
                            Dialog.show("Succès", "Commande ajoutée", new Command("OK"));
                            //new service_commande().imprimer(commande);
                        } else
                            Dialog.show("ERREUR", "Erreur de serveur", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERREUR", "Le dépôt doit être un nombre entier", new Command("OK"));
                    }
                }
            }
        });

        btnListe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new ListCommandes(addCommande.this).show();
            }
        });

        addAll(tfNumPanier, tfEmetteur, tfDepot, tfStatut, tfType, btnValider, btnListe);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
}
