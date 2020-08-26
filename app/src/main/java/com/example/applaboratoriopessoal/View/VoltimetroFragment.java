package com.example.applaboratoriopessoal.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.applaboratoriopessoal.Listener.BluetoothListener;
import com.example.applaboratoriopessoal.R;

public class VoltimetroFragment extends Fragment implements BluetoothListener {
    BluetoothConnectionActivity bluetoothConnectionActivity;
    private View rootView;
    TextView textViewMostraTensaoFragment;

    public VoltimetroFragment() {
        // Required empty public constructor
    }

    public static VoltimetroFragment newInstance(BluetoothConnectionActivity bluetoothConnectionActivity) {
        VoltimetroFragment fragment = new VoltimetroFragment();
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
        rootView = inflater.inflate(R.layout.fragment_voltimetro, container, false);
        textViewMostraTensaoFragment = (TextView) rootView.findViewById(R.id.textViewMostraTensaoFragment);
        return rootView;
    }

    @Override
    public void setTextOnTextView(String textoInserido) {
        Log.d("VoltimetroFragment", textoInserido);
        final String texttoInseridoVoltimetro = textoInserido;
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textViewMostraTensaoFragment.setText(texttoInseridoVoltimetro);
            }
        });

    }
}