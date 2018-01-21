package fr.utt.if26.hygie.JavaClasses;

import java.io.Serializable;

/**
 * Created by Francois on 20/12/2017.
 */

public class Medecin implements Serializable{

    private String nom;
    private String prenom;
    private String motDePasse;
    private String identifiant;
    private int numRPPS;
    private String email;


    public Medecin()
    {
        this.nom = "Inconnu";
        this.prenom = "Inconnu";
        this.motDePasse = "";
        this.identifiant = "Inconnu";
        this.numRPPS = 0;
        this.email = "Inconnu";
    }

    public Medecin(String id, String mdp, String firstName, String secondName, int rpps, String email)
    {
        this.identifiant = id;
        this.motDePasse = mdp;
        this.nom = firstName;
        this.prenom = secondName;
        this.numRPPS = rpps;
        this.email = email;
    }

    public String getNom(){
        return this.nom;
    }

    public String getPrenom(){
        return this.prenom;
    }

    public int getRPPS(){return this.numRPPS; }

    public String getSRPPS(){
        return  Integer.toString(this.numRPPS);
    }

    public String getMotDePasse(){ return this.motDePasse;}

    public String getIdentifiant(){ return this.identifiant;}

    public int getNumRPPS() {
        return numRPPS;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public void setNumRPPS(int numRPPS) {
        this.numRPPS = numRPPS;
    }
}
