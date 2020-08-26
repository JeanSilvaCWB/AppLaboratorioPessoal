package com.example.applaboratoriopessoal.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.applaboratoriopessoal.Listener.BluetoothListener;
import com.example.applaboratoriopessoal.R;

public class VoltimetroFragment extends Fragment implements BluetoothListener {
    BluetoothConnectionActivity bluetoothConnectionActivity;
    FragmentManager fragmentManager;
    private View rootView;
    TextView textViewMostraTensaoFragment;
    Button btnVoltar;

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
        btnVoltar = (Button)rootView.findViewById(R.id.btnVoltar);
        fragmentManager = getFragmentManager();

        btnVoltar.setOnClickListener(onClick);
        return rootView;
    }

    private final View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btnVoltar:
                    fragmentManager.popBackStack();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void setTextOnTextView(String textoInserido) {
        Log.d("VoltimetroFragment", textoInserido);
        final String textoInseridoVoltimetro = textoInserido;
        if (getActivity() != null){
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    textViewMostraTensaoFragment.setText(textoInseridoVoltimetro);
                }
            });
        }

    }
}