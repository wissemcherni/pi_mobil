/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
import com.codename1.ui.spinner.Picker;
import com.myapp.entities.Livraison;
import com.myapp.services.service_livraison;
//import java.time.LocalDate;
//import java.time.ZoneId;
import com.codename1.ui.Display;
//import java.sql.SQLException;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;


/**
 *
 * @author wissem
 */

    public class addLivraison extends Form {

    public addLivraison(Form previous) {
        setTitle("Ajouter une nouvelle livraison");
        setLayout(BoxLayout.y());
        TextField tfNumCommande = new TextField("", "Numéro de commande");
        TextField tfNomLivreur = new TextField("", "Nom du livreur");
        /*Picker datePicker = new Picker();
        datePicker.setType(Display.PICKER_TYPE_DATE);
        datePicker.setDate(new java.util.Date());*/

        Button btnValider = new Button("Ajouter la livraison");
        Button btnListe = new Button("Liste des livraisons");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNumCommande.getText().length() == 0) || (tfNomLivreur.getText().length() == 0)) {
                    Dialog.show("Alerte", "Veuillez remplir tous les champs", new Command("OK"));
                } else {
                    try {
                        int numCommande = Integer.parseInt(tfNumCommande.getText().toString());
                        String nomLivreur = tfNomLivreur.getText().toString();
                        //LocalDate dateLivraison = datePicker.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                        Livraison livraison = new Livraison(numCommande, nomLivreur /* , dateLivraison*/);
                        if (service_livraison.getInstance().addLivraison(livraison)) {
                            Dialog.show("Succès", "Livraison ajoutée", new Command("OK"));
                            // Envoi du mail
        /*String to = "wissem.cherni@esprit.tn";
        String subject = "Nouvelle livraison ajoutée";
        String body = "Une nouvelle livraison a été ajoutée pour la commande n° " + l.getNum_commande() + " par le livreur " + l.getNom_livreur() + " à la date " + l.getDATE();
        String from = "wissem.cherni@esprit.tn";
        String password = "212JMT8602";
        String host = "smtp.gmail.com";

        // Création de la session
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
    @Override
    protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
        return new javax.mail.PasswordAuthentication(from, password);
    }
});

        // Création du message
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);
        message.setText(body);

        // Envoi du message
        Transport.send(message);

        System.out.println("Mail envoyé !");
    } catch (SQLException ex) {
        System.out.println("Erreur lors de l'ajout de la livraison : " + ex.getMessage());
    } catch (MessagingException ex) {
        System.out.println("Erreur lors de l'envoi du mail : " + ex.getMessage());
    } */
                        } else {
                            Dialog.show("ERREUR", "Erreur de serveur", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERREUR", "Le numéro de commande doit être un nombre entier", new Command("OK"));
                    }
                }
            }
        });

        btnListe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new ListLivraisons(addLivraison.this).show();
            }
        });

        addAll(tfNumCommande, tfNomLivreur /*, datePicker*/ , btnValider, btnListe);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    addLivraison(HomeFORM aThis) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

    

