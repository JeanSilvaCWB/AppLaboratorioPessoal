package com.example.applaboratoriopessoal.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.applaboratoriopessoal.Listener.BluetoothListener;
import com.example.applaboratoriopessoal.R;

import java.nio.charset.Charset;

public class OsciloscopioFragment extends Fragment implements BluetoothListener {
    BluetoothConnectionActivity bluetoothConnectionActivity;
    FragmentManager fragmentManager;
    private View rootView;
    Button btnSalvar;
    TextView textViewTeste;

    public OsciloscopioFragment() {
        // Required empty public constructor
    }

    public static OsciloscopioFragment newInstance(BluetoothConnectionActivity bluetoothConnectionActivity) {
        OsciloscopioFragment fragment = new OsciloscopioFragment();
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
        rootView =  inflater.inflate(R.layout.fragment_osciloscopio, container, false);
        btnSalvar = (Button)rootView.findViewById(R.id.btnSalvar);
        textViewTeste = (TextView)rootView.findViewById(R.id.textViewTeste);
        fragmentManager = getFragmentManager();

        btnSalvar.setOnClickListener(onClick);
        return rootView;
    }

    private final View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btnSalvar:
                    fragmentManager.popBackStack();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void setTextOnTextView(String textoInserido) {
        Log.d("OsciloscopioFragment", textoInserido);
        final String textoInseridoOsciloscopio = textoInserido;
        if (getActivity() != null){
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    textViewTeste.setText(textoInseridoOsciloscopio);
                }
            });
        }
    }
}