package com.ummeeth.massecorporelle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        TextView result_value = findViewById(R.id.result_IMC);
        TextView result_text = findViewById(R.id.result_IMCText);

        Intent resultActivity = getIntent();

        String valeurIMC = String.valueOf(resultActivity.getFloatExtra("valeurIMC", 0));
        result_value.setText(valeurIMC);
        result_text.setText(resultActivity.getStringExtra("valeurIMCText"));

    }
}