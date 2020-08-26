package com.example.applaboratoriopessoal.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.applaboratoriopessoal.Listener.BluetoothListener;
import com.example.applaboratoriopessoal.R;

import java.nio.charset.Charset;

public class ConfiguraFonteAlimentacaoFragment extends Fragment implements BluetoothListener, View.OnClickListener {
    BluetoothConnectionActivity bluetoothConnectionActivity;
    private View rootView;
    EditText editTextTeste;
    Button btnEnviarTeste;

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
        rootView =  inflater.inflate(R.layout.fragment_configura_fonte_alimentacao, container, false);
        editTextTeste = (EditText)rootView.findViewById(R.id.editTextTeste);
        btnEnviarTeste = (Button)rootView.findViewById(R.id.btnEnviarTeste);
        btnEnviarTeste.setOnClickListener(this);
        return rootView;
    }

    public void onClick(View v){
        byte[] bytes = editTextTeste.getText().toString().getBytes(Charset.defaultCharset());
        bluetoothConnectionActivity.write(bytes);
    }

    @Override
    public void setTextOnTextView(String textoInserido) {
    }
}