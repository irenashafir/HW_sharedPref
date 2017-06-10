package shafir.irena.hw_sharedpref;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class All_Notes extends AppCompatActivity {

    RecyclerView rvNotes;
    ArrayList<Note> myNoteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all__notes);

        Intent noteIntent = getIntent();
        myNoteList = noteIntent.getParcelableArrayListExtra("notesList");

        rvNotes = (RecyclerView) findViewById(R.id.rvNotes);

        NoteAdapter adapter = new NoteAdapter(this, myNoteList);
        rvNotes.setLayoutManager(new LinearLayoutManager(this));
        rvNotes.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 123) {
            if (resultCode == EditableNote.RQ_OK_CODE) {
                Intent getFromEdit = getIntent();
                String newNote = getFromEdit.getStringExtra("text");
                int noteNum = getFromEdit.getIntExtra("num", 0);
                Note note = getFromEdit.getParcelableExtra("note");

                myNoteList.set(noteNum, new Note(noteNum, newNote));
                Toast.makeText(this, "changes set", Toast.LENGTH_SHORT).show();
            } else if (resultCode == EditableNote.RESULT_CANCELED) {
                Toast.makeText(this, "no changes made", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);

        Intent editIntent = new Intent(this, EditableNote.class);
        startActivityForResult(editIntent, 0);
    }
}

