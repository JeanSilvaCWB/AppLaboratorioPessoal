package com.example.applaboratoriopessoal.View;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.applaboratoriopessoal.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class DescubraResistenciaFragment extends Fragment {
    FragmentManager fragmentManager;
    FrameLayout frameLayout;
    ConstraintLayout constraintLayoutResistencia;
    private View rootView;
    private int valorCor = 0;
    private int idFaixa = 0;
    private int valorPrimeiraFaixa;
    private int valorSegundaFaixa;
    private int valorTerceiraFaixa;
    private String concatenaValor;
    TextView textViewMostraResistencia;
    View btnPrimeiraFaixa;
    View btnSegundaFaixa;
    View btnTerceiraFaixa;
    View btnQuartaFaixa;
    CardView btnPreto1;
    CardView btnMarrom1;
    CardView btnVermelho1;
    CardView btnLaranja1;
    CardView btnAmarelo1;
    CardView btnVerde1;
    CardView btnAzul1;
    CardView btnVioleta1;
    CardView btnCinza1;
    CardView btnBranco1;
    Button btnVoltar;

    public DescubraResistenciaFragment() {
        // Required empty public constructor
    }

    public static DescubraResistenciaFragment newInstance() {
        DescubraResistenciaFragment fragment = new DescubraResistenciaFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_descubra_resistencia, container, false);
        frameLayout = (FrameLayout)rootView.findViewById(R.id.framePrimeiraFaixa);
        constraintLayoutResistencia = (ConstraintLayout)rootView.findViewById(R.id.constraintLayoutResistencia);
        textViewMostraResistencia = (TextView)rootView.findViewById(R.id.textViewMostraResistencia);
        btnPreto1 = (CardView)rootView.findViewById(R.id.btnPreto1);
        btnMarrom1 = (CardView)rootView.findViewById(R.id.btnMarrom1);
        btnVermelho1 = (CardView)rootView.findViewById(R.id.btnVermelho1);
        btnLaranja1 = (CardView)rootView.findViewById(R.id.btnLaranja1);
        btnAmarelo1 = (CardView)rootView.findViewById(R.id.btnAmarelo1);
        btnVerde1 = (CardView)rootView.findViewById(R.id.btnVerde1);
        btnAzul1 = (CardView)rootView.findViewById(R.id.btnAzul1);
        btnVioleta1 = (CardView)rootView.findViewById(R.id.btnVioleta1);
        btnCinza1 = (CardView)rootView.findViewById(R.id.btnCinza1);
        btnBranco1 = (CardView)rootView.findViewById(R.id.btnBranco1);
        btnVoltar = (Button)rootView.findViewById(R.id.btnVoltar);
        fragmentManager = getFragmentManager();
        btnPrimeiraFaixa = (View) rootView.findViewById(R.id.btnPrimeiraFaixa);
        btnSegundaFaixa = (View) rootView.findViewById(R.id.btnSegundaFaixa);
        btnTerceiraFaixa = (View) rootView.findViewById(R.id.btnTerceiraFaixa);
        btnQuartaFaixa = (View) rootView.findViewById(R.id.btnQuartaFaixa);

        btnPrimeiraFaixa.setOnClickListener(onClick);
        btnSegundaFaixa.setOnClickListener(onClick);
        btnTerceiraFaixa.setOnClickListener(onClick);
        btnQuartaFaixa.setOnClickListener(onClick);
        btnVoltar.setOnClickListener(onClick);
        btnPreto1.setOnClickListener(onClick);
        btnMarrom1.setOnClickListener(onClick);
        btnVermelho1.setOnClickListener(onClick);
        btnLaranja1.setOnClickListener(onClick);
        btnAmarelo1.setOnClickListener(onClick);
        btnVerde1.setOnClickListener(onClick);
        btnAzul1.setOnClickListener(onClick);
        btnVioleta1.setOnClickListener(onClick);
        btnCinza1.setOnClickListener(onClick);
        btnBranco1.setOnClickListener(onClick);
        return rootView;
    }

    private final View.OnClickListener onClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btnPrimeiraFaixa:
                    constraintLayoutResistencia.setVisibility(View.INVISIBLE);
                    frameLayout.setVisibility(View.VISIBLE);
                    idFaixa = 1;
                    break;
                case R.id.btnSegundaFaixa:
                    constraintLayoutResistencia.setVisibility(View.INVISIBLE);
                    frameLayout.setVisibility(View.VISIBLE);
                    idFaixa = 2;
                    break;
                case R.id.btnTerceiraFaixa:
                    constraintLayoutResistencia.setVisibility(View.INVISIBLE);
                    frameLayout.setVisibility(View.VISIBLE);
                    idFaixa = 3;
                    break;
                case R.id.btnPreto1:
                    constraintLayoutResistencia.setVisibility(View.VISIBLE);
                    frameLayout.setVisibility(View.INVISIBLE);
                    if(idFaixa == 1){
                        btnPrimeiraFaixa.setBackgroundColor(getResources().getColor(R.color.preto));
                        concatenaValor = String.valueOf(idFaixa) + "." + String.valueOf(0);
                        calculoResistencia(concatenaValor);
                    }
                    if(idFaixa == 2){
                        btnSegundaFaixa.setBackgroundColor(getResources().getColor(R.color.preto));
                        concatenaValor = String.valueOf(idFaixa) + "." + String.valueOf(0);
                        calculoResistencia(concatenaValor);
                    }
                    if(idFaixa == 3){
                        btnTerceiraFaixa.setBackgroundColor(getResources().getColor(R.color.preto));
                        concatenaValor = String.valueOf(idFaixa) + "." + String.valueOf(0);
                        calculoResistencia(concatenaValor);
                    }
                    break;
                case R.id.btnMarrom1:
                    constraintLayoutResistencia.setVisibility(View.VISIBLE);
                    frameLayout.setVisibility(View.INVISIBLE);
                    if(idFaixa == 1){
                        btnPrimeiraFaixa.setBackgroundColor(getResources().getColor(R.color.marrom));
                        concatenaValor = String.valueOf(idFaixa) + "." + String.valueOf(1);
                        calculoResistencia(concatenaValor);
                    }
                    if(idFaixa == 2){
                        btnSegundaFaixa.setBackgroundColor(getResources().getColor(R.color.marrom));
                        concatenaValor = String.valueOf(idFaixa) + "." + String.valueOf(1);
                        calculoResistencia(concatenaValor);
                    }
                    if(idFaixa == 3){
                        btnTerceiraFaixa.setBackgroundColor(getResources().getColor(R.color.marrom));
                        concatenaValor = String.valueOf(idFaixa) + "." + String.valueOf(10);
                        calculoResistencia(concatenaValor);
                    }
                    break;
                case R.id.btnVermelho1:
                    constraintLayoutResistencia.setVisibility(View.VISIBLE);
                    frameLayout.setVisibility(View.INVISIBLE);
                    if(idFaixa == 1){
                        btnPrimeiraFaixa.setBackgroundColor(getResources().getColor(R.color.vermelho));
                        concatenaValor = String.valueOf(idFaixa) + "." + String.valueOf(2);
                        calculoResistencia(concatenaValor);
                    }
                    if(idFaixa == 2){
                        btnSegundaFaixa.setBackgroundColor(getResources().getColor(R.color.vermelho));
                        concatenaValor = String.valueOf(idFaixa) + "." + String.valueOf(2);
                        calculoResistencia(concatenaValor);
                    }
                    if(idFaixa == 3){
                        btnTerceiraFaixa.setBackgroundColor(getResources().getColor(R.color.vermelho));
                        concatenaValor = String.valueOf(idFaixa) + "." + String.valueOf(100);
                        calculoResistencia(concatenaValor);
                    }
                    break;
                case R.id.btnLaranja1:
                    constraintLayoutResistencia.setVisibility(View.VISIBLE);
                    frameLayout.setVisibility(View.INVISIBLE);
                    if(idFaixa == 1){
                        btnPrimeiraFaixa.setBackgroundColor(getResources().getColor(R.color.laranja));
                        concatenaValor = String.valueOf(idFaixa) + "." + String.valueOf(3);
                        calculoResistencia(concatenaValor);
                    }
                    if(idFaixa == 2){
                        btnSegundaFaixa.setBackgroundColor(getResources().getColor(R.color.laranja));
                        concatenaValor = String.valueOf(idFaixa) + "." + String.valueOf(3);
                        calculoResistencia(concatenaValor);
                    }
                    if(idFaixa == 3){
                        btnTerceiraFaixa.setBackgroundColor(getResources().getColor(R.color.laranja));
                        concatenaValor = String.valueOf(idFaixa) + "." + String.valueOf(1000);
                        calculoResistencia(concatenaValor);
                    }
                    break;
                case R.id.btnAmarelo1:
                    constraintLayoutResistencia.setVisibility(View.VISIBLE);
                    frameLayout.setVisibility(View.INVISIBLE);
                    if(idFaixa == 1){
                        btnPrimeiraFaixa.setBackgroundColor(getResources().getColor(R.color.amarelo));
                        concatenaValor = String.valueOf(idFaixa) + "." + String.valueOf(4);
                        calculoResistencia(concatenaValor);
                    }
                    if(idFaixa == 2){
                        btnSegundaFaixa.setBackgroundColor(getResources().getColor(R.color.amarelo));
                        concatenaValor = String.valueOf(idFaixa) + "." + String.valueOf(4);
                        calculoResistencia(concatenaValor);
                    }
                    if(idFaixa == 3){
                        btnTerceiraFaixa.setBackgroundColor(getResources().getColor(R.color.amarelo));
                        concatenaValor = String.valueOf(idFaixa) + "." + String.valueOf(10000);
                        calculoResistencia(concatenaValor);
                    }
                    break;
                case R.id.btnVerde1:
                    constraintLayoutResistencia.setVisibility(View.VISIBLE);
                    frameLayout.setVisibility(View.INVISIBLE);
                    if(idFaixa == 1){
                        btnPrimeiraFaixa.setBackgroundColor(getResources().getColor(R.color.verde));
                        concatenaValor = String.valueOf(idFaixa) + "." + String.valueOf(5);
                        calculoResistencia(concatenaValor);
                    }
                    if(idFaixa == 2){
                        btnSegundaFaixa.setBackgroundColor(getResources().getColor(R.color.verde));
                        concatenaValor = String.valueOf(idFaixa) + "." + String.valueOf(5);
                        calculoResistencia(concatenaValor);
                    }
                    if(idFaixa == 3){
                        btnTerceiraFaixa.setBackgroundColor(getResources().getColor(R.color.verde));
                        concatenaValor = String.valueOf(idFaixa) + "." + String.valueOf(100000);
                        calculoResistencia(concatenaValor);
                    }
                    break;
                case R.id.btnAzul1:
                    constraintLayoutResistencia.setVisibility(View.VISIBLE);
                    frameLayout.setVisibility(View.INVISIBLE);
                    if(idFaixa == 1){
                        btnPrimeiraFaixa.setBackgroundColor(getResources().getColor(R.color.azul));
                        concatenaValor = String.valueOf(idFaixa) + "." + String.valueOf(6);
                        calculoResistencia(concatenaValor);
                    }
                    if(idFaixa == 2){
                        btnSegundaFaixa.setBackgroundColor(getResources().getColor(R.color.azul));
                        concatenaValor = String.valueOf(idFaixa) + "." + String.valueOf(6);
                        calculoResistencia(concatenaValor);
                    }
                    if(idFaixa == 3){
                        btnTerceiraFaixa.setBackgroundColor(getResources().getColor(R.color.azul));
                        concatenaValor = String.valueOf(idFaixa) + "." + String.valueOf(1000000);
                        calculoResistencia(concatenaValor);
                    }
                    break;
                case R.id.btnVioleta1:
                    constraintLayoutResistencia.setVisibility(View.VISIBLE);
                    frameLayout.setVisibility(View.INVISIBLE);
                    if(idFaixa == 1){
                        btnPrimeiraFaixa.setBackgroundColor(getResources().getColor(R.color.violeta));
                        concatenaValor = String.valueOf(idFaixa) + "." + String.valueOf(7);
                        calculoResistencia(concatenaValor);
                    }
                    if(idFaixa == 2){
                        btnSegundaFaixa.setBackgroundColor(getResources().getColor(R.color.violeta));
                        concatenaValor = String.valueOf(idFaixa) + "." + String.valueOf(7);
                        calculoResistencia(concatenaValor);
                    }
                    if(idFaixa == 3){
                        btnTerceiraFaixa.setBackgroundColor(getResources().getColor(R.color.violeta));
                        concatenaValor = String.valueOf(idFaixa) + "." + String.valueOf(10000000);
                        calculoResistencia(concatenaValor);
                    }
                    break;
                case R.id.btnCinza1:
                    constraintLayoutResistencia.setVisibility(View.VISIBLE);
                    frameLayout.setVisibility(View.INVISIBLE);
                    if(idFaixa == 1){
                        btnPrimeiraFaixa.setBackgroundColor(getResources().getColor(R.color.cinza));
                        concatenaValor = String.valueOf(idFaixa) + "." + String.valueOf(8);
                        calculoResistencia(concatenaValor);
                    }
                    if(idFaixa == 2){
                        btnSegundaFaixa.setBackgroundColor(getResources().getColor(R.color.cinza));
                        concatenaValor = String.valueOf(idFaixa) + "." + String.valueOf(8);
                        calculoResistencia(concatenaValor);
                    }
                    if(idFaixa == 3){

                    }
                    break;
                case R.id.btnBranco1:
                    constraintLayoutResistencia.setVisibility(View.VISIBLE);
                    frameLayout.setVisibility(View.INVISIBLE);
                    if(idFaixa == 1){
                        btnPrimeiraFaixa.setBackgroundColor(getResources().getColor(R.color.branco));
                        concatenaValor = String.valueOf(idFaixa) + "." + String.valueOf(9);
                        calculoResistencia(concatenaValor);
                    }
                    if(idFaixa == 2){
                        btnSegundaFaixa.setBackgroundColor(getResources().getColor(R.color.branco));
                        concatenaValor = String.valueOf(idFaixa) + "." + String.valueOf(9);
                        calculoResistencia(concatenaValor);
                    }
                    if(idFaixa == 3){

                    }
                    break;
                case R.id.btnVoltar:
                    //fragmentManager.popBackStack();
                    break;
                default:
                    break;
            }
        }
    };

    public void calculoResistencia(String numero){
        if(idFaixa == 1) {
            String[] separaValor1 = numero.split("\\.");
            valorPrimeiraFaixa = Integer.valueOf(separaValor1[1]);
        }
        if(idFaixa == 2) {
            String[] separaValor2 = numero.split("\\.");
            valorSegundaFaixa = Integer.valueOf(separaValor2[1]);
        }
        if(idFaixa == 3) {
            String[] separaValor2 = numero.split("\\.");
            valorTerceiraFaixa = Integer.valueOf(separaValor2[1]);
        }
        int fatorMultiplicador = Integer.valueOf(String.valueOf(valorTerceiraFaixa));
        int soma = Integer.valueOf((String.valueOf(valorPrimeiraFaixa) + String.valueOf(valorSegundaFaixa)));
        long resultado = soma * fatorMultiplicador;
        String valorfinal = format(resultado);
        textViewMostraResistencia.setText(valorfinal);
    }

    private static final NavigableMap<Long, String> suffixes = new TreeMap<>();
    static {
        suffixes.put(1_000L, "k");
        suffixes.put(1_000_000L, "M");
        suffixes.put(1_000_000_000L, "G");
        suffixes.put(1_000_000_000_000L, "T");
        suffixes.put(1_000_000_000_000_000L, "P");
        suffixes.put(1_000_000_000_000_000_000L, "E");
    }

    public static String format(long value) {
        //Long.MIN_VALUE == -Long.MIN_VALUE so we need an adjustment here
        if (value == Long.MIN_VALUE) return format(Long.MIN_VALUE + 1);
        if (value < 0) return "-" + format(-value);
        if (value < 1000) return Long.toString(value); //deal with easy case

        Map.Entry<Long, String> e = suffixes.floorEntry(value);
        Long divideBy = e.getKey();
        String suffix = e.getValue();

        long truncated = value / (divideBy / 10); //the number part of the output times 10
        boolean hasDecimal = truncated < 100 && (truncated / 10d) != (truncated / 10);
        return hasDecimal ? (truncated / 10d) + suffix : (truncated / 10) + suffix;
    }
}