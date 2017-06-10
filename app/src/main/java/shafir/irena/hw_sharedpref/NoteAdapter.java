package shafir.irena.hw_sharedpref;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.SupportActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by irena on 10/05/2017.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder>
        implements View.OnClickListener{


    private LayoutInflater inflater;
    private Context context;
    private ArrayList<Note> notes;
    View.OnClickListener listener;

    public NoteAdapter(Context context, ArrayList<Note> notes) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.notes = notes;
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.note, parent, false);
        return new NoteViewHolder(v);
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        Note newNote = notes.get(position);
        holder.tvNumber.setText(String.valueOf(newNote.noteNumber));
        holder.tvNoteText.setText(newNote.content);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }


    @Override
    public void onClick(View v) {

    }


    public class NoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
            EditNoteFragment.OnNoteChangeListener {

        TextView tvNumber;
        TextView tvNoteText;
        ImageView ivEdit;

        public NoteViewHolder(View itemView) {
            super(itemView);

            tvNumber = (TextView) itemView.findViewById(R.id.tvNumber);
            tvNoteText = (TextView) itemView.findViewById(R.id.tvNoteText);
            ivEdit = (ImageView) itemView.findViewById(R.id.ivEdit);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int position = getAdapterPosition();
                    Note note = notes.get(position);

                    Intent adapterIntent = new Intent(context, EditableNote.class);
                    adapterIntent.putExtra("text", tvNoteText.getText().toString());
                    adapterIntent.putExtra("position", note );
                    adapterIntent.putExtra("num", Integer.valueOf(tvNumber.getText().toString()));
                    if (adapterIntent.resolveActivity(context.getPackageManager()) != null){
                        context.startActivity(adapterIntent);
                    }
                }
            });
        }


        @Override
        public void onClick(View v) {


        }

        @Override
        public void OnNoteChange(String noteText, int position) {
            tvNoteText.setText(noteText);
            tvNumber.setText(String.valueOf(position));
        }
    }




}
