package dk.nikolaj.mynotebookexpanded.view;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import dk.nikolaj.mynotebookexpanded.R;
import dk.nikolaj.mynotebookexpanded.storage.NoteStorage;

public class ViewHolder extends RecyclerView.ViewHolder {

    public TextView textView;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        LinearLayout linearLayout = (LinearLayout) itemView;
        textView = linearLayout.findViewById(R.id.custom_row_textView);
    }

    public void setData(int row){
        textView.setText(NoteStorage.getNote(row).getHeadline());
    }
}
