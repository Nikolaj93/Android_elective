package dk.nikolaj.firebaselogindemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import dk.nikolaj.firebaselogindemo.Adapter.MyRecycleViewAdapter;
import dk.nikolaj.firebaselogindemo.Model.Note;
import dk.nikolaj.firebaselogindemo.Storage.FirestoreRepo;
import dk.nikolaj.firebaselogindemo.auth.FirebaseManager;

public class SecretActivity extends AppCompatActivity {

    private RecyclerView notesList;
    private MyRecycleViewAdapter adapter;
    private LinearLayoutManager layoutManager;
    private FirestoreRepo db;
    private List<Note> notes = new ArrayList<>();
    private FirebaseManager firebaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secret);
        db = FirestoreRepo.getInstance();
        firebaseManager = new FirebaseManager();
        notesList = findViewById(R.id.notesList);
        layoutManager = new LinearLayoutManager(this);
        notesList.setLayoutManager(layoutManager);
        adapter = new MyRecycleViewAdapter(notes);
        notesList.setAdapter(adapter);
        db.setChangeListener(notes, adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.note_list_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    public void newNote(MenuItem menuItem) {
        startActivity(new Intent(this, NoteEditActivity.class));
    }

    public void signOut(MenuItem menuItem) {
        firebaseManager.signOut();
    }
}
