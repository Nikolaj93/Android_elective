package dk.nikolaj.firebaselogindemo.Adapter;


import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dk.nikolaj.firebaselogindemo.Model.Note;
import dk.nikolaj.firebaselogindemo.R;
import dk.nikolaj.firebaselogindemo.ViewHolder.ViewHolder;

public class MyRecycleViewAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<Note> notes;

    public MyRecycleViewAdapter(List<Note> notes) {
        this.notes = notes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_element, parent, false), notes, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.initViewHolder(position);
    }


    @Override
    public int getItemCount() {
        return notes.size();
    }
}
