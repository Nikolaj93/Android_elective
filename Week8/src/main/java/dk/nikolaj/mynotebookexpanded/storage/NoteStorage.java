package dk.nikolaj.mynotebookexpanded.storage;

import java.util.ArrayList;

import dk.nikolaj.mynotebookexpanded.model.Note;

public class NoteStorage {

    private static ArrayList<Note> list;

    static {
        list = new ArrayList<>();
    }

    public static Note getNote(int index){
        return list.get(index);
    }

    public static int getLength(){
        return list.size();
    }

    public static ArrayList<Note> getList(){
        return list;
    }
}
