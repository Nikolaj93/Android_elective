package dk.nikolaj.week17twofragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import dk.nikolaj.week17twofragment.fragments.ListFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new ListFragment()).commit();
        }
    }
}