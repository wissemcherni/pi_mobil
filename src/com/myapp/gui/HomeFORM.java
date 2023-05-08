/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;


public class HomeFORM extends Form {

    private static final String TITLE = "Home";
    private static final String CHOOSE_OPTION_LABEL_TEXT = "Choose an option";
    private static final String COMMAND_BTN_TEXT = "Commande";
    private static final String LIVRAISON_BTN_TEXT = "Livraison";

    public HomeFORM() {
        super(TITLE);
        setLayout(new BorderLayout());

        Label lblTitle = new Label(TITLE);
        lblTitle.getStyle().setFgColor(0xFFFFFF);
        lblTitle.getStyle().setAlignment(Component.CENTER);
        add(BorderLayout.NORTH, lblTitle);

        Container buttonsContainer = new Container(new GridLayout(2, 1));
        buttonsContainer.getStyle().setAlignment(Component.CENTER);
        buttonsContainer.setUIID("ButtonsContainer");

        Button btnCommande = createIconButton(COMMAND_BTN_TEXT, FontImage.MATERIAL_SHOPPING_CART);
        btnCommande.addActionListener(e-> new addCommande(this).show());
        Button btnLivraison = createIconButton(LIVRAISON_BTN_TEXT, FontImage.MATERIAL_LOCAL_SHIPPING);
        btnLivraison.addActionListener(e-> new addLivraison(this).show());

        buttonsContainer.add(btnCommande);
        buttonsContainer.add(btnLivraison);

        add(BorderLayout.CENTER, buttonsContainer);

        Label lblChooseOption = new Label(CHOOSE_OPTION_LABEL_TEXT);
        lblChooseOption.getStyle().setFgColor(0x000000);
        lblChooseOption.getStyle().setAlignment(Component.CENTER);
        add(BorderLayout.SOUTH, lblChooseOption);
    }

    private Button createIconButton(String text, char icon) {
        Button button = new Button();
        button.setIcon(FontImage.createFixed("" + icon, FontImage.getMaterialDesignFont(), 0xFFFFFF, 450, 450));
        button.setUIID(text);
        button.getStyle().setPadding(Component.TOP, 20);
        button.getStyle().setPadding(Component.BOTTOM, 20);
        return button;
    }
}

