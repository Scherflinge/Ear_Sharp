package edu.wit.mobileapp.earsharp.music;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.wit.mobileapp.earsharp.R;

import static android.content.Context.MODE_PRIVATE;

public class LessonDAOImpl implements LessonDAO {

    private static final String DATABASE_NAME = "earsharp.db";
    private static final String[] LESSON_COLS = {"id", "name", "description"};
    private static final String[] LESSON_CHORDS_COLS = {"lesson_id", "root", "extension"};

    private static LessonDAOImpl instance;
    private static boolean initialized = false;

    private SQLiteDatabase db;

    public static LessonDAOImpl getInstance(Context context) {
        if (initialized) {
            return instance;
        }
        instance = new LessonDAOImpl();
        instance.init(context);
        initialized = true;
        return instance;
    }

    private LessonDAOImpl() {}

    private void init(Context context) {
        db = context.openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE,null);

        try {
            InputStream is = context.getResources().openRawResource(R.raw.earsharp);
            List<String> sqls = convertStreamToStringList(is);
            for (String sql : sqls) {
                db.execSQL(sql);
            }
            is.close();
        } catch (IOException e) {
            Log.v("EarSharp", e.getMessage());
        }
    }

    @Override
    public Lesson getLesson(int id) {
        Lesson lesson = new Lesson();
        Cursor cursor = db.query("lessons", LESSON_COLS, "id = ?",
                new String[] {Integer.toString(id)}, null, null, null);
        if (cursor.getCount() == 0) {
            return null;
        }
        cursor.moveToFirst();
        lesson.setId(id);
        lesson.setName(cursor.getString(cursor.getColumnIndex("name")));
        lesson.setDescription(cursor.getString(cursor.getColumnIndex("description")));
        cursor.close();

        lesson.setIntervalChords(getLessonChords(id));

        return lesson;
    }

    @Override
    public List<Lesson> getAllLessons() {
        List<Lesson> lessons = new ArrayList<>();
        Cursor cursor = db.query("lessons", LESSON_COLS, null, null,
                null, null, "id");
        while (cursor.moveToNext()) {
            Lesson lesson = new Lesson();
            lesson.setId(cursor.getInt(cursor.getColumnIndex("id")));
            lesson.setName(cursor.getString(cursor.getColumnIndex("name")));
            lesson.setDescription(
                    cursor.getString(cursor.getColumnIndex("description")));
            lesson.setIntervalChords(getLessonChords(lesson.getId()));

            lessons.add(lesson);
        }
        cursor.close();
        return lessons;
    }

    private List<IntervalChord> getLessonChords(int lesson_id) {
        List<IntervalChord> intervalChords = new ArrayList<>();
        Cursor cursor = db.query("lesson_chords", LESSON_CHORDS_COLS,"lesson_id = ?",
                new String[] {Integer.toString(lesson_id)}, null, null,null);
        while (cursor.moveToNext()) {
            Interval root = Interval.valueOf(
                    cursor.getString(cursor.getColumnIndex("root")));
            Extension extension = Extension.valueOf(
                    cursor.getString(cursor.getColumnIndex("extension")));
            intervalChords.add(new IntervalChord(root, extension));
        }
        cursor.close();
        return intervalChords;
    }

    private static List<String> convertStreamToStringList(InputStream is) throws IOException {
        List<String> out = new ArrayList<>();
        Scanner scanner = new Scanner(is);
        while (scanner.hasNextLine()) {
            out.add(scanner.nextLine());
        }
        return out;
    }
}
