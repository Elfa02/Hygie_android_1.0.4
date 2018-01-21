package fr.utt.if26.hygie.JavaClasses;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import fr.utt.if26.hygie.ConnectActivity;
import fr.utt.if26.hygie.R;

public class ModificationActivity extends AppCompatActivity {

    private Medecin med;
    private EditText nom, prenom, mail, rpps, identif;
    private Button annulerButton, validerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modification);
        this.med = (Medecin)getIntent().getSerializableExtra("Medecin");
        init();
    }

    private void init()
    {
        validerButton = (Button) findViewById(R.id.button5);
        validerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        annulerButton = (Button) findViewById(R.id.button6);
        annulerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        nom = (EditText) findViewById(R.id.editText9);
        prenom = (EditText) findViewById(R.id.editText10);
        mail = (EditText) findViewById(R.id.editText11);
        rpps = (EditText) findViewById(R.id.editText12);
        identif  = (EditText) findViewById(R.id.editText13);

        nom.setText(med.getNom());
        prenom.setText(med.getPrenom());
        mail.setText(med.getEmail());
        rpps.setText(med.getSRPPS());
        identif.setText(med.getIdentifiant());
    }

    @Override
    public void finish() {
        Intent resultIntent = new Intent();
        TextView TVnom = (TextView) findViewById(R.id.editText9);
        TextView TVprenom = (TextView) findViewById(R.id.editText10);
        TextView TVid = (TextView) findViewById(R.id.editText13);
        TextView TVrpps = (TextView) findViewById(R.id.editText12);
        TextView TVmail = (TextView) findViewById(R.id.editText11);
        String id = TVid.getText().toString();
        String nom = TVnom.getText().toString();
        String prenom = TVprenom.getText().toString();
        String rpps = TVrpps.getText().toString();
        String mail = TVmail.getText().toString();
        resultIntent.putExtra("returnId", id);
        resultIntent.putExtra("returnNom", nom);
        resultIntent.putExtra("returnPrenom", prenom);
        resultIntent.putExtra("returnRpps", rpps);
        resultIntent.putExtra("returnMail", mail);
        setResult(3, resultIntent);
        super.finish();
        this.overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
    }
}
