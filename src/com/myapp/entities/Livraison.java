/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myapp.entities;

//import java.time.LocalDate;

/**
 *
 * @author wissem
 */
public class Livraison {
    private int id_livraison ;
    private int num_commande ;
    private String nom_livreur ;
    //private LocalDate DATE ;

    public Livraison() {
    }

    public Livraison(int id_livraison, int num_commande, String nom_livreur/*, LocalDate DATE*/) {
        this.id_livraison = id_livraison;
        this.num_commande = num_commande;
        this.nom_livreur = nom_livreur;
        //this.DATE = DATE;
    }

    public Livraison(int num_commande, String nom_livreur/*, LocalDate DATE*/) {
        this.num_commande = num_commande;
        this.nom_livreur = nom_livreur;
        //this.DATE = DATE;
    }

    public int getId_livraison() {
        return id_livraison;
    }

    public int getNum_commande() {
        return num_commande;
    }

    public String getNom_livreur() {
        return nom_livreur;
    }

    /*public LocalDate getDATE() {
        return DATE;
    }*/

    public void setId_livraison(int id_livraison) {
        this.id_livraison = id_livraison;
    }

    public void setNum_commande(int num_commande) {
        this.num_commande = num_commande;
    }

    public void setNom_livreur(String nom_livreur) {
        this.nom_livreur = nom_livreur;
    }

    /*public void setDATE(LocalDate DATE) {
        this.DATE = DATE;
    }
*/
    /*@Override
    public String toString() {
        return "Livraison{" + "id_livraison=" + id_livraison + ", num_commande=" + num_commande + ", nom_livreur=" + nom_livreur + ", DATE=" + DATE + '}';
    }*/

    @Override
    public String toString() {
        return "Livraison{" + "id_livraison=" + id_livraison + ", num_commande=" + num_commande + ", nom_livreur=" + nom_livreur + '}';
    }
    
    
    
    
    
}
