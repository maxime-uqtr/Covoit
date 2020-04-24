package com.exemple.Covoit.vue.ui.propositions;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.exemple.Covoit.R;

public class PropositionsFragment extends Fragment {

    private TrajetsViewModel trajetsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        trajetsViewModel =
                ViewModelProviders.of(this).get(TrajetsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_propositions, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        trajetsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
