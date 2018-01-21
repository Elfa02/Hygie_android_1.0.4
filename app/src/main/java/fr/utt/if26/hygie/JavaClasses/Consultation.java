package fr.utt.if26.hygie.JavaClasses;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Francois on 20/12/2017.
 */

public class Consultation {
    private Medecin medecin;
    private Patient patient;
    private String horaire;
    private String objet;

    public Consultation(Medecin medecin, Patient patient, String horaire, String objet) {
        this.medecin = medecin;
        this.patient = patient;
        this.horaire = horaire;
        this.objet = objet;
    }

    public Consultation(Patient patient, String horaire, String objet) {
        this.patient = patient;
        this.horaire = horaire;
        this.objet = objet;
    }

    public Patient getPatient() {
        return patient;
    }

    public Medecin getMedecin() {
        return medecin;
    }

    public String getHoraire() {
        return horaire;
    }

    public String getObjet() {
        return objet;
    }


}
