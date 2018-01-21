package fr.utt.if26.hygie.JavaClasses;

/**
 * Created by Francois on 20/12/2017.
 */

public class Patient {

    private String nom;
    private String prenom;
    private int age;
    private String email;
    private String telephone;

    public Patient(String nom, String prenom, int age, String email, String tel) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.email = email;
        this.telephone = tel;
    }

    public Patient(String nom) {
        this.nom = nom;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }
}
