package com.example.cvbuilder;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EnterEducation extends AppCompatActivity {

    EditText etEducation,etProgram;
    Button btnSave,btnCancel;
    String education,program;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_enter_education);

        init();
        btnSave.setOnClickListener(v->{
            education=etEducation.getText().toString().trim();
            program=etProgram.getText().toString().trim();
            if(education.isEmpty() || program.isEmpty()){
                Toast.makeText(EnterEducation.this,"Enter Complete Info",Toast.LENGTH_SHORT).show();
            }
            else{
                Intent resultIntent = new Intent();
                resultIntent.putExtra("Education", program+" from "+education);
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
        etEducation=findViewById(R.id.etEducation);
        etProgram=findViewById(R.id.etProgram);
        btnSave=findViewById(R.id.btnSave);
        btnCancel=findViewById(R.id.btnCancel);
    }
}