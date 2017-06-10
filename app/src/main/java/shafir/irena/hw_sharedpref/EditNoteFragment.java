package shafir.irena.hw_sharedpref;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditNoteFragment extends DialogFragment implements View.OnClickListener {

    EditText etNewNote;
    ImageView ivSave;
    ImageView ivNotePad;
    OnNoteChangeListener listener;
    int position;


    public interface OnNoteChangeListener{
        public void OnNoteChange(String noteText, int position);
    }

    public EditNoteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_edit_note, container, false);

        etNewNote = (EditText) v.findViewById(R.id.etNewNote);
        ivSave = (ImageView) v.findViewById(R.id.ivSave);
        ivNotePad = (ImageView) v.findViewById(R.id.ivNotePad);

        OnNoteChangeListener activity = (OnNoteChangeListener) getActivity();
        activity.OnNoteChange(etNewNote.getText().toString(), position);

        ivSave.setOnClickListener(this);
        return v;
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.ivSave:
                String note = etNewNote.getText().toString();
                listener.OnNoteChange(note, position);
                break;
        }
        dismiss();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnNoteChangeListener){
            listener = (OnNoteChangeListener)context;
        }
        else throw new RuntimeException("must implement OnNoteChangeListener");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener=null;
    }
}
