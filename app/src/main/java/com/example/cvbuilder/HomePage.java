package com.example.cvbuilder;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.ByteArrayOutputStream;

public class HomePage extends AppCompatActivity {

    //Hooks
    Boolean ImageSelected=false;
    Button btnProfilePic,btnPersonalInfo,btnSummary,btnEducation,btnExperience,btnCertification,btnReferences,btnPreviewCV;
    private ActivityResultLauncher<Intent> personalDetailsLauncher;
    private ActivityResultLauncher<Intent> ProfilePicLauncher;
    private ActivityResultLauncher<Intent> SummaryLauncher;
    private ActivityResultLauncher<Intent> EducationLauncher;
    private ActivityResultLauncher<Intent> ExperienceLauncher;
    private ActivityResultLauncher<Intent> CertificationLauncher;
    private ActivityResultLauncher<Intent> ReferencesLauncher;
    private CV cvInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_page);

        //Initializing
        init();
        btnProfilePic.setOnClickListener(v->{
            ProfilePicLauncher.launch(new Intent(HomePage.this,EnterProfilePic.class));
        });
        btnPersonalInfo.setOnClickListener(v->{
            personalDetailsLauncher.launch(new Intent(HomePage.this, EnterPersonalInfo.class));
        });
        btnSummary.setOnClickListener(v->{
            SummaryLauncher.launch(new Intent(HomePage.this, EnterSummary.class));
        });
        btnEducation.setOnClickListener(v->{
            EducationLauncher.launch(new Intent(HomePage.this, EnterEducation.class));
        });
        btnExperience.setOnClickListener(v->{
            ExperienceLauncher.launch(new Intent(HomePage.this,EnterExperience.class));
        });
        btnCertification.setOnClickListener(v->{
            CertificationLauncher.launch(new Intent(HomePage.this, EnterCertifications.class));
        });
        btnReferences.setOnClickListener(v->{
            ReferencesLauncher.launch(new Intent(HomePage.this, EnterReferences.class));
        });

        btnPreviewCV.setOnClickListener(v->{
            Intent intent = new Intent(HomePage.this, CVPreview.class);
            if(cvInfo.getName().isEmpty()) {
                intent.putExtra("Name", "Empty");
                intent.putExtra("Email","");
                intent.putExtra("PhoneNumber","");
                intent.putExtra("Gender","Empty");
            }
            else {
                intent.putExtra("Name", cvInfo.getName());
                intent.putExtra("Email",cvInfo.getEmail());
                intent.putExtra("PhoneNumber",cvInfo.getPhoneNumber());
                intent.putExtra("Gender",cvInfo.getGender());
            }
            intent.putExtra("Summary",cvInfo.getSummary());
            intent.putExtra("Education",cvInfo.getEducation());
            intent.putExtra("WorkPlace",cvInfo.getWorkPlace());
            intent.putExtra("WorkDuration", cvInfo.getWorkDuration());
            intent.putExtra("Certification",cvInfo.getCertification());
            intent.putExtra("References",cvInfo.getReferences());


            if(ImageSelected) {
                intent.putExtra("profileImageUri", cvInfo.getProfilePic().toString());
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            }

            startActivity(intent);
        });

    }
    private void init(){
        btnProfilePic=findViewById(R.id.btnProfilePic);
        btnPersonalInfo=findViewById(R.id.btnPersonalInfo);
        btnSummary=findViewById(R.id.btnSummary);
        btnEducation=findViewById(R.id.btnEducation);
        btnExperience=findViewById(R.id.btnExperience);
        btnCertification=findViewById(R.id.btnCertification);
        btnReferences=findViewById(R.id.btnReferences);
        btnPreviewCV=findViewById(R.id.btnPreviewCV);
        cvInfo=new CV();

        // Activity Result Launcher
        ProfilePicLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        String imageUriString = data.getStringExtra("profileImageUri");
                        if (imageUriString != null) {
                            Uri imageUri = Uri.parse(imageUriString);

                            getContentResolver().takePersistableUriPermission(
                                    imageUri, Intent.FLAG_GRANT_READ_URI_PERMISSION
                            );
                            cvInfo.setProfilePic(imageUri);
                            ImageSelected=true;
                        }
                        }

                }
        );
        // Activity Result Launcher
        personalDetailsLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                             cvInfo.setName(data.getStringExtra("Name"));
                             cvInfo.setEmail(data.getStringExtra("Email"));
                             cvInfo.setPhoneNumber(data.getStringExtra("PhoneNumber"));
                             cvInfo.setGender(data.getStringExtra("Gender"));
                        }
                    }
                }
        );
        // Activity Result Launcher
        SummaryLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            cvInfo.setSummary(data.getStringExtra("Summary"));
                        }
                    }
                }
        );
        EducationLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            cvInfo.setEducation(data.getStringExtra("Education"));
                        }
                    }
                }
        );
        ExperienceLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            cvInfo.setWorkPlace(data.getStringExtra("WorkPlace"));
                            cvInfo.setWorkDuration(data.getStringExtra("WorkDuration")+" Months");
                        }
                    }
                }
        );
        CertificationLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            cvInfo.setCertification(data.getStringExtra("Certification"));
                        }
                    }
                }
        );
        ReferencesLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            cvInfo.setReferences(data.getStringExtra("References"));
                        }
                    }
                }
        );
    }
}