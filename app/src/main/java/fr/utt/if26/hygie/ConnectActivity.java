package fr.utt.if26.hygie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ConnectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);
        init();
    }

    private void init(){
        Button closeButton = (Button) findViewById(R.id.button);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void finish() {
        Intent resultIntent = new Intent();
        TextView TVID = (TextView) findViewById(R.id.editText7);
        TextView TVPW = (TextView) findViewById(R.id.editText8);
        String id = TVID.getText().toString();
        String pw = TVPW.getText().toString();

        resultIntent.putExtra("returnid", id);
        resultIntent.putExtra("returnpw", pw);
        setResult(ConnectActivity.RESULT_OK, resultIntent);
        super.finish();
        this.overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
    }
}
