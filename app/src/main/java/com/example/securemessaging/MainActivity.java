package com.example.securemessaging;

import android.content.Intent;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText user,pass;
    ImageView img;
    Animation anim;
    Button forgot_pass;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        img = (ImageView) findViewById(R.id.sharingan);
        anim = AnimationUtils.loadAnimation(this,R.anim.login);
        img.startAnimation(anim);
        forgot_pass = (Button)findViewById(R.id.forgot_password);
        forgot_pass.setPaintFlags(forgot_pass.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);//underlining the text
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.


    }

    public void signIn(String email,String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Log.d("Logged-in", "signInWithEmail:success");
                            i = new Intent(MainActivity.this,listOfmessages.class);
                            startActivity(i);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("Incorrect user and pass", "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }
    public void login(View view) {

        user = (EditText)findViewById(R.id.username);
        pass = (EditText)findViewById(R.id.password);
        String userNameString = user.getText().toString();
        String passwordString = pass.getText().toString();
        if(TextUtils.isEmpty(userNameString)||TextUtils.isEmpty(passwordString)){
            Toast.makeText(MainActivity.this,"please enter all the details",Toast.LENGTH_SHORT).show();
        }
        else
            signIn(userNameString,passwordString);
    }
}
