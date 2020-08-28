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
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    private View rootView;
    Button btnConfigurarFonteAlimentacao;
    Button btnVoltimetro;
    Button btnOsciloscopio;

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
        btnConfigurarFonteAlimentacao = (Button) rootView.findViewById(R.id.btnConfigurarFonteAlimentacao);
        btnVoltimetro = (Button) rootView.findViewById(R.id.btnVoltimetro);
        btnOsciloscopio = (Button) rootView.findViewById(R.id.btnOsciloscopio);
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        btnConfigurarFonteAlimentacao.setOnClickListener(onClick);
        btnVoltimetro.setOnClickListener(onClick);
        btnOsciloscopio.setOnClickListener(onClick);
        return rootView;
    }

    private final View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btnConfigurarFonteAlimentacao:
                    fragmentTransaction.replace(R.id.fragment_container, ConfiguraFonteAlimentacaoFragment.newInstance(bluetoothConnectionActivity));
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                    break;
                case R.id.btnVoltimetro:
                    fragmentTransaction.replace(R.id.fragment_container, VoltimetroFragment.newInstance(bluetoothConnectionActivity));
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                    break;
                case R.id.btnOsciloscopio:
                    fragmentTransaction.replace(R.id.fragment_container, OsciloscopioFragment.newInstance(bluetoothConnectionActivity));
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void setTextOnTextView(String textoInserido) {
    }
}