package com.example.cvbuilder;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EnterSummary extends AppCompatActivity {

    EditText etSummary;
    Button btnSave,btnCancel;
    String summary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_enter_summary);

        init();
        btnSave.setOnClickListener(v->{
            summary=etSummary.getText().toString().trim();
            if(summary.isEmpty()) {
                etSummary.setError("Please Enter Summary");
            }
            else{
                Intent resultIntent = new Intent();
                resultIntent.putExtra("Summary", summary);
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
        etSummary=findViewById(R.id.etSummary);
        btnSave=findViewById(R.id.btnSave);
        btnCancel=findViewById(R.id.btnCancel);
    }
}