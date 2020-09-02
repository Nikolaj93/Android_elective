package dk.nikolaj.firebaselogindemo.auth;

import android.app.Activity;

import androidx.annotation.NonNull;

import com.facebook.AccessToken;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;

public class FirebaseManager {
    private boolean signInStatus = false;
    FirebaseAuth auth; // current user object.

    public FirebaseManager() {
        auth = FirebaseAuth.getInstance();
        setupAuthStateListener();
    }

    private void setupAuthStateListener(){
        auth.addIdTokenListener(new FirebaseAuth.IdTokenListener() {
            @Override
            public void onIdTokenChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null){
                    System.out.println("Signed out from Firebase!");
                }else {
                    System.out.println("Signed in to Firebase!");
                }
            }
        });
    }

    public boolean signIn(String email, String pwd, final Activity activity){
        auth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    System.out.println("LOGIN success! " + task.getResult().getUser().getEmail());
                    signInStatus = true;
                } else {
                    System.out.println("LOGIN failed! " + task.getException());
                    signInStatus= false;
                }
            }
        });
        return signInStatus;
    }

    // sign up
    // will require email and password (min. 6 chars)
    public void signUp(String email, String pwd) {
        auth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    System.out.println("Sign up success! " + task.getResult().getUser().getEmail());
                } else {
                    System.out.println("Sign up failed! " + task.getException());
                }
            }
        });
    }

    public void signOut(){
        auth.signOut();
    }

    public void handleFacebookAccessToken(AccessToken token) {
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        auth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    System.out.println("Firebase Log in, using Facebook ");
                }else {
                    System.out.println("Failed Firebase Log in, using Facebook ");
                }
            }
        });
    }
}
