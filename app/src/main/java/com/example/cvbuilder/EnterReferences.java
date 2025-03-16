package com.example.cvbuilder;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EnterReferences extends AppCompatActivity {

    EditText etReferences;
    Button btnSave,btnCancel;
    String References;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_enter_references);

        init();
        btnSave.setOnClickListener(v->{
            References=etReferences.getText().toString().trim();
            if(References.isEmpty()) {
                etReferences.setError("Please Enter references");
            }
            else{
                Intent resultIntent = new Intent();
                resultIntent.putExtra("References", References);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
        btnCancel.setOnClickListener(v->{
            setResult(RESULT_CANCELED);
            finish();
        });
    }
    private void init(){
        etReferences=findViewById(R.id.etReferences);
        btnSave=findViewById(R.id.btnSave);
        btnCancel=findViewById(R.id.btnCancel);
    }
}