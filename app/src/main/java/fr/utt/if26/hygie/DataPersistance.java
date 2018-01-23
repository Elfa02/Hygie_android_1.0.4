package fr.utt.if26.hygie;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import fr.utt.if26.hygie.JavaClasses.Consultation;
import fr.utt.if26.hygie.JavaClasses.Medecin;
import fr.utt.if26.hygie.JavaClasses.Patient;

/**
 * Created by Francois on 20/12/2017.
 */

public class DataPersistance extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Medecins.db";
    public static final String TABLE_MEDECINS = "medecin";
        public static final String ATTRIBUT_NOM = "nom";
        public static final String ATTRIBUT_PRENOM = "prenom";
        public static final String ATTRIBUT_RPPS = "rpps";
        public static final String ATTRIBUT_IDENTIFIANT = "identifiant";
        public static final String ATTRIBUT_PW = "pw";
        public static final String ATTRIBUT_EMAIL = "mail";

    public static final String TABLE_CONSULTATIONS = "consultation";
        public static final String ATTRIBUT_CONSULT_IDENTIFIANT = "IdConsult";
        public static final String ATTRIBUT_MEDECIN = "medecinTraitant";
        public static final String ATTRIBUT_PATIENT = "patient";
        public static final String ATTRIBUT_HORAIRE = "horaire";
        public static final String ATTRIBUT_OBJET = "objet";

    private static int count=0;
    SQLiteDatabase db = null;


    public DataPersistance(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        context.deleteDatabase(DATABASE_NAME);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL("DROP DATABASE EXISTS "+DATABASE_NAME);

        final String table_consultation_create =
                "CREATE TABLE " + TABLE_CONSULTATIONS + "(" +
                        ATTRIBUT_CONSULT_IDENTIFIANT + " TEXT primary key," +
                        ATTRIBUT_MEDECIN + " TEXT, " +
                        ATTRIBUT_PATIENT + " TEXT," +
                        ATTRIBUT_HORAIRE + " TEXT, " +
                        ATTRIBUT_OBJET + " TEXT" +
                        ")";
        Log.i("Creation Consultations",table_consultation_create);
        db.execSQL(table_consultation_create);

        final String table_medecin_create =
                "CREATE TABLE " + TABLE_MEDECINS + "(" +
                        ATTRIBUT_IDENTIFIANT + " TEXT primary key," +
                        ATTRIBUT_NOM + " TEXT, " +
                        ATTRIBUT_PRENOM + " TEXT," +
                        ATTRIBUT_RPPS + " INTEGER, " +
                        ATTRIBUT_PW + " TEXT, " +
                        ATTRIBUT_EMAIL + " TEXT" +
                        ")";
        Log.i("Creation table Medecins",table_medecin_create);
        db.execSQL(table_medecin_create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("DROP TABLE","Medecins");
        onCreate(db);
    }


    public void addMedecin(Medecin m){
        Log.i("Insertion de  ", m.getNom());

        Log.i("Nom : ", m.getNom());
        Log.i("PreNom : ", m.getPrenom());
        Log.i("Identifiant : ", m.getIdentifiant());
        Log.i("PW : ", m.getMotDePasse());

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ATTRIBUT_IDENTIFIANT, m.getIdentifiant());
        contentValues.put(ATTRIBUT_NOM, m.getNom());
        contentValues.put(ATTRIBUT_PRENOM, m.getPrenom());
        contentValues.put(ATTRIBUT_RPPS, m.getRPPS());
        contentValues.put(ATTRIBUT_PW, m.getMotDePasse());
        contentValues.put(ATTRIBUT_EMAIL, m.getEmail());
        long result = db.insert(TABLE_MEDECINS, null, contentValues);
        db.close();
    }

    public Medecin getMedecinByID(String idMedecin)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Log.i("RECHERCHE DE  ",""+idMedecin);
        Cursor cur = db.rawQuery("SELECT * FROM " + TABLE_MEDECINS +
                " WHERE " + ATTRIBUT_IDENTIFIANT + "=?", new String[] { idMedecin + "" } );
        if (cur != null)
            cur.moveToFirst();
        Medecin resMed = new Medecin(cur.getString(0),cur.getString(4),cur.getString(1),cur.getString(2),Integer.parseInt(cur.getString(3)),cur.getString(5));
        return resMed;
    }

    public void addConsultation(Medecin m, Patient patient, SimpleDateFormat hor, String obj){
        Log.i("Insertion de  ",""+obj);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ATTRIBUT_CONSULT_IDENTIFIANT, count++);
        contentValues.put(ATTRIBUT_MEDECIN, m.getIdentifiant());
        contentValues.put(ATTRIBUT_PATIENT, patient.getPrenom()+" "+patient.getNom());
        contentValues.put(ATTRIBUT_HORAIRE, hor.format(new Date()));
        contentValues.put(ATTRIBUT_OBJET, obj);
        long result = db.insert(TABLE_CONSULTATIONS, null, contentValues);
        db.close();
    }

    public int verifLogs(String id, String pw){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] selectionArgs = new String[]{id, pw};
        try{
            int i = 0;
            Cursor c = null;
            c = db.rawQuery("SELECT * from "+TABLE_MEDECINS+" WHERE "+ATTRIBUT_IDENTIFIANT+"=? and "+ATTRIBUT_PW+"=?", selectionArgs);
            c.moveToFirst();
            i = c.getCount();
            c.close();
            return i;
        }
        catch(Exception e){e.printStackTrace();}
        return 0;
    }

    public ArrayList<Consultation> getConsultationsMedecin(String idMedecin){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Consultation> res = new ArrayList<>();
        String query = "SELECT * FROM "+TABLE_CONSULTATIONS+" WHERE "+ATTRIBUT_MEDECIN+" ='"+idMedecin+"';";
        Log.i("Requete ",query);

        Cursor c = db.rawQuery(query, null);
        while(c.moveToNext()) {
            Consultation m = new Consultation(new Patient(c.getString(2)),c.getString(3),c.getString(4));
            res.add(m);
        }
        c.close();

        return res;
    }

    public void deleteMedecinByID(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MEDECINS, ATTRIBUT_IDENTIFIANT + "='" + id + "'", null);
    }

    public int getMedecinsCount(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor mCount= db.rawQuery("select count(*) from "+ TABLE_MEDECINS, null);
        mCount.moveToFirst();
        int count= mCount.getInt(0);
        mCount.close();
        return count;
    }

    public int getConsultationsCount(){
        Cursor mCount= db.rawQuery("select count(*) from "+ TABLE_CONSULTATIONS, null);
        mCount.moveToFirst();
        int count= mCount.getInt(0);
        mCount.close();
        return count;
    }

    public void printAllMedecins(){

    }


    //Hash
/*

   Log.i ("Persistance onCreate", "M�decin");

    M�decin m = new M�decin("karp", "32ac991d88d626c75cf319149ca6f7865076ae64")

    addM�decin(m,db);

}



    private void addUsers(Users u, SQLiteDatabase db) {

        db.beginTransaction();

        try {



            ContentValues values = new ContentValues();



            values.put(Medecin_Id,  u.getId());

            values.put(Medecin_mdp, u.getPassword());

            db.insertOrThrow(TABLE_Medecin, null, values);

            db.setTransactionSuccessful();

        } catch (Exception e) {

            Log.d(TAG, "Error while trying to add post to database");

        } finally {

            db.endTransaction();

        }

    }

    */
/*
    public Module getModule(String sigle){
        Cursor cur = db.rawQuery("SELECT * FROM " + TABLE_MODULES +
                " WHERE " + ATTRIBUT_SIGLE + "=?", new String[] { sigle + "" } );
        if (cur != null)
            cur.moveToFirst();
        Module resMod = new Module(cur.getString(0),cur.getString(1),Integer.parseInt(cur.getString(2)),Resultat.A);
        return resMod;
    }

    public ArrayList<Module> getAllModules(){
        ArrayList<Module> res = new ArrayList<Module>();

        String query = "SELECT * FROM "+TABLE_MODULES;
        Cursor c = db.rawQuery(query, null);
        while(c.moveToNext()) {
            Module m = new Module(c.getString(0),c.getString(1),Integer.parseInt(c.getString(2)),Resultat.A);
            res.add(m);
        }
        c.close();
        return res;
    }

    public void deleteModule(Module module){
        String sigle = module.getSigle();
        db.delete(TABLE_MODULES, ATTRIBUT_SIGLE + "=" + sigle, null);
    }

    public int getModuleCount(){
        Cursor mCount= db.rawQuery("select count(*) from "+ TABLE_MODULES, null);
        mCount.moveToFirst();
        int count= mCount.getInt(0);
        mCount.close();
        return count;
    }
*/
}
