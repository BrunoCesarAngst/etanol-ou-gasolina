package com.ramonguimaraes.etanolougasolina;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private Button btnCalcular;
    private TextInputEditText edtPrecoEtanol, edtPrecoGasolina;
    private Double gasolina = 0.0, etanol = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        edtPrecoEtanol = findViewById(R.id.edtEtanol);
        edtPrecoGasolina = findViewById(R.id.edtGasolina);
        btnCalcular = findViewById(R.id.btnCalcular);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    gasolina = Double.parseDouble(Objects.requireNonNull(edtPrecoGasolina.getText()).toString());
                    etanol = Double.parseDouble(Objects.requireNonNull(edtPrecoEtanol.getText()).toString());

                    double res = calc(etanol, gasolina);

                    showAlertDialog(res <= 0.7 ? getString(R.string.etanol) : getString(R.string.gasolina));
                } catch (Exception e) {
                    Snackbar snack = Snackbar.make(v, R.string.mensagem_de_erro, Snackbar.LENGTH_LONG);
                    snack.setAction(R.string.snackbar_msg_dismiss, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            snack.dismiss();
                        }
                    });
                    snack.show();
                }

            }
        });
    }

    private double calc(double valorEtanol, double valorGasolina) {
        return valorEtanol / valorGasolina;
    }

    private void showAlertDialog(String msg) {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        alertDialog.setTitle(getString(R.string.etanol_ou_gasolina)).setMessage(getString(R.string.compensa_usar) + msg)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        edtPrecoEtanol.setText("");
                        edtPrecoGasolina.setText("");
                        dialog.dismiss();
                    }

                }).create().show();

    }

}