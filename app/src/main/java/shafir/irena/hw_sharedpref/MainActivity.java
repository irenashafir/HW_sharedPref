package shafir.irena.hw_sharedpref;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TextWatcher {

    EditText etNoteText;
    TextView tvNum;
    ImageView noteImage;
    ImageView newImage;
    Button btnBack;
    Button btnNext;
    public SharedPreferences prefs;
    int noteNum = 1;
    FloatingActionButton fab;
    Button btnSave;
    String note ="note";
    int totalNoteCount = 1;
    ArrayList<Note> notes = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null){
            noteNum = savedInstanceState.getInt("noteNum");

        }

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etNoteText = (EditText) findViewById(R.id.etNoteText);
        tvNum = (TextView) findViewById(R.id.tvNum);
        noteImage = (ImageView) findViewById(R.id.noteImage);
        newImage = (ImageView) findViewById(R.id.newImage);
        btnBack = (Button) findViewById(R.id.btnBack);
        btnNext = (Button) findViewById(R.id.btnNext);
        btnSave = (Button) findViewById(R.id.btnSave);

        etNoteText.addTextChangedListener(this);

        prefs = getSharedPreferences("notes" , MODE_PRIVATE);

        load();
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id){
            case R.id.view_all_notes:
                Intent intent = new Intent(this, All_Notes.class);
                  intent.putExtra("notesList", notes);
                if (intent.resolveActivity(getPackageManager()) != null){
                    startActivity(intent);
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        save();
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    private void save() {
        SharedPreferences.Editor edit = prefs.edit();
        edit.putString(note+noteNum, getNoteText());
        edit.apply();
    }

    @NonNull
    private String getNoteText() {
        return etNoteText.getText().toString();
    }

    private void load() {
        String note = prefs.getString(tvNum.getText().toString(), "");
        etNoteText.setText(note);
        int noteNumber = prefs.getInt("noteNum", noteNum);
        tvNum.setText(String.valueOf(noteNumber));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("notes", "onSave");
        outState.putInt("noteNum", noteNum);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d("notes", "onRestore");
        noteNum = savedInstanceState.getInt("noteNum");
    }


    public void newNote(View view) {
        if (getNoteText()!= null){
            final AlertDialog.Builder build = new AlertDialog.Builder(this);
            build.setTitle("do you want to save the current note?");
            build.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    save();
                    noteNum = totalNoteCount +1;
                    tvNum.setText(String.valueOf(noteNum));
                    etNoteText.setText(null);
                    Toast.makeText(MainActivity.this, "saved", Toast.LENGTH_SHORT).show();
                }
            });
            build.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    tvNum.setText(String.valueOf(noteNum));
                    etNoteText.setText(null);
                }
            });
            build.show();
        }
        else{
            Toast.makeText(this, "note ready", Toast.LENGTH_SHORT).show();
        }

    }

    public void saveNote(View view) {
        if (getNoteText().isEmpty()) {
            Toast.makeText(this, "pls insert note before saving", Toast.LENGTH_SHORT).show();
        } else
            save();
        noteNum++;
        totalNoteCount++;
        notes.add(new Note(noteNum, getNoteText()));
        tvNum.setText(String.valueOf(noteNum));
        etNoteText.setText(null);
    }


    public void back(View view) {
        if (noteNum == 1) {
            noteNum = totalNoteCount;
            String textNote = prefs.getString(note + noteNum, "");
            etNoteText.setText(textNote);
            tvNum.setText(String.valueOf(noteNum));
        } else {
            noteNum--;
            String textNote = prefs.getString(note + noteNum, "");
            etNoteText.setText(textNote);
            tvNum.setText(String.valueOf(noteNum));
        }
    }

    public void nextNote(View view) {

        if (noteNum == totalNoteCount) {
            noteNum = 1;
            String textNote = prefs.getString(note + noteNum, "");
            etNoteText.setText(textNote);
            tvNum.setText(String.valueOf(noteNum));

        } else {
            noteNum++;
            String textNote = prefs.getString(note + noteNum, "");
            etNoteText.setText(textNote);
            tvNum.setText(String.valueOf(noteNum));
        }
    }





}
