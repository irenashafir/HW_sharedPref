package shafir.irena.hw_sharedpref;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by irena on 10/05/2017.
 */

public class Note implements Parcelable{

    int noteNumber;
    String content;

    public Note(int noteNumber, String content) {
        this.noteNumber = noteNumber;
        this.content = content;
    }

    protected Note(Parcel in) {
        noteNumber = in.readInt();
        content = in.readString();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    public int getNoteNumber() {
        return noteNumber;
    }

    public String getContent() {
        return content;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(noteNumber);
        dest.writeString(content);
    }


    public void setNoteNumber(int noteNumber) {
        this.noteNumber = noteNumber;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
