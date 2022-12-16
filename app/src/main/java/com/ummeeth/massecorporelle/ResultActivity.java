package com.ummeeth.massecorporelle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private TextView result_value;
    private TextView result_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        result_value = findViewById(R.id.result_IMC);
        result_text = findViewById(R.id.result_IMCText);

        Intent resultActivity = getIntent();

        String  valeurIMC = String.valueOf(resultActivity.getDoubleExtra("valeurIMC",-1));
        result_value.setText(valeurIMC);
        result_text.setText(resultActivity.getStringExtra("valeurIMCText"));

    }
}