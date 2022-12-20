package com.ummeeth.massecorporelle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText ET_saisieTaille;
    private EditText ET_saisiePoids;
    private Button BT_effacer;
    private Button BT_calculer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar main_toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(main_toolbar);

        ET_saisieTaille = findViewById(R.id.main_taille_et);
        ET_saisiePoids = findViewById(R.id.main_poids_et);
        BT_effacer = findViewById(R.id.main_cancel_bt);
        BT_calculer = findViewById(R.id.main_calcul_bt);

        BT_calculer.setEnabled(false);
    }

    @Override
    protected void onStart()  {
        super.onStart();

        ET_saisieTaille.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2){}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2){

                if (!ET_saisieTaille.getText().toString().isEmpty() && !ET_saisiePoids.getText().toString().isEmpty()) {
                    BT_calculer.setEnabled(true);
                } else {
                    BT_calculer.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        ET_saisiePoids.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (!ET_saisieTaille.getText().toString().isEmpty() && !ET_saisiePoids.getText().toString().isEmpty()) {
                    BT_calculer.setEnabled(true);
                } else {
                    BT_calculer.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        BT_effacer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetFields();
            }
        });

        BT_calculer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent resultActivity = new Intent(MainActivity.this, ResultActivity.class);
                resultActivity.putExtra("valeurIMC", calculIMC(ET_saisieTaille, ET_saisiePoids));
                resultActivity.putExtra("valeurIMCText", textIMC(calculIMC(ET_saisieTaille, ET_saisiePoids)));
                startActivity(resultActivity);
                float IMC = calculIMC(ET_saisieTaille, ET_saisiePoids);
                String textIMC = textIMC(IMC);

                Toast toast = Toast.makeText(getApplicationContext(), "Vous faîtes " + IMC + "kg/m²\n" + textIMC, Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    private void resetFields() {
        ET_saisieTaille.setText("");
        ET_saisiePoids.setText("");
        BT_calculer.setEnabled(true);
        ET_saisieTaille.requestFocus();
    }

    private float calculIMC(EditText taille, EditText poids) {
        float convertionCmM = Float.parseFloat(taille.getText().toString())/100;
        float IMC = Float.parseFloat(poids.getText().toString())/(convertionCmM*convertionCmM);

        DecimalFormat nbreApresDecimal = new DecimalFormat("0.00");
        nbreApresDecimal.setMaximumFractionDigits(2);
        return IMC = Float.parseFloat(nbreApresDecimal.format(IMC));
    }

    private String textIMC(float IMC) {

        String textIMC = "On peut plus rien faire pour vous...";
        if (IMC < 16.5) {
            textIMC = "Vous êtes en dénutrition";
        } else if (IMC < 18.5) {
            textIMC = "Vous êtes maigre";
        } else if (IMC < 25) {
            textIMC = "Vous avez un poids normal";
        } else if (IMC < 30) {
            textIMC = "Vous êtes en surpoids";
        } else if (IMC < 35) {
            textIMC = "Vous êtes obèse";
        } else if (IMC < 40) {
            textIMC = "Vous êtes VRAIMENT obèse";
        }
        return textIMC;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_favorite:
                //Do action
                return true;
            case R.id.action_delete:
                //Do action
                return true;
            case R.id.action_setting:
                //Do action
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}