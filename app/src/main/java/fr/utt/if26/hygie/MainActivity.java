package fr.utt.if26.hygie;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import java.io.Serializable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import fr.utt.if26.hygie.JavaClasses.Consultation;
import fr.utt.if26.hygie.JavaClasses.ConsultationAdapter;
import fr.utt.if26.hygie.JavaClasses.Medecin;
import fr.utt.if26.hygie.JavaClasses.ModificationActivity;
import fr.utt.if26.hygie.JavaClasses.Patient;

public class MainActivity extends AppCompatActivity {

    private Button btnConnect = null;
    private Button btnSubscribe = null;
    private DataPersistance db = null;
    private boolean logged = false;
    private Menu m = null;
    private Intent inte;
    private String IDmedecin;
    private Medecin medecin;
    private ListView lv;
    private static final int REQUEST_CODE_LOGIN = 1;
    private static final int REQUEST_CODE_REGISTER = 2;
    private static final int REQUEST_CODE_MODIF = 3;

    private static final int MENU_LOGIN = Menu.FIRST;
    private static final int MENU_ABOUT = Menu.FIRST + 1;
    private static final int MENU_QUIT = Menu.FIRST + 2;
    private static final int MENU_LOGOUT = Menu.FIRST + 3;

    public void populate()
    {
        Medecin m = new Medecin("Karp", "1234", "Jean-Claude", "Karp", 012345, "jcKarp@wanadoo.fr");
        Medecin m2 = new Medecin("HouseMD", "1478", "Gregory", "House", 3697, "gregHouse@wanadoo.us");
        Medecin m3 = new Medecin("Freud", "6935", "Sigmund", "Freud", 16512, "sFreud@wanadoo.de");
        Patient p1 = new Patient("Imaginaire","Malade",56,"hypochondriaque@gmal.fr", "0682442189");
        Patient p2 = new Patient("Babar","Leroi",34,"babar@gmal.fr", "0682442189");
        Patient p3 = new Patient("Bonaparte","Napoléon",56,"jepossedeleurope@gmal.eu", "0682442189");
        Patient p4 = new Patient("Quasisanstete","Nick",56,"etetee@gmal.fr", "0682442189");
        Patient p5 = new Patient("House","Gregory",56,"genie@gmal.us", "0682442189");
        Patient p6 = new Patient("Legrand","Alexandre",56,"jepossedelasie@gmal.mc", "0682442189");
        Patient p7 = new Patient("Tchaïkovski","Piotr Illitch",56,"melomane@gmal.fr", "0682442189");
        Patient p8 = new Patient("DeMedicis","Marie",56,"venise@gmal.it", "0682442189");
        Patient p9 = new Patient("Pascal","Blaise",56,"jemetlapression@gmal.fr", "0682442189");
        Patient p10 = new Patient("Givoarien","Fred",56,"aïe@gmal.fr", "0682442189");
        Patient p11 = new Patient("XVIII","Louis",56,"jepossedelafrance@gmal.fr", "0682442189");
        Patient p12 = new Patient("Trump","Donald",56,"ausecours@gmal.us", "0682442189");
        db.addMedecin(m); db.addMedecin(m2); db.addMedecin(m3);
        db.addConsultation(m,p1,new SimpleDateFormat(),"Malaise");db.addConsultation(m2,p1,new SimpleDateFormat(),"Malaise");db.addConsultation(m3,p1,new SimpleDateFormat(),"Malaise");
        db.addConsultation(m,p2,new SimpleDateFormat(),"Cécité");db.addConsultation(m2,p2,new SimpleDateFormat(),"Cécité");db.addConsultation(m3,p2,new SimpleDateFormat(),"Cécité");
        db.addConsultation(m,p3,new SimpleDateFormat(),"Ulcère");db.addConsultation(m2,p3,new SimpleDateFormat(),"Ulcère");db.addConsultation(m3,p3,new SimpleDateFormat(),"Ulcère");
        db.addConsultation(m,p4,new SimpleDateFormat(),"Quasi-plus de tête");db.addConsultation(m2,p4,new SimpleDateFormat(),"Quasi-plus de tête");db.addConsultation(m3,p4,new SimpleDateFormat(),"Quasi-plus de tête");
        db.addConsultation(m,p5,new SimpleDateFormat(),"Misanthrope");db.addConsultation(m2,p5,new SimpleDateFormat(),"Misanthrope");
        db.addConsultation(m,p6,new SimpleDateFormat(),"Paludisme");db.addConsultation(m2,p6,new SimpleDateFormat(),"Paludisme");
        db.addConsultation(m,p7,new SimpleDateFormat(),"Choléra");db.addConsultation(m2,p7,new SimpleDateFormat(),"Choléra");
        db.addConsultation(m,p8,new SimpleDateFormat(),"Mal à la jambe");db.addConsultation(m2,p8,new SimpleDateFormat(),"Mal à la jambe");
        db.addConsultation(m,p9,new SimpleDateFormat(),"Maux de tête");
        db.addConsultation(m,p10,new SimpleDateFormat(),"Vision");
        db.addConsultation(m,p11,new SimpleDateFormat(),"Goutte");
        db.addConsultation(m2,p12,new SimpleDateFormat(),"Autisme");
        db.addConsultation(m2,p5,new SimpleDateFormat(),"Vision");
    }

    public void init()
    {
        btnConnect = (Button) findViewById(R.id.connectButton);
        btnSubscribe = (Button) findViewById(R.id.subscribeButton);

        btnConnect.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent inConnect = new Intent(MainActivity.this,ConnectActivity.class);
                MainActivity.this.startActivityForResult(inConnect, REQUEST_CODE_LOGIN);
            }
        });

        btnSubscribe.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent inSubscribe = new Intent(MainActivity.this,SubscribeActivity.class);
                MainActivity.this.startActivityForResult(inSubscribe, REQUEST_CODE_REGISTER);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        db = new DataPersistance(this);
        populate();
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        menu.clear();
        m = menu;
        Log.i("Menu : ","prepare appelé");

        menu.add(0, MENU_QUIT, Menu.NONE, "Quitter");
        menu.add(0, MENU_ABOUT, Menu.NONE, "A propos");
        if(!logged)
            menu.add(0, MENU_LOGIN, Menu.NONE, "Connecter");
        else
            menu.add(0, MENU_LOGOUT, Menu.NONE, "Se déconnecter");

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onActivityResult(int aRequestCode, int aResultCode, Intent data)
    {
        switch (aRequestCode) {
            case REQUEST_CODE_LOGIN:
                if(db.verifLogs(data.getStringExtra("returnid"),data.getStringExtra("returnpw"))>0)
                {
                    Log.i("Connection attempt : ","Connection acceptée");
                    logged = true;
                    IDmedecin = data.getStringExtra("returnid");
                    initLoggedView();
                }
                else
                    Log.i("Connection attempt : ","Connection refusée");
                break;
            case REQUEST_CODE_REGISTER:
            {
                Medecin nouvMed = new Medecin(data.getStringExtra("returnID"),data.getStringExtra("returnPW"),data.getStringExtra("returnNom"),data.getStringExtra("returnPrenom"),data.getIntExtra("returnRPPS",-1),data.getStringExtra("returnEmail"));
                nouvMed.setEmail(data.getStringExtra("returnEmail"));
                db.addMedecin(nouvMed);
            }
            break;
            case REQUEST_CODE_MODIF:
            {
                Medecin nouvMed = medecin;
                nouvMed.setIdentifiant(data.getStringExtra("returnId"));
                nouvMed.setNom(data.getStringExtra("returnNom"));
                nouvMed.setPrenom(data.getStringExtra("returnPrenom"));
                nouvMed.setNumRPPS(Integer.parseInt(data.getStringExtra("returnRpps")));
                nouvMed.setEmail(data.getStringExtra("returnMail"));
                db.deleteMedecinByID(IDmedecin);
                db.addMedecin(nouvMed);
                IDmedecin = nouvMed.getIdentifiant();
                initLoggedView();
            }
            break;
        }
        super.onActivityResult(aRequestCode, aResultCode, data);
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item)
    {
        switch(item.getItemId())
        {
            case MENU_QUIT: {
                finishAndRemoveTask ();
                return true;
            }
            case MENU_LOGIN: {
                Intent inConnect = new Intent(MainActivity.this,ConnectActivity.class);
                MainActivity.this.startActivityForResult(inConnect, REQUEST_CODE_LOGIN);
                return true;
            }
            case MENU_LOGOUT: {
                logged = false;
                setContentView(R.layout.activity_main);
                init();
                lv = null;
                IDmedecin = null;
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void initLoggedView()
    {
        setContentView(R.layout.activity_gestion);
        TextView welcome = (TextView) findViewById(R.id.welcomeText);
        welcome.setText("Bienvenue Docteur "+IDmedecin+". \nVos prochains rendez-vous sont : ");

        //Demander à la BD les informations sur le medecin
        medecin = db.getMedecinByID(IDmedecin);

        //Demander à la BD la liste des consultation à venir
        ArrayList<Consultation> consultations = db.getConsultationsMedecin(IDmedecin);
        lv = (ListView)findViewById(R.id.listViewPatients);
        ConsultationAdapter ca = new ConsultationAdapter(MainActivity.this,consultations);
        lv.setAdapter(ca);

        //Remplissage infos médecin
        TextView nom = (TextView) findViewById(R.id.txtVNOM);
        TextView prenom = (TextView) findViewById(R.id.txtPrenom);
        TextView RPPS = (TextView) findViewById(R.id.txtVRPPS);
        TextView iden = (TextView) findViewById(R.id.txtVID);
        TextView email = (TextView) findViewById(R.id.txtVMAIL);
        nom.setText(medecin.getNom());
        prenom.setText(medecin.getPrenom());
        RPPS.setText(medecin.getSRPPS());
        iden.setText(IDmedecin);
        email.setText(medecin.getEmail());

        Button modifButton = (Button) findViewById(R.id.button4);
        modifButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent inConnect = new Intent(MainActivity.this,ModificationActivity.class);
                inConnect.putExtra("Medecin", medecin);
                MainActivity.this.startActivityForResult(inConnect, REQUEST_CODE_MODIF);
            }
        });

    }

}
