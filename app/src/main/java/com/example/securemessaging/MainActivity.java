package com.example.securemessaging;

import android.content.Intent;
import android.graphics.Paint;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView img;
    Animation anim;
    Button forgot_pass;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = (ImageView) findViewById(R.id.sharingan);
        anim = AnimationUtils.loadAnimation(this,R.anim.login);
        img.startAnimation(anim);
        forgot_pass = (Button)findViewById(R.id.forgot_password);
        forgot_pass.setPaintFlags(forgot_pass.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);//underlining the text
    }

    public void listOfMessages(View view) {
        i = new Intent(this,listOfmessages.class);
        startActivity(i);
    }
}
