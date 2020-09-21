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
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.nio.charset.Charset;

public class OsciloscopioFragment extends Fragment implements BluetoothListener {
    BluetoothConnectionActivity bluetoothConnectionActivity;
    FragmentManager fragmentManager;
    private View rootView;
    private int lastX = 0;
    Button btnSalvar;
    GraphView graficoOsciloscopio;
    LineGraphSeries<DataPoint> series;

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
        graficoOsciloscopio = (GraphView)rootView.findViewById(R.id.graficoOsciloscopio);
        series = new LineGraphSeries<DataPoint>();
        fragmentManager = getFragmentManager();

        series = new LineGraphSeries<DataPoint>();
        graficoOsciloscopio.addSeries(series);
        Viewport viewport = graficoOsciloscopio.getViewport();
        viewport.setYAxisBoundsManual(true);
        viewport.setMinY(-15);
        viewport.setMaxY(15);
        viewport.setXAxisBoundsManual(true);
        viewport.setMinX(0);
        viewport.setMaxX(100);
        viewport.setScrollable(true);
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
        //final String textoInseridoOsciloscopio = textoInserido;
        final Double numero = Double.parseDouble(textoInserido + ".00");
        if (getActivity() != null){
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    series.appendData(new DataPoint(lastX++, numero), true, 100);
                }
            });
        }
    }
}