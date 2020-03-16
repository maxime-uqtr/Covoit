package com.exemple.Covoit;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.villeDep.setText(mCovoiturage.get(position).getVilleDep());
        holder.villeArr.setText(mCovoiturage.get(position).getVilleArr());
        holder.date.setText(mCovoiturage.get(position).getDate().toString());
        holder.nbPassager.setText(String.valueOf(mCovoiturage.get(position).getNbPassager()));

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
        private TextView date, villeDep, villeArr, nbPassager;
        private View v;

        public ViewHolder(View v){
            super(v);
            this.v = v;
            this.date = v.findViewById(R.id.date);
            this.villeDep = v.findViewById(R.id.villeDep);
            this.villeArr = v.findViewById(R.id.villeArr);
            this.nbPassager = v.findViewById(R.id.nbPassager);
        }
    }
}
