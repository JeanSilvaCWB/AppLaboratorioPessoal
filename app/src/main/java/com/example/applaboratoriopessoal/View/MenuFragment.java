package com.example.applaboratoriopessoal.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.applaboratoriopessoal.Listener.BluetoothListener;
import com.example.applaboratoriopessoal.R;

import static android.widget.Toast.LENGTH_LONG;

public class MenuFragment extends Fragment implements BluetoothListener {
    BluetoothConnectionActivity bluetoothConnectionActivity;
    private View rootView;
    Button btnConfigurarFonteAlimentacao;
    Button btnVoltimetro;

    public MenuFragment() {
        // Required empty public constructor
    }

    public static MenuFragment newInstance(BluetoothConnectionActivity bluetoothConnectionActivity) {
        MenuFragment fragment = new MenuFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        fragment.bluetoothConnectionActivity = bluetoothConnectionActivity;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bluetoothConnectionActivity.setBluetoothListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_menu, container, false);
        btnConfigurarFonteAlimentacao = (Button)rootView.findViewById(R.id.btnConfigurarFonteAlimentacao);
        btnVoltimetro = (Button)rootView.findViewById(R.id.btnVoltimetro);
        return rootView;
    }

    @Override
    public void setTextOnTextView(String textoInserido) {
    }
}