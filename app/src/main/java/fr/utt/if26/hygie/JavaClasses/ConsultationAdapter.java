package fr.utt.if26.hygie.JavaClasses;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import fr.utt.if26.hygie.R;

/**
 * Created by Francois on 20/12/2017.
 */


public class ConsultationAdapter extends ArrayAdapter<Consultation> {

    private Context context;

    public ConsultationAdapter(Context context, ArrayList<Consultation> objects) {
        super(context, 0, objects);
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent){

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_consultation, parent, false);
        }

        ModuleViewHolder viewHolder = (ModuleViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new ModuleViewHolder();
            viewHolder.patient = (TextView)convertView.findViewById(R.id.patient);
            viewHolder.objet = (TextView)convertView.findViewById(R.id.objet);
            viewHolder.horaire = (TextView)convertView.findViewById(R.id.horaireConsult);
            viewHolder.composeNumber = (ImageButton)convertView.findViewById(R.id.imageButton3);
            convertView.setTag(viewHolder);
        }

        Consultation consu = getItem(position);

        viewHolder.patient.setText(consu.getPatient().getNom());
        viewHolder.objet.setText(consu.getObjet());
        viewHolder.horaire.setText(consu.getHoraire().toString());
        viewHolder.composeNumber.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.i("Ligne : ","CALL");
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:0682442189"));
                context.startActivity(callIntent);
            }
        });

        return convertView;
    }

    private class ModuleViewHolder{
        public TextView patient;
        public TextView objet;
        public TextView horaire;
        public ImageButton composeNumber;
    }
}
