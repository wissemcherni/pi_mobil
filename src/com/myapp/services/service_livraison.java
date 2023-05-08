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
import com.myapp.entities.Livraison;
import com.myapp.utils.statics;
import java.io.IOException;
//import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author wissem
 */
public class service_livraison {
      public ArrayList<Livraison> livraisons;

    public static service_livraison instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private service_livraison() {
        req = new ConnectionRequest();
    }

    public static service_livraison getInstance() {
        if (instance == null) {
            instance = new service_livraison();
        }
        return instance;
    }

    public boolean addLivraison(Livraison l) {

      
     int num_commande = l.getNum_commande() ;
    String nom_livreur = l.getNom_livreur();
     //LocalDate DATE = l.getDATE(); 
        
        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
        String url = statics.BASE_URL+"/livraison/new" + num_commande + "/" + nom_livreur /*+ "/" + DATE */;

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

    public ArrayList<Livraison> parseLivraiosn(String jsonText) {
    try {
        livraisons = new ArrayList<>();
        JSONParser j = new JSONParser();
        Map<String, Object> livraisonsListJson
                = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

        List<Map<String, Object>> list = (List<Map<String, Object>>) livraisonsListJson.get("root");
        for (Map<String, Object> obj : list) {
                Livraison l = new Livraison();
                l.setId_livraison((int)Float.parseFloat(obj.get("id_livraison").toString()));
                l.setNum_commande((int)Float.parseFloat(obj.get("num_commande").toString()));
                l.setNom_livreur(obj.get("nom_livreur").toString());
               /* LocalDate date = LocalDate.parse(obj.get("date").toString());
                l.setDATE(date);*/
            livraisons.add(l);
        }

    } catch (IOException ex) {
        System.out.println(ex.getMessage());
    }
    return livraisons;
}
  

    public ArrayList<Livraison> getAllLivraisons() {
    String url = statics.BASE_URL+"/livraison/";
    req.setUrl(url);
    req.setPost(false);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            livraisons = parseLivraiosn(new String(req.getResponseData()));
            req.removeResponseListener(this);
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return livraisons;


    
}
    
    public boolean deleteLivraison(Livraison l) {

    int id_livraison = l.getId_livraison();
    String url = statics.BASE_URL+"/livraison/new/{idCommande}" + id_livraison;

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

public boolean updateLivraison(Livraison l) {

    int id_livraison = l.getId_livraison();
    int num_commande = l.getNum_commande() ;
    String nom_livreur = l.getNom_livreur();
    // LocalDate DATE = l.getDATE(); 
        
    String url = statics.BASE_URL+"/livraison/new" + num_commande + "/" + nom_livreur /*+ "/" + DATE */;

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
    
}
