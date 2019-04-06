package com.example.securemessaging;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

public class signup extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText email,pass,re_pass;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth = FirebaseAuth.getInstance();

    }
    @Override
    public void onStart() {
        super.onStart();
    }

    public void sign_up(View view) {
        email = (EditText)findViewById(R.id.username);
        pass = (EditText)findViewById(R.id.password);
        re_pass = (EditText)findViewById(R.id.retype_password);
        String emailStr = email.getText().toString();
        String passStr = pass.getText().toString();
        String re_passStr = re_pass.getText().toString();
        if(TextUtils.isEmpty(emailStr)||TextUtils.isEmpty(passStr)||TextUtils.isEmpty(re_passStr)){
            Toast.makeText(signup.this,"please enter all the details",Toast.LENGTH_SHORT).show();
        }
        else if(pass.getText().toString().equals(re_pass.getText().toString())){
            firebase_signUp(email.getText().toString(),pass.getText().toString());
        }
        else{
            Toast.makeText(signup.this,"Password doesnt match!",Toast.LENGTH_LONG).show();
            pass.setText("");
            re_pass.setText("");
            pass.setFocusable(true);
        }
    }
    public void firebase_signUp(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        String error;
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(signup.this, "sign successful.",
                                    Toast.LENGTH_SHORT).show();
                            i = new Intent(signup.this,MainActivity.class);
                            startActivity(i);
                        } else {

                            String errorCode = ((FirebaseAuthException) task.getException()).getErrorCode();

                            switch (errorCode) {

                                case "ERROR_INVALID_CUSTOM_TOKEN":
                                    Toast.makeText(signup.this, "The custom token format is incorrect. Please check the documentation.", Toast.LENGTH_LONG).show();
                                    break;

                                case "ERROR_CUSTOM_TOKEN_MISMATCH":
                                    Toast.makeText(signup.this, "The custom token corresponds to a different audience.", Toast.LENGTH_LONG).show();
                                    break;

                                case "ERROR_INVALID_CREDENTIAL":
                                    Toast.makeText(signup.this, "The supplied auth credential is malformed or has expired.", Toast.LENGTH_LONG).show();
                                    break;


                                case "ERROR_USER_MISMATCH":
                                    Toast.makeText(signup.this, "The supplied credentials do not correspond to the previously signed in user.", Toast.LENGTH_LONG).show();
                                    break;

                                case "ERROR_REQUIRES_RECENT_LOGIN":
                                    Toast.makeText(signup.this, "This operation is sensitive and requires recent authentication. Log in again before retrying this request.", Toast.LENGTH_LONG).show();
                                    break;

                                case "ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL":
                                    Toast.makeText(signup.this, "An account already exists with the same email address but different sign-in credentials. Sign in using a provider associated with this email address.", Toast.LENGTH_LONG).show();
                                    break;

                                case "ERROR_EMAIL_ALREADY_IN_USE":
                                    //Toast.makeText(signup.this, "The email address is already in use by another account.   ", Toast.LENGTH_LONG).show();
                                    EditText user = (EditText)findViewById(R.id.username) ;
                                    user.setError("The email address is already in use by another account.");
                                    user.requestFocus();
                                    break;

                                case "ERROR_CREDENTIAL_ALREADY_IN_USE":
                                    Toast.makeText(signup.this, "This credential is already associated with a different user account.", Toast.LENGTH_LONG).show();
                                    break;

                                case "ERROR_USER_DISABLED":
                                    Toast.makeText(signup.this, "The user account has been disabled by an administrator.", Toast.LENGTH_LONG).show();
                                    break;

                                case "ERROR_USER_TOKEN_EXPIRED":
                                    Toast.makeText(signup.this, "The user\\'s credential is no longer valid. The user must sign in again.", Toast.LENGTH_LONG).show();
                                    break;

                                case "ERROR_USER_NOT_FOUND":
                                    Toast.makeText(signup.this, "There is no user record corresponding to this identifier. The user may have been deleted.", Toast.LENGTH_LONG).show();
                                    break;

                                case "ERROR_INVALID_USER_TOKEN":
                                    Toast.makeText(signup.this, "The user\\'s credential is no longer valid. The user must sign in again.", Toast.LENGTH_LONG).show();
                                    break;

                                case "ERROR_OPERATION_NOT_ALLOWED":
                                    Toast.makeText(signup.this, "This operation is not allowed. You must enable this service in the console.", Toast.LENGTH_LONG).show();
                                    break;

                                case "ERROR_WEAK_PASSWORD":
                                    Toast.makeText(signup.this, "The given password is invalid.", Toast.LENGTH_LONG).show();
                                    EditText etPassword = (EditText)findViewById(R.id.password);
                                    etPassword.setError("The password is invalid it must 6 characters at least");
                                    etPassword.setText("");
                                    etPassword.requestFocus();
                                    EditText etPassword1 = (EditText)findViewById(R.id.retype_password);
                                    etPassword1.setText("");
                                    break;

                            }

                        // ...
                    }
                }});

    }
}
