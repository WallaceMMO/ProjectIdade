/*
 *@author:<Wallace Moura Machado de Oliveira;1110482413004>
 */
package com.example.projectidade;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText txtDia, txtMes, txtAno;
    Button btnCalcular;
    TextView lblResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtDia = findViewById(R.id.txtDia);
        txtMes = findViewById(R.id.txtMes);
        txtAno = findViewById(R.id.txtAno);
        btnCalcular = findViewById(R.id.btnCalcular);
        lblResultado = findViewById(R.id.lblResultado);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularIdade();
            }
        });
    }

    void calcularIdade() {
        int diaNascimento = Integer.parseInt(txtDia.getText().toString());
        int mesNascimento = Integer.parseInt(txtMes.getText().toString());
        int anoNascimento = Integer.parseInt(txtAno.getText().toString());

        Calendar dataAtual = Calendar.getInstance();
        int diaAtual = dataAtual.get(Calendar.DAY_OF_MONTH);
        int mesAtual = dataAtual.get(Calendar.MONTH) + 1;
        int anoAtual = dataAtual.get(Calendar.YEAR);

        int anos = anoAtual - anoNascimento;
        int meses = mesAtual - mesNascimento;
        int dias = diaAtual - diaNascimento;

        if (dias < 0) {
            meses--;
            dias += diasNoMes(mesAtual - 1, anoAtual);
        }

        if (meses < 0) {
            anos--;
            meses += 12;
        }

        lblResultado.setText("Você tem " + anos + " anos, " + meses + " meses e " + dias + " dias.");
    }

    int diasNoMes(int mes, int ano) {
        if (mes == 0) {
            mes = 12;
            ano--;
        }
        switch (mes) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                if (isBissexto(ano)) {
                    return 29;
                } else {
                    return 28;
                }
            default:
                return 0;
        }
    }

    boolean isBissexto(int ano) {
        if (ano % 4 != 0) {
            return false;
        } else if (ano % 100 != 0) {
            return true;
        } else if (ano % 400 == 0) {
            return true;
        } else {
            return false;
        }
    }
}