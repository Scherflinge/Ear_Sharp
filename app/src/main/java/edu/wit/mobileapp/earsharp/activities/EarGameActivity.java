package edu.wit.mobileapp.earsharp.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.wit.mobileapp.earsharp.R;
import edu.wit.mobileapp.earsharp.game.Difficulty;
import edu.wit.mobileapp.earsharp.game.EarGame;
import edu.wit.mobileapp.earsharp.game.PlayNoteListener;
import edu.wit.mobileapp.earsharp.music.Chord;
import edu.wit.mobileapp.earsharp.music.Extension;
import edu.wit.mobileapp.earsharp.music.Interval;
import edu.wit.mobileapp.earsharp.music.IntervalChord;
import edu.wit.mobileapp.earsharp.music.Lesson;
import edu.wit.mobileapp.earsharp.music.LessonDAO;
import edu.wit.mobileapp.earsharp.music.LessonDAOImpl;
import edu.wit.mobileapp.earsharp.music.Note_Enum;

public class EarGameActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener,
        PlayNoteListener {

    private LessonDAO lessonDAO;

    // Controls
    private Button btnReplay;
    private Spinner spnInterval;
    private List<ImageView> imageViews;
    private Button btnTestStart;
    private Button btnGuess;
    private ImageView correctImage;
    // Game
    private EarGame game;
    private IntervalChord selectedInterval;
    private TextView roundCounter;
    private Lesson lesson;
    private List<Chord> testLesson = Arrays.asList(
            new Chord(Note_Enum.A1, Extension.None),
            new Chord(Note_Enum.B1, Extension.None),
            new Chord(Note_Enum.C1, Extension.None),
            new Chord(Note_Enum.D1, Extension.None),
            new Chord(Note_Enum.E1, Extension.None),
            new Chord(Note_Enum.F1, Extension.None),
            new Chord(Note_Enum.G1, Extension.None),
            new Chord(Note_Enum.A2, Extension.None),
            new Chord(Note_Enum.B2, Extension.None),
            new Chord(Note_Enum.C2, Extension.None),
            new Chord(Note_Enum.D2, Extension.None),
            new Chord(Note_Enum.E2, Extension.None),
            new Chord(Note_Enum.F2, Extension.None),
            new Chord(Note_Enum.G2, Extension.None)
    );
    private List<Interval> testIntervals = Arrays.asList(Interval.I, Interval.II, Interval.III,
            Interval.IV, Interval.V, Interval.VI, Interval.VII);

    private List<IntervalChord> testIntervalChords = Arrays.asList(
            new IntervalChord(Interval.II, Extension.Min),
            new IntervalChord(Interval.III, Extension.Min),
            new IntervalChord(Interval.IV, Extension.Maj),
            new IntervalChord(Interval.V, Extension.Maj),
            new IntervalChord(Interval.VI, Extension.Min),
            new IntervalChord(Interval.VII, Extension.Dim)
            );
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ear_game);

        setTitle(getString(R.string.ear_game));

        Bundle inBundle = this.getIntent().getExtras();
        int lessonId = inBundle.getInt("lesson_id");

        lessonDAO = LessonDAOImpl.getInstance(this);
        lesson = lessonDAO.getLesson(lessonId);

        if (lesson == null) {
            Log.v("EarSharp", "Could not load lesson with id=" + lessonId);
        } else {
            Log.v("EarSharp", "Loaded lesson with id=" + lessonId);
        }

        game = new EarGame(this, lesson.getIntervalChords(), Difficulty.Easy);

        correctImage = findViewById(R.id.correct_image);
        correctImage.setVisibility(View.INVISIBLE);
        imageViews = new ArrayList<>();
        imageViews.add((ImageView)findViewById(R.id.image_light_1));
        imageViews.add((ImageView)findViewById(R.id.image_light_2));
        imageViews.add((ImageView)findViewById(R.id.image_light_3));
        imageViews.add((ImageView)findViewById(R.id.image_light_4));
        imageViews.add((ImageView)findViewById(R.id.image_light_5));
        imageViews.add((ImageView)findViewById(R.id.image_light_6));
        imageViews.add((ImageView)findViewById(R.id.image_light_7));
        imageViews.add((ImageView)findViewById(R.id.image_light_8));

        btnReplay = (Button)findViewById(R.id.button_replay);
        btnReplay.setOnClickListener((view) -> {
            btnReplay.setEnabled(false);
            game.playChord();
        });

        btnTestStart = (Button)findViewById(R.id.button_teststart);
        btnTestStart.setOnClickListener((view) -> {
            startNewRound();
        });

        roundCounter = findViewById(R.id.num_correct);
        game.setMaxRounds(15);

        roundCounter.setText("Round "+game.getCurrentRound()+"/"+game.getMaxRounds());

        spnInterval = (Spinner)findViewById(R.id.spinner_intervals);

        ArrayAdapter<IntervalChord> spinnerAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, lesson.getIntervalChords());
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnInterval.setAdapter(spinnerAdapter);
        spnInterval.setOnItemSelectedListener(this);

        btnTestStart.setEnabled(false);

        btnGuess = (Button)findViewById(R.id.button_guess);
        btnGuess.setOnClickListener((view) -> {
            boolean correct = game.checkInterval(selectedInterval);
            endRound();
            if(correct){
                displayCorrect();
            }
            else{
                displayFalse();
            }


//            Intent intent = new Intent();
//            intent.setClass(EarGameActivity.this, ResultsScreenActivity.class);
//
//            Bundle outBundle = new Bundle();
//            // TODO Add Bundle Data Here
//
//            intent.putExtras(outBundle);
//            startActivity(intent);

        });
        game.startNewRound();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        selectedInterval = (IntervalChord)parent.getItemAtPosition(pos);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        selectedInterval = null;
    }

    @Override
    public void highlightNotes(List<Integer> intervals) {
        for (Integer interval : intervals) {
            imageViews.get(interval).setColorFilter(getResources().getColor(R.color.colorHighlightNote));
        }
    }

    @Override
    public void unhighlightNotes() {
        for (ImageView imageView : imageViews) {
            imageView.clearColorFilter();
        }

    }

    @Override
    public void donePlayingNotes() {
        btnReplay.setEnabled(true);
    }

    @Override
    public void startNewRound() {
        game.startNewRound();
        correctImage.setVisibility(View.INVISIBLE);
        btnGuess.setEnabled(true);
        btnTestStart.setEnabled(false);
        game.playChord();
        roundCounter.setText(game.getCurrentRound()+"/"+game.getMaxRounds());
    }

    @Override
    public void endRound() {
        btnGuess.setEnabled(false);
        btnTestStart.setEnabled(true);
    }

    private void displayFalse(){
        correctImage.setImageResource(R.drawable.x);
        correctImage.setVisibility(View.VISIBLE);
    }

    private void displayCorrect(){
        correctImage.setImageResource(R.drawable.check);
        correctImage.setVisibility(View.VISIBLE);
    }

    @Override
    public void end(){
        Bundle b = new Bundle();
        Intent i = new Intent();
        i.setClass(EarGameActivity.this, ResultsScreenActivity.class);
        b.putString("results","You got " + game.getNumCorrect() + "/" + game.getMaxRounds() + " correct");
        b.putString("comments", game.getComments());
        i.putExtras(b);
        startActivity(i);
        finish();
    }
}
