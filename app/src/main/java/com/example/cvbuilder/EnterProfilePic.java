package com.example.cvbuilder;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class EnterProfilePic extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    FloatingActionButton btnSelectImage;
    ImageView ivPfp;
    Button btnSave,btnCancel;
    private Uri imageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_enter_profile_pic);

        btnSelectImage=findViewById(R.id.btnSelectImage);
        btnSave=findViewById(R.id.btnSave);
        btnCancel=findViewById(R.id.btnCancel);
        ivPfp=findViewById(R.id.ivProfilePic);


        btnSelectImage.setOnClickListener(v->{
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.setType("image/*");
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            startActivityForResult(intent, 1);
        });
        btnSave.setOnClickListener(v->{
            Intent resultIntent = new Intent();
            resultIntent.putExtra("profileImageUri", imageUri.toString());
            resultIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
        btnCancel.setOnClickListener(v->{
            setResult(RESULT_CANCELED);
            finish();
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
            ivPfp.setImageURI(imageUri);
        }
    }
}