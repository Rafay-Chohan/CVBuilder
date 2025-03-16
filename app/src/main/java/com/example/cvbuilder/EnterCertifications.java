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

public class EnterCertifications extends AppCompatActivity {


    EditText etCertifications;
    Button btnSave,btnCancel;
    String Certifications;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_enter_certifications);

        init();
        btnSave.setOnClickListener(v->{
            Certifications=etCertifications.getText().toString().trim();
            if(Certifications.isEmpty()) {
                etCertifications.setError("Please Enter Certifications");
            }
            else{
                Intent resultIntent = new Intent();
                resultIntent.putExtra("Certification", Certifications);
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
        etCertifications=findViewById(R.id.etCertifications);
        btnSave=findViewById(R.id.btnSave);
        btnCancel=findViewById(R.id.btnCancel);
    }
}