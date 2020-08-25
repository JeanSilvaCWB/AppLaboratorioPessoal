package com.example.applaboratoriopessoal.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.applaboratoriopessoal.Model.Dados;
import com.example.applaboratoriopessoal.R;

import java.nio.charset.Charset;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void btnConfigurarFonteAlimentacao(View view) {
        Intent intent = new Intent(MenuActivity.this, ConfiguraFonteAlimentacaoActivity.class);
        startActivityForResult(intent, 1);
    }

    public void btnVoltimetro(View view) {
        Intent intent = new Intent(MenuActivity.this, VoltimetroActivity.class);
        startActivityForResult(intent, 2);
    }

    public void btnFecharApp(View view) {
        finishAffinity();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intentRetornado) {
        super.onActivityResult(requestCode, resultCode, intentRetornado);
        if ((requestCode == 1) && (resultCode == ConfiguraFonteAlimentacaoActivity.RESULT_OK)) {
            Toast.makeText(MenuActivity.this, "Retornou do Configurar Fonte de Alimentação.", Toast.LENGTH_LONG).show();
        }
        if ((requestCode == 2) && (resultCode == VoltimetroActivity.RESULT_OK)) {
            Toast.makeText(MenuActivity.this, "Retornou do Voltímetro.", Toast.LENGTH_LONG).show();
        }
    }
}
