package com.example.cvbuilder;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class CVPreview extends AppCompatActivity {



    public static final String READ_MEDIA_IMAGES = "android.permission.READ_MEDIA_IMAGES";
    //Hook
    ImageView ivProfilePic;
    Button btnShare, btnBack;
    TextView tvName,tvEmail,tvGender,tvPhoneNumber,tvSummary,tvEducation,tvWorkPlace,tvWorkDuration,tvCertification,tvReferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cvpreview);

        init();

        tvName.setText(getIntent().getStringExtra("Name"));
        tvEmail.setText(getIntent().getStringExtra("Email"));
        tvPhoneNumber.setText(getIntent().getStringExtra("PhoneNumber"));
        tvGender.setText(getIntent().getStringExtra("Gender"));
        tvSummary.setText(getIntent().getStringExtra("Summary"));
        tvEducation.setText(getIntent().getStringExtra("Education"));
        tvWorkPlace.setText(getIntent().getStringExtra("WorkPlace"));
        tvWorkDuration.setText(getIntent().getStringExtra("WorkDuration"));
        tvCertification.setText(getIntent().getStringExtra("Certification"));
        tvReferences.setText(getIntent().getStringExtra("References"));
        String imageUriString = getIntent().getStringExtra("profileImageUri");
        if (imageUriString != null) {
            Uri imageUri = Uri.parse(imageUriString);
            getContentResolver().takePersistableUriPermission(
                    imageUri, Intent.FLAG_GRANT_READ_URI_PERMISSION
            );
            ivProfilePic.setImageURI(imageUri);
        }
        btnShare.setOnClickListener(v->generateAndSharePDF());
        btnBack.setOnClickListener(v->{
            finish();
        });

    }
    private void init(){
        tvName=findViewById(R.id.tvName);
        tvEmail=findViewById(R.id.tvEmail);
        tvGender=findViewById(R.id.tvGender);
        tvPhoneNumber=findViewById(R.id.tvPhoneNumber);
        tvSummary=findViewById(R.id.tvSummary);
        tvEducation=findViewById(R.id.tvEducation);
        tvWorkPlace=findViewById(R.id.tvWorkPlace);
        tvWorkDuration=findViewById(R.id.tvWorkDuration);
        tvCertification=findViewById(R.id.tvCertification);
        tvReferences=findViewById(R.id.tvReferences);
        ivProfilePic=findViewById(R.id.ivProfilePicture);
        btnShare=findViewById(R.id.btnShare);
        btnBack=findViewById(R.id.btnBack);

    }
    public void generateAndSharePDF() {
        PdfDocument document = new PdfDocument();
        Paint paint = new Paint();
        Paint titlePaint = new Paint();
        int pageWidth = 600;
        int pageHeight = 800;

        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(pageWidth, pageHeight, 1).create();
        PdfDocument.Page page = document.startPage(pageInfo);
        Canvas canvas = page.getCanvas();

        // Extract Bitmap from ImageView
        ivProfilePic.setDrawingCacheEnabled(true);
        Bitmap profileBitmap = Bitmap.createBitmap(ivProfilePic.getDrawingCache());
        ivProfilePic.setDrawingCacheEnabled(false);

        if (profileBitmap != null) {
            Bitmap resizedBitmap = Bitmap.createScaledBitmap(profileBitmap, 120, 120, false);
            canvas.drawBitmap(resizedBitmap, (pageWidth - 120) / 2, 20, paint);
        }

        titlePaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        titlePaint.setTextSize(24);
        titlePaint.setTextAlign(Paint.Align.LEFT);

        paint.setTextSize(16);
        paint.setTextAlign(Paint.Align.LEFT);

        int yPosition = 180;

        // Name
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        canvas.drawText("Name: " + tvName.getText().toString(), 50, yPosition, paint);
        yPosition += 30;

        // Gender
        paint.setTypeface(Typeface.DEFAULT);
        canvas.drawText("Gender: " + tvGender.getText().toString(), 50, yPosition, paint);
        yPosition += 30;

        // Email
        canvas.drawText("Email: " + tvEmail.getText().toString(), 50, yPosition, paint);
        yPosition += 30;

        // Phone Number
        canvas.drawText("Phone: " + tvPhoneNumber.getText().toString(), 50, yPosition, paint);
        yPosition += 40;

        // Section Titles
        titlePaint.setTextSize(18);
        titlePaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));

        // Summary
        canvas.drawText("Summary", 50, yPosition, titlePaint);
        yPosition += 25;
        paint.setTypeface(Typeface.DEFAULT);
        canvas.drawText(tvSummary.getText().toString(), 50, yPosition, paint);
        yPosition += 50;

        // Education
        canvas.drawText("Education", 50, yPosition, titlePaint);
        yPosition += 25;
        canvas.drawText(tvEducation.getText().toString(), 50, yPosition, paint);
        yPosition += 50;

        // Experience
        canvas.drawText("Experience", 50, yPosition, titlePaint);
        yPosition += 25;
        canvas.drawText(tvWorkPlace.getText().toString(), 50, yPosition, paint);
        yPosition += 25;
        canvas.drawText(tvWorkDuration.getText().toString(), 50, yPosition, paint);
        yPosition += 50;

        // Certifications
        canvas.drawText("Certifications", 50, yPosition, titlePaint);
        yPosition += 25;
        canvas.drawText(tvCertification.getText().toString(), 50, yPosition, paint);
        yPosition += 50;

        // References
        canvas.drawText("References", 50, yPosition, titlePaint);
        yPosition += 25;
        canvas.drawText(tvReferences.getText().toString(), 50, yPosition, paint);

        document.finishPage(page);
        File pdfFile = new File(getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "MyCV.pdf");
        try {
            FileOutputStream fos = new FileOutputStream(pdfFile);
            document.writeTo(fos);
            document.close();
            fos.close();
            sharePDF(pdfFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void sharePDF(File file) {
        Uri uri = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".provider", file);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("application/pdf");
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        intent.putExtra(Intent.EXTRA_SUBJECT, "My CV");
        intent.putExtra(Intent.EXTRA_TEXT, "Here is my CV.");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(Intent.createChooser(intent, "Share CV"));
    }


}