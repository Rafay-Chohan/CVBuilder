package com.example.cvbuilder;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    //Hooks
    Animation splashscreen;
    ImageView ivLogo;
    TextView tvSubtitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //Function to initialize Hooks etc
        init();

        //Attaching Annimation to the hook
        splashscreen = AnimationUtils.loadAnimation(this, R.anim.splash_screen);

        //Attaching Annimation to the objects
        ivLogo.setAnimation(splashscreen);
        tvSubtitle.setAnimation(splashscreen);

        //Handler Starts the Activity after a delay
        new Handler()
                .postDelayed(()->{
                    startActivity(new Intent(MainActivity.this, HomePage.class));
                    finish();
                }, 4000);
    }
    private void init(){
        ivLogo = findViewById(R.id.ivLogo);
        tvSubtitle = findViewById(R.id.tvSubtitle);
    }
}