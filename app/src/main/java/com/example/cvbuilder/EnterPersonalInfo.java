package com.example.cvbuilder;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EnterPersonalInfo extends AppCompatActivity {

    //Hooks
    EditText etName,etEmail,etPhoneNumber;
    String Name,Email,PhoneNumber,Gender;
    private RadioGroup radioGroupGender;
    private RadioButton radioMale, radioFemale, radioOther;
    Button btnSave,btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_enter_personal_info);

        init();
        btnSave.setOnClickListener(v->{
            Name=etName.getText().toString().trim();
            Email=etEmail.getText().toString().trim();
            PhoneNumber=etPhoneNumber.getText().toString().trim();
            if(Name.isEmpty() || Email.isEmpty()|| PhoneNumber.isEmpty())
            {
                Toast.makeText(EnterPersonalInfo.this,"Please Enter Complete Info",Toast.LENGTH_SHORT).show();
            }
            else {
                int selectedId = radioGroupGender.getCheckedRadioButtonId();
                if (selectedId == R.id.radioMale) {
                    Gender = "Male";
                } else if (selectedId == R.id.radioFemale) {
                    Gender = "Female";
                } else if (selectedId == R.id.radioOther) {
                    Gender = "Other";
                }
                Intent resultIntent = new Intent();
                resultIntent.putExtra("Name", Name);
                resultIntent.putExtra("Email", Email);
                resultIntent.putExtra("PhoneNumber", PhoneNumber);
                resultIntent.putExtra("Gender", Gender);
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
        etName=findViewById(R.id.etName);
        etEmail=findViewById(R.id.etEmail);
        etPhoneNumber=findViewById(R.id.etPhoneNumber);
        radioGroupGender=findViewById(R.id.radioGroupGender);
        btnSave=findViewById(R.id.btnSave);
        btnCancel=findViewById(R.id.btnCancel);
    }
}