package com.example.applaboratoriopessoal.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.applaboratoriopessoal.R;

public class ConfiguraFonteAlimentacaoFragment extends Fragment {

    public ConfiguraFonteAlimentacaoFragment() {
        // Required empty public constructor
    }

    public static ConfiguraFonteAlimentacaoFragment newInstance() {
        ConfiguraFonteAlimentacaoFragment fragment = new ConfiguraFonteAlimentacaoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_configura_fonte_alimentacao, container, false);
    }
}