package com.example.applaboratoriopessoal.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.applaboratoriopessoal.Listener.BluetoothListener;
import com.example.applaboratoriopessoal.R;

import java.nio.charset.Charset;

public class ConfiguraFonteAlimentacaoFragment extends Fragment implements BluetoothListener {
    BluetoothConnectionActivity bluetoothConnectionActivity;
    FragmentManager fragmentManager;
    private View rootView;
    private double valorTensao;
    Button btnDecremento;
    Button btnIncremento;
    Button btnSalvar;
    TextView textViewValorTensaoFonte;

    public ConfiguraFonteAlimentacaoFragment() {
        // Required empty public constructor
    }

    public static ConfiguraFonteAlimentacaoFragment newInstance(BluetoothConnectionActivity bluetoothConnectionActivity) {
        ConfiguraFonteAlimentacaoFragment fragment = new ConfiguraFonteAlimentacaoFragment();
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
        rootView = inflater.inflate(R.layout.fragment_configura_fonte_alimentacao, container, false);
        btnDecremento = (Button) rootView.findViewById(R.id.btnDecremento);
        btnIncremento = (Button) rootView.findViewById(R.id.btnIncremento);
        btnSalvar = (Button) rootView.findViewById(R.id.btnSalvar);
        textViewValorTensaoFonte = (TextView) rootView.findViewById(R.id.textViewValorTensaoFonte);
        fragmentManager = getFragmentManager();

        btnDecremento.setOnClickListener(onClick);
        btnIncremento.setOnClickListener(onClick);
        btnSalvar.setOnClickListener(onClick);
        return rootView;
    }

    private final View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btnDecremento:
                    if(valorTensao > 0){
                        valorTensao = valorTensao - 0.14;
                        textViewValorTensaoFonte.setText(String.format("%.2f", valorTensao));
                    } else {}
                    break;
                case R.id.btnIncremento:
                    if(valorTensao <= 15){
                        valorTensao = valorTensao + 0.14;
                        textViewValorTensaoFonte.setText(String.format("%.2f", valorTensao));
                    } else {}
                    break;
                case R.id.btnSalvar:
                    byte[] bytes = String.valueOf(valorTensao).getBytes(Charset.defaultCharset());
                    bluetoothConnectionActivity.write(bytes);
                    fragmentManager.popBackStack();
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