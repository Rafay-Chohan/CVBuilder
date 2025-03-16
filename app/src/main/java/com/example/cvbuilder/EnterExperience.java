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

public class EnterExperience extends AppCompatActivity {

    EditText etWorkPlace,etWorkDuration;
    Button btnSave,btnCancel;
    String WorkPlace,WorkDuration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_enter_experience);

        init();
        btnSave.setOnClickListener(v->{
            WorkPlace=etWorkPlace.getText().toString().trim();
            WorkDuration=etWorkDuration.getText().toString().trim();
            if(WorkPlace.isEmpty() || WorkDuration.isEmpty()){
                Toast.makeText(EnterExperience.this,"Enter Complete Info",Toast.LENGTH_SHORT).show();
            }
            else{
                Intent resultIntent = new Intent();
                resultIntent.putExtra("WorkPlace", WorkPlace);
                resultIntent.putExtra("WorkDuration", WorkDuration);
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
        etWorkPlace=findViewById(R.id.etWorkPlace);
        etWorkDuration=findViewById(R.id.etWorkDuration);
        btnSave=findViewById(R.id.btnSave);
        btnCancel=findViewById(R.id.btnCancel);
    }
}