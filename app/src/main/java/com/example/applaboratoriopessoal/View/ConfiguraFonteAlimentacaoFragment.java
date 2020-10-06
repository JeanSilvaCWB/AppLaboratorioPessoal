package com.example.applaboratoriopessoal.View;

import android.content.Context;
import android.graphics.Typeface;
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
    private double valorTensaoPositiva;
    private double valorTensaoNegativa;
    Button btnDecrementoPositivo;
    Button btnDecrementoNegativo;
    Button btnIncrementoPositivo;
    Button btnIncrementoNegativo;
    Button btnSalvar;
    TextView textViewValorTensaoFontePositivo;
    TextView textViewValorTensaoFonteNegativo;

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
        btnDecrementoPositivo = (Button) rootView.findViewById(R.id.btnDecrementoPositivo);
        btnDecrementoNegativo = (Button) rootView.findViewById(R.id.btnDecrementoNegativo);
        btnIncrementoPositivo = (Button) rootView.findViewById(R.id.btnIncrementoPositivo);
        btnIncrementoNegativo = (Button) rootView.findViewById(R.id.btnIncrementoNegativo);
        btnSalvar = (Button) rootView.findViewById(R.id.btnSalvar);
        textViewValorTensaoFontePositivo = (TextView) rootView.findViewById(R.id.textViewValorTensaoFontePositivo);
        textViewValorTensaoFonteNegativo = (TextView) rootView.findViewById(R.id.textViewValorTensaoFonteNegativo);
        fragmentManager = getFragmentManager();

        btnDecrementoPositivo.setOnClickListener(onClick);
        btnDecrementoNegativo.setOnClickListener(onClick);
        btnIncrementoPositivo.setOnClickListener(onClick);
        btnIncrementoNegativo.setOnClickListener(onClick);
        btnSalvar.setOnClickListener(onClick);
        return rootView;
    }

    private final View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btnDecrementoPositivo:
                    if(valorTensaoPositiva > 0){
                        valorTensaoPositiva = valorTensaoPositiva - 0.14;
                        textViewValorTensaoFontePositivo.setText(String.format("%.2f", valorTensaoPositiva));
                    } else {}
                    break;
                case R.id.btnDecrementoNegativo:
                    if(valorTensaoNegativa >= -15){
                        valorTensaoNegativa = valorTensaoNegativa - 0.14;
                        textViewValorTensaoFonteNegativo.setText(String.format("%.2f", valorTensaoNegativa));
                    } else {}
                    break;
                case R.id.btnIncrementoPositivo:
                    if(valorTensaoPositiva <= 15){
                        valorTensaoPositiva = valorTensaoPositiva + 0.14;
                        textViewValorTensaoFontePositivo.setText(String.format("%.2f", valorTensaoPositiva));
                    } else {}
                    break;
                case R.id.btnIncrementoNegativo:
                    if(valorTensaoNegativa <= 0){
                        valorTensaoNegativa = valorTensaoNegativa + 0.14;
                        textViewValorTensaoFonteNegativo.setText(String.format("%.2f", valorTensaoNegativa));
                    } else {}
                    break;
                case R.id.btnSalvar:
                    String valorFonte = String.valueOf(valorTensaoPositiva) + "." + String.valueOf(valorTensaoNegativa);
                    byte[] bytes = valorFonte.getBytes(Charset.defaultCharset());
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