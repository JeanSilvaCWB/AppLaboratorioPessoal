package com.example.applaboratoriopessoal.View;

import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.applaboratoriopessoal.R;

import java.nio.charset.Charset;

public class ConfiguraFonteAlimentacaoActivity extends AppCompatActivity {
    private double valorTensao;
    private TextView textViewValorTensao;
    BluetoothConnectionActivity mBluetoothConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configura_fonte_alimentacao);

        textViewValorTensao = (TextView)findViewById(R.id.textViewValorTensao);
        textViewValorTensao.setText(String.valueOf(valorTensao));
        BluetoothConnectionActivity bluetoothConnectionActivity;
    }

    public void btnDiminuirTensao(View view) {
        if(valorTensao >= 0) {
            valorTensao = valorTensao - 0.1375;
            textViewValorTensao.setText(String.valueOf(valorTensao));
        }
    }

    public void btnAumentarTensao(View view) {
        if(valorTensao <= 15) {
            valorTensao = valorTensao + 0.1375;
            textViewValorTensao.setText(String.valueOf(valorTensao));
        }
    }

    public void btnSalvar(View view) {
        byte[] bytes = String.valueOf(valorTensao).getBytes(Charset.defaultCharset());
        mBluetoothConnection.write(bytes);
        /*Intent returnIntent = new Intent();
        setResult(ConfiguraFonteAlimentacaoActivity.RESULT_OK,returnIntent);
        finish();*/
    }
}
