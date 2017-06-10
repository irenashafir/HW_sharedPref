package shafir.irena.hw_sharedpref;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.security.PublicKey;

public class EditableNote extends AppCompatActivity{


    public static final int RQ_OK_CODE = 123;
    EditText etNewNote;
    ImageView ivSave;
    ImageView ivNotePad;
    int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editable_note);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etNewNote = (EditText) findViewById(R.id.etNewNote);
        ivSave = (ImageView) findViewById(R.id.ivSave);
        ivNotePad = (ImageView) findViewById(R.id.ivNotePad);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent editNote = getIntent();
        String noteText = editNote.getStringExtra("text");
        final Note note = editNote.getParcelableExtra("position");
        position = editNote.getIntExtra("num", 0);
        etNewNote.setText(noteText);

    }


    public void save(View v) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("text", etNewNote.getText().toString());
        returnIntent.putExtra("num", position);
        returnIntent.putExtras(returnIntent);

        if (getParent() == null){
            setResult(All_Notes.RESULT_OK, returnIntent);
        }
        else {
            getParent().setResult(All_Notes.RESULT_OK, returnIntent);
        }
        finish();
    }



}
