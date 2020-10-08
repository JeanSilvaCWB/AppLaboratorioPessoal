package com.example.applaboratoriopessoal.View;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.applaboratoriopessoal.Listener.BluetoothListener;
import com.example.applaboratoriopessoal.R;

import java.nio.charset.Charset;
import java.text.DecimalFormat;

public class ConfiguraFonteAlimentacaoFragment extends Fragment implements BluetoothListener {
    BluetoothConnectionActivity bluetoothConnectionActivity;
    FragmentManager fragmentManager;
    private View rootView;
    private double valorTensaoPositiva;
    private double valorTensaoNegativa;
    private int stepTensaoPositiva;
    private int stepTensaoNegativa;
    boolean fechaThread = false;
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
        String chaveGeradorFuncoes = "FONTE";
        byte[] bytes = chaveGeradorFuncoes.getBytes(Charset.defaultCharset());
        bluetoothConnectionActivity.write(bytes);
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
                    if(stepTensaoPositiva > 0){
                        stepTensaoPositiva--;
                        valorTensaoPositiva = valorTensaoPositiva - 0.2375;
                        textViewValorTensaoFontePositivo.setText(String.format("%.2f", valorTensaoPositiva));
                    } else {}
                    break;
                case R.id.btnDecrementoNegativo:
                    if(stepTensaoNegativa > 0){
                        stepTensaoNegativa--;
                        valorTensaoNegativa = valorTensaoNegativa + 0.2375;
                        textViewValorTensaoFonteNegativo.setText(String.format("%.2f", valorTensaoNegativa));
                    } else {}
                    break;
                case R.id.btnIncrementoPositivo:
                    if(stepTensaoPositiva < 20){
                        stepTensaoPositiva++;
                        valorTensaoPositiva = valorTensaoPositiva + 0.2375;
                        textViewValorTensaoFontePositivo.setText(String.format("%.2f", valorTensaoPositiva));
                    } else {}
                    break;
                case R.id.btnIncrementoNegativo:
                    if(stepTensaoNegativa < 20){
                        stepTensaoNegativa++;
                        valorTensaoNegativa = valorTensaoNegativa - 0.2375;
                        textViewValorTensaoFonteNegativo.setText(String.format("%.2f", valorTensaoNegativa));
                    } else {}
                    break;
                case R.id.btnSalvar:
                    String valorFonte = "FONTE*" + String.valueOf(stepTensaoPositiva) + "*" + String.valueOf(stepTensaoNegativa);
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
    public void setTextOnTextView(String textoRecebido) {
        Log.d("FonteFragment", textoRecebido);
        String[] separaValor1 = textoRecebido.split("\\*");
        stepTensaoPositiva = Integer.valueOf(separaValor1[0]);
        stepTensaoNegativa = Integer.valueOf(separaValor1[1]);
        valorTensaoPositiva = (stepTensaoPositiva * 0.2375) + 1.25;
        valorTensaoNegativa = 0 - (stepTensaoNegativa * 0.2375) - 1.25;

        if (getActivity() != null || fechaThread == true){
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    textViewValorTensaoFontePositivo.setText(String.format("%.2f",valorTensaoPositiva));
                    textViewValorTensaoFonteNegativo.setText(String.format("%.2f",valorTensaoNegativa));
                    fechaThread = true;
                    onStop();
                }
            });
        }
    }
}