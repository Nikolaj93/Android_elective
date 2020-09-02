package dk.nikolaj.week13listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import dk.nikolaj.week13listview.adapter.ListviewAdapter;
import dk.nikolaj.week13listview.model.Musician;

public class MainActivity extends AppCompatActivity {

    List<Musician> musicianList;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize
        musicianList = new ArrayList<>();

        //Adds musicians to list.
        musicianList.add(new Musician(R.drawable.bobdylan,"Bob Dylan","Folk"));
        musicianList.add(new Musician(R.drawable.jimihendrix,"Jimi Hendrix","Rock"));
        musicianList.add(new Musician(R.drawable.johnmayer,"John Mayer","Pop"));
        musicianList.add(new Musician(R.drawable.billyjoel,"Billy Joel","Rock/Pop"));

        listView = findViewById(R.id.listView);

        //Adapter created, passing this as context, the list view as layout, and the musicianlist
        ListviewAdapter adapter = new ListviewAdapter(this,R.layout.my_list_item, musicianList);

        //Atttach the adapter to listview.
        listView.setAdapter(adapter);
    }
}