package dk.nikolaj.firebaselogindemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.FaceDetector;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.login.widget.LoginButton;

import dk.nikolaj.firebaselogindemo.auth.FacebookManager;
import dk.nikolaj.firebaselogindemo.auth.FirebaseManager;

import java.sql.SQLOutput;

public class MainActivity extends AppCompatActivity {

    
    private EditText emailText;
    private EditText passwordText;
    private FirebaseManager firebaseManager;
    private FacebookManager facebookManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emailText = findViewById(R.id.emailText);
        passwordText = findViewById(R.id.passwordText);
        firebaseManager = new FirebaseManager();
        facebookManager = new FacebookManager(firebaseManager);
        Button lambdaBtn = findViewById(R.id.lambdaBtn);
        addClickListener(lambdaBtn, "Lambda pressed");
        LoginButton loginButton = findViewById(R.id.facebook_login); // Facebook's own login button
        facebookManager.handleFacebookLogin(loginButton, this); // start Facebook login process
    }

    void addClickListener(Button button, String txt){
        button.setOnClickListener(view -> {
            System.out.println("Button click " + txt + " type:" + view.getClass().getName());
        });
    }

    public void signIn(View view){
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();
        if (email.length() > 0 && password.length() > 0 ) {
            firebaseManager.signIn(email, password, this);
            finish();
            Intent intentLogin = new Intent(this, SecretActivity.class);
            startActivity(intentLogin);
        } else {
            Toast.makeText(this, "Please fill both fields", Toast.LENGTH_SHORT).show();
        }
    }
    public void signUp(View view) {
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();
        if (email.length() > 0 && password.length() > 0) {
            firebaseManager.signUp(email, password);
        } else {
            Toast.makeText(this, "Please fill both fields", Toast.LENGTH_SHORT).show();
        }
    }
    /*
    public void signOut(View view){
        firebaseManager.signOut();
    }*/

    // keep this activity as minimal as possible. Put code in separate classes
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        facebookManager.callbackManager.onActivityResult(requestCode, resultCode, data); // notify callbackManager
        super.onActivityResult(requestCode, resultCode, data);
    }
}