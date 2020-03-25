package com.exemple.Covoit;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.exemple.Covoit.controleur.OnListClickListener;
import com.exemple.Covoit.models.Covoiturage;

import java.util.List;

public class ListAdapteur extends RecyclerView.Adapter<ListAdapteur.ViewHolder>{
    private List<Covoiturage> mCovoiturage;

    private OnListClickListener listener;

    public ListAdapteur(List<Covoiturage> mCovoiturage, OnListClickListener listener) {
        this.mCovoiturage = mCovoiturage;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        Covoiturage c = mCovoiturage.get(position);
        holder.itineraire.setText(c.getVilleDep() + " - " + c.getVilleArr());
        holder.date.setText(c.getDate().getDay() + "/" + c.getDate().getMonth() + "/" + c.getDate().getYear());
        holder.prix.setText("Prix : $" + String.valueOf(c.getPrix()));
        holder.nbPassager.setText("Place(s) : " + String.valueOf(c.getNbPassager()));

        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onListClick(mCovoiturage.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCovoiturage.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView date, itineraire, prix, nbPassager;
        private View v;

        public ViewHolder(View v){
            super(v);
            this.v = v;
            this.date = v.findViewById(R.id.item_date);
            this.itineraire = v.findViewById(R.id.item_itineraire);
            this.prix = v.findViewById(R.id.item_prix);
            this.nbPassager = v.findViewById(R.id.item_nbPassager);
        }
    }

    public void setCovoiturages(List<Covoiturage> c){
        mCovoiturage = c;
    }
}
