/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myapp.entities;

/**
 *
 * @author wissem
 */
public class Commande {
    private int id_commande ;
    private int num_panier ;
    private String emetteur ;
    private int depot ;
     private String statut ;
     private String type ;

    public Commande() {
    }

    public Commande(int id_commande, int num_panier, String emetteur, int depot, String statut, String type) {
        this.id_commande = id_commande;
        this.num_panier = num_panier;
        this.emetteur = emetteur;
        this.depot = depot;
        this.statut = statut;
        this.type = type;
    }

    public Commande(int num_panier, String emetteur, int depot, String statut, String type) {
        this.num_panier = num_panier;
        this.emetteur = emetteur;
        this.depot = depot;
        this.statut = statut;
        this.type = type;
    }

    public int getId_commande() {
        return id_commande;
    }

    public int getNum_panier() {
        return num_panier;
    }

    public String getEmetteur() {
        return emetteur;
    }

    public int getDepot() {
        return depot;
    }

    public String getStatut() {
        return statut;
    }

    public String getType() {
        return type;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public void setNum_panier(int num_panier) {
        this.num_panier = num_panier;
    }

    public void setEmetteur(String emetteur) {
        this.emetteur = emetteur;
    }

    public void setDepot(int depot) {
        this.depot = depot;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Commande{" + "id_commande=" + id_commande + ", num_panier=" + num_panier + ", emetteur=" + emetteur + ", depot=" + depot + ", statut=" + statut + ", type=" + type + '}';
    }
     
     
    
}
