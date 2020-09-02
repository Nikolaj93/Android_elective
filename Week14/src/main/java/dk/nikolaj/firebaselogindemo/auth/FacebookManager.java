package dk.nikolaj.firebaselogindemo.auth;

import android.app.Activity;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

public class FacebookManager {

    public CallbackManager callbackManager;

    public FacebookManager(FirebaseManager firebaseManager) {
        this.firebaseManager = firebaseManager;
    }

    private FirebaseManager firebaseManager;

    public void handleFacebookLogin(LoginButton button, Activity activity){
        callbackManager = CallbackManager.Factory.create(); // Facebook class to handle events
        // this will start the login process
        LoginManager.getInstance().logInWithReadPermissions(activity, Arrays.asList("email"));
        // add more permissions: public_profile, user_hometown etc.
        button.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                System.out.println("Facebook login OK" + loginResult.getAccessToken().getUserId());
                // call firebase method to log in
                firebaseManager.handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                System.out.println("onCancel");
            }

            @Override
            public void onError(FacebookException error) {
                System.out.println("Facebook error" + error.getMessage());
            }
        });
    }
}
