package dk.nikolaj.mynotebookexpanded.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dk.nikolaj.mynotebookexpanded.EditNote;
import dk.nikolaj.mynotebookexpanded.R;
import dk.nikolaj.mynotebookexpanded.model.Note;
import dk.nikolaj.mynotebookexpanded.storage.NoteStorage;
import dk.nikolaj.mynotebookexpanded.view.ViewHolder;

public class MyRecycleViewAdapter extends RecyclerView.Adapter<ViewHolder> {

    private ArrayList<Note> list;
    public static final String headlineKey = "HEADLINE_KEY";
    public static final String bodyKey = "BODY_KEY";
    public static final String positionKey = "POSITION_KEY";

    public MyRecycleViewAdapter() {
        this.list = NoteStorage.getList();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout ll = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_row, parent, false);
        return new ViewHolder(ll);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.setData(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), EditNote.class);
                intent.putExtra(headlineKey, NoteStorage.getList().get(position).getHeadline());
                intent.putExtra(bodyKey, NoteStorage.getList().get(position).getBody());
                intent.putExtra(positionKey, position);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return NoteStorage.getLength();
    }
}