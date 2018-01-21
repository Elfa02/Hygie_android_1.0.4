package fr.utt.if26.hygie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class SubscribeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe);
        init();
    }

    private void init(){
        Button valider = (Button) findViewById(R.id.button2);
        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Button annuler = (Button) findViewById(R.id.button3);
        annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void finish(){
        Intent resultIntent = new Intent();

        EditText ETnom = (EditText) findViewById(R.id.editText);
        EditText ETprenom = (EditText) findViewById(R.id.editText2);
        EditText ETidentifiant = (EditText) findViewById(R.id.editText3);
        EditText ETPW = (EditText) findViewById(R.id.editText4);
        EditText ETnumRPPS = (EditText) findViewById(R.id.editText5);
        Spinner SPcategorie = (Spinner) findViewById(R.id.spinner);
        EditText ETemail = (EditText) findViewById(R.id.editText6);

        String nom = ETnom.getText().toString();
        String prenom = ETprenom.getText().toString();
        String id = ETidentifiant.getText().toString();
        int numRPPS = Integer.parseInt(ETnumRPPS.getText().toString());
        String pw = ETPW.getText().toString();
        String email = ETemail.getText().toString();

        resultIntent.putExtra("returnNom", nom);
        resultIntent.putExtra("returnPrenom", prenom);
        resultIntent.putExtra("returnID", id);
        resultIntent.putExtra("returnPW", pw);
        resultIntent.putExtra("returnEmail", email);
        resultIntent.putExtra("returnRPPS", numRPPS);
        setResult(2, resultIntent);
        super.finish();
        this.overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
    }
}
