/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.myapp.entities.Commande;
import com.myapp.utils.statics;
//import java.awt.Desktop;
//import java.io.File;
//import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
//import javax.swing.JOptionPane;

/**
 *
 * @author wissem
 */
public class service_commande {
    
    public ArrayList<Commande> commandes;

    public static service_commande instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public service_commande() {
        req = new ConnectionRequest();
    }

    public static service_commande getInstance() {
        if (instance == null) {
            instance = new service_commande();
        }
        return instance;
    }

    public boolean addCommande(Commande c) {

        int num_panier = c.getNum_panier() ;
    String emetteur = c.getEmetteur();
    int depot = c.getDepot();
      String statut = c.getStatut() ;
     String type =c.getType();
        
        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
        String url = statics.BASE_URL+"/commande/contoller/new" + num_panier + "/" + emetteur + "/" + depot + "/" + statut + "/" + type;

        req.setUrl(url);
        req.setPost(false);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<Commande> parseCommande(String jsonText) {
    try {
        commandes = new ArrayList<>();
        JSONParser j = new JSONParser();
        Map<String, Object> commandesListJson
                = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

        List<Map<String, Object>> list = (List<Map<String, Object>>) commandesListJson.get("root");
        for (Map<String, Object> obj : list) {
            Commande c = new Commande();
            //float id_commande = Float.parseFloat(obj.get("id_commande").toString());
            //c.setId_commande((int) id_commande);
            c.setNum_panier(((int) Float.parseFloat(obj.get("num_panier").toString())));
            c.setEmetteur(obj.get("emetteur").toString());
            c.setDepot(Integer.parseInt(obj.get("depot").toString()));
            c.setStatut(String.valueOf((int) Float.parseFloat(obj.get("statut").toString())));
            c.setType(obj.get("type").toString());
            commandes.add(c);
        }

    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    return commandes;
}
  

    public ArrayList<Commande> getAllCommandes() {
    String url = statics.BASE_URL+"/commande/contoller/";
    req.setUrl(url);
    req.setPost(false);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            commandes = parseCommande(new String(req.getResponseData()));
            req.removeResponseListener(this);
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return commandes;


    
}
    
    public boolean deleteCommande(Commande c) {

    int id_commande = c.getId_commande();
    String url = statics.BASE_URL+"/commande/contoller/new/{idCommande}" + id_commande;

    req.setUrl(url);
    req.setHttpMethod("DELETE");

    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
            req.removeResponseListener(this);
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOK;
}

public boolean updateCommande(Commande c) {

    int id_commande = c.getId_commande();
    int num_panier = c.getNum_panier() ;
    String emetteur = c.getEmetteur();
    int depot = c.getDepot();
    String statut = c.getStatut() ;
    String type = c.getType();
        
    String url = statics.BASE_URL+"/commande/contoller/new/{idCommande}/edit" + id_commande + "/" + num_panier + "/" + emetteur + "/" + depot + "/" + statut + "/" + type;

    req.setUrl(url);
    req.setHttpMethod("PUT");
        
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
            req.removeResponseListener(this);
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOK;
}
/*public void imprimer(Commande c) throws IOException, DocumentException {
    // Créer le fichier PDF
    File folder = new File("documents");
    if (!folder.exists()) {
        folder.mkdir();
    }
    String nom_fichier = "documents/info.pdf";
    Document document = new Document();
    PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(nom_fichier));
    MyHeaderFooter event = new MyHeaderFooter();
    writer.setPageEvent(event);
    document.open();

    // Ajouter une table pour les données de la commande
    PdfPTable table = new PdfPTable(2);
    table.setWidthPercentage(100);
    table.getDefaultCell().setPadding(5);
    table.getDefaultCell().setBorderWidth(0);

    PdfPCell cell;
    //Ajouter une couleur d'arrière-plan pour la première ligne
    cell = new PdfPCell(new Phrase("Dépôt", new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD)));
    cell.setBackgroundColor(new BaseColor(179, 204, 255));
    cell.setBorderWidthBottom(1f);
    table.addCell(cell);
    cell = new PdfPCell(new Phrase(c.getDepot()));
    cell.setBorderWidthBottom(1f);
    table.addCell(cell);

    cell = new PdfPCell(new Phrase("Statut", new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD)));
    cell.setBackgroundColor(new BaseColor(179, 204, 255));
    cell.setBorderWidthBottom(1f);
    table.addCell(cell);
    cell = new PdfPCell(new Phrase(c.getStatut()));
    cell.setBorderWidthBottom(1f);
    table.addCell(cell);

    cell = new PdfPCell(new Phrase("Émetteur", new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD)));
    cell.setBackgroundColor(new BaseColor(179, 204, 255));
    cell.setBorderWidthBottom(1f);
    table.addCell(cell);
    cell = new PdfPCell(new Phrase(c.getEmetteur()));
    cell.setBorderWidthBottom(1f);
    table.addCell(cell);

    cell = new PdfPCell(new Phrase("Type", new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD)));
    cell.setBackgroundColor(new BaseColor(179, 204, 255));
    cell.setBorderWidthBottom(1f);
    table.addCell(cell);
    cell = new PdfPCell(new Phrase(c.getType()));
    cell.setBorderWidthBottom(1f);
    table.addCell(cell);

    document.add(table);

    // Ajouter une signature en bas de page
    Paragraph signature = new Paragraph("X-CHANGE", new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD ,new BaseColor(255, 165, 0)));
    signature.setAlignment(Element.ALIGN_CENTER);
    signature.setSpacingBefore(20f);
    document.add(signature);

    // Fermer le document
    document.close();

    // Demander à l'utilisateur s'il veut ouvrir le fichier
    int valid = JOptionPane.showOptionDialog(
        null, 
        new Object[]{
            "Voulez vous directement ouvrir le fichier ?",
                
            "Cliquez OUI pour ouvrir ou NON pour annuler",
        },
        "ouverture du fichier "+nom_fichier, 
        JOptionPane.YES_NO_OPTION,
        JOptionPane.WARNING_MESSAGE, 
        null, null, "OK"
    );
    if (valid == JOptionPane.OK_OPTION) {
        File ouvrir = new File(nom_fichier); 
        Desktop desk = Desktop.getDesktop();
        desk.open(ouvrir);
    } 
}
 public class MyHeaderFooter extends PdfPageEventHelper {
    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        // Ajouter un pied de page
        Phrase footer = new Phrase("Page " + writer.getPageNumber());
        PdfContentByte cb = writer.getDirectContent();
        ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, footer, (document.right() - document.left()) / 2 + document.leftMargin(), document.bottom() - 10, 0);

        // Ajouter un en-tête
        Phrase header = new Phrase("Bon de commande", new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD, new BaseColor(63, 174, 172)));
        ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, header, (document.right() - document.left()) / 2 + document.leftMargin(), document.top() + 10, 0);
    }
}*/
}

