package com.example.applaboratoriopessoal.View;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.applaboratoriopessoal.R;

public class VoltimetroActivity extends AppCompatActivity {
    private static final String TAG = "VoltimetroActivity";
    TextView textViewValorTensaoVoltimetro;
    String teste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voltimetro);
        textViewValorTensaoVoltimetro = (TextView)findViewById(R.id.textViewValorTensaoVoltimetro);
        mostraValorTensao();
    }

    public void mostraValorTensao() {
        teste = BluetoothConnectionActivity.incomingMessage;
        textViewValorTensaoVoltimetro.setText(teste);
    }

    public void btnVoltar(View view) {
        Intent returnIntent = new Intent();
        setResult(VoltimetroActivity.RESULT_OK,returnIntent);
        finish();
    }
}
