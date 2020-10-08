package com.example.applaboratoriopessoal.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.applaboratoriopessoal.Listener.BluetoothListener;
import com.example.applaboratoriopessoal.R;

import java.nio.charset.Charset;

public class ConfiguraGeradorFuncoesFragment extends Fragment implements BluetoothListener {
    BluetoothConnectionActivity bluetoothConnectionActivity;
    FragmentManager fragmentManager;
    private View rootView;
    private double valorFrequencia;
    private double valorTensaoGerador;
    private int stepFrequencia;
    private int stepTensaoGerador;
    private String tipoOndaSelecionada;
    private String tipoOnda;
    boolean fechaThread = false;
    TextView textViewValorFrequencia;
    TextView textViewValorTensaoGerador;
    Button btnDecrementoFrequencia;
    Button btnDecrementoTensaoGerador;
    Button btnIncrementoFrequencia;
    Button btnIncrementoTensaoGerador;
    Button btnSalvar;
    RadioButton btnOndaQuadrada;
    RadioButton btnOndaSenoidal;
    RadioButton btnOndaTriangular;

    public ConfiguraGeradorFuncoesFragment() {
        // Required empty public constructor
    }

    public static ConfiguraGeradorFuncoesFragment newInstance(BluetoothConnectionActivity bluetoothConnectionActivity) {
        ConfiguraGeradorFuncoesFragment fragment = new ConfiguraGeradorFuncoesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        fragment.bluetoothConnectionActivity = bluetoothConnectionActivity;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bluetoothConnectionActivity.setBluetoothListener(this);
        String chaveGeradorFuncoes = "GERADORFUNCOES";
        byte[] bytes = chaveGeradorFuncoes.getBytes(Charset.defaultCharset());
        bluetoothConnectionActivity.write(bytes);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_configura_gerador_funcoes, container, false);
        btnDecrementoFrequencia = (Button) rootView.findViewById(R.id.btnDecrementoFrequencia);
        btnDecrementoTensaoGerador = (Button) rootView.findViewById(R.id.btnDecrementoTensaoGerador);
        btnIncrementoFrequencia= (Button) rootView.findViewById(R.id.btnIncrementoFrequencia);
        btnIncrementoTensaoGerador = (Button) rootView.findViewById(R.id.btnIncrementoTensaoGerador);
        btnSalvar = (Button) rootView.findViewById(R.id.btnSalvar);
        btnOndaQuadrada = (RadioButton) rootView.findViewById(R.id.btnOndaQuadrada);
        btnOndaSenoidal = (RadioButton) rootView.findViewById(R.id.btnOndaSenoidal);
        btnOndaTriangular = (RadioButton) rootView.findViewById(R.id.btnOndaTriangular);
        textViewValorFrequencia = (TextView) rootView.findViewById(R.id.textViewValorFrequencia);
        textViewValorTensaoGerador = (TextView) rootView.findViewById(R.id.textViewValorTensaoGerador);
        fragmentManager = getFragmentManager();

        btnDecrementoFrequencia.setOnClickListener(onClick);
        btnDecrementoTensaoGerador.setOnClickListener(onClick);
        btnIncrementoFrequencia.setOnClickListener(onClick);
        btnIncrementoTensaoGerador.setOnClickListener(onClick);
        btnSalvar.setOnClickListener(onClick);
        btnOndaQuadrada.setOnClickListener(onClick);
        btnOndaSenoidal.setOnClickListener(onClick);
        btnOndaTriangular.setOnClickListener(onClick);
        return rootView;
    }

    private final View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btnDecrementoFrequencia:
                    if(stepFrequencia >= 0){
                        stepFrequencia--;
                        valorFrequencia = valorFrequencia - (stepFrequencia * 3);
                        textViewValorFrequencia.setText(String.format("%.2f", valorFrequencia));
                    } else {}
                    break;
                case R.id.btnDecrementoTensaoGerador:
                    if(stepTensaoGerador >= 0){
                        stepTensaoGerador--;
                        valorTensaoGerador = valorTensaoGerador - (stepTensaoGerador * 5);
                        textViewValorTensaoGerador.setText(String.format("%.2f", valorTensaoGerador));
                    } else {}
                    break;
                case R.id.btnIncrementoFrequencia:
                    if(stepFrequencia <= 99){
                        stepFrequencia++;
                        valorFrequencia = valorFrequencia + (stepFrequencia * 3);
                        textViewValorFrequencia.setText(String.format("%.2f", valorFrequencia));
                    } else {}
                    break;
                case R.id.btnIncrementoTensaoGerador:
                    if(stepTensaoGerador <= 99){
                        stepTensaoGerador++;
                        valorTensaoGerador = valorTensaoGerador + (stepTensaoGerador * 5);
                        textViewValorTensaoGerador.setText(String.format("%.2f", valorTensaoGerador));
                    } else {}
                    break;
                case R.id.btnSalvar:
                    String valoresGerador = String.valueOf(stepFrequencia) + "." + String.valueOf(stepTensaoGerador) + "." + tipoOndaSelecionada;
                    byte[] bytes = valoresGerador.getBytes(Charset.defaultCharset());
                    bluetoothConnectionActivity.write(bytes);
                    fragmentManager.popBackStack();
                    break;
                case R.id.btnOndaQuadrada:
                    btnOndaQuadrada.setChecked(true);
                    btnOndaSenoidal.setChecked(false);
                    btnOndaTriangular.setChecked(false);
                    tipoOndaSelecionada = "Quadrada";
                    break;
                case R.id.btnOndaSenoidal:
                    btnOndaQuadrada.setChecked(false);
                    btnOndaSenoidal.setChecked(true);
                    btnOndaTriangular.setChecked(false);
                    tipoOndaSelecionada = "Senoidal";
                    break;
                case R.id.btnOndaTriangular:
                    btnOndaQuadrada.setChecked(false);
                    btnOndaSenoidal.setChecked(false);
                    btnOndaTriangular.setChecked(true);
                    tipoOndaSelecionada = "Triangular";
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void setTextOnTextView(String textoRecebido) {
        Log.d("GeradorFuncoesFragment", textoRecebido);
        String[] separaValor1 = textoRecebido.split("\\*");
        stepFrequencia = Integer.valueOf(separaValor1[0]);
        stepTensaoGerador = Integer.valueOf(separaValor1[1]);
        tipoOnda = separaValor1[2];
        valorFrequencia = stepFrequencia * 2;
        valorTensaoGerador = stepTensaoGerador * 4;

        if (getActivity() != null || fechaThread == true){
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    textViewValorFrequencia.setText(String.valueOf(valorFrequencia));
                    textViewValorTensaoGerador.setText(String.valueOf(valorTensaoGerador));
                    if(tipoOnda == "Quadrada"){
                        btnOndaQuadrada.setChecked(true);
                    }
                    if(tipoOnda == "Senoidal"){
                        btnOndaSenoidal.setChecked(true);
                    }
                    if(tipoOnda == "Triangular"){
                        btnOndaTriangular.setChecked(true);
                    }
                    fechaThread = true;
                    onStop();
                }
            });
        }
    }
}