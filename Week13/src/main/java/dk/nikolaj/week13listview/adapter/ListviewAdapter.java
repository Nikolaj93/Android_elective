package dk.nikolaj.week13listview.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import dk.nikolaj.week13listview.R;
import dk.nikolaj.week13listview.model.Musician;

public class ListviewAdapter extends ArrayAdapter<Musician> {

    Context mCtx; //Context is needed to inflate
    int resource;
    List<Musician> musicianList;

    //Constructor adapter
    public ListviewAdapter(Context mCtx, int resource, List<Musician> musicianList){
        super(mCtx,resource, musicianList);
        this.mCtx = mCtx;
        this.resource = resource;
        this.musicianList = musicianList;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mCtx); //Inflater

        View view = inflater.inflate(resource,null); //To inflate the view
        TextView textViewName = view.findViewById(R.id.textViewName);
        TextView textViewGenre = view.findViewById(R.id.textViewGenre);
        ImageView imageView = view.findViewById(R.id.imageView);

        //All the data to be displayed
        Musician musician = musicianList.get(position); //Musician object of specified position
        textViewName.setText(musician.getName());
        textViewGenre.setText(musician.getGenre());
        imageView.setImageDrawable(mCtx.getDrawable(musician.getImage()));

        //Anonymous class for onclick listener
        view.findViewById(R.id.buttonDelete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeItem(position);
            }
        });
        //Returns the view
        return view;
    }

    private void removeItem(final int index){ //delete method, final cause its an innerclass
        AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);
        builder.setTitle("Do you want to delete " + getItem(index).getName() + " from the list?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Will delete the selected musician.
                musicianList.remove(index);
                notifyDataSetChanged();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Dont do anything, just close the dialog in case of no.
            }
        });

        //Create and show the dialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
