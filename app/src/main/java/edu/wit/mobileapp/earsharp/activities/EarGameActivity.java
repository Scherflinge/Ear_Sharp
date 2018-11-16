package edu.wit.mobileapp.earsharp.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

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
import edu.wit.mobileapp.earsharp.music.Note_Enum;

public class EarGameActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener,
        PlayNoteListener {

    // Controls
    private Button btnReplay;
    private Spinner spnInterval;
    private List<ImageView> imageViews;

    // Game
    private EarGame game;
    private Interval selectedInterval;
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

        Bundle inBundle = this.getIntent().getExtras();
        // TODO Process bundle data

        setTitle(getString(R.string.ear_game));

        game = new EarGame(this, testIntervalChords, Difficulty.Easy);
//        game.startNewRound();

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

        Button btnTestStart = (Button)findViewById(R.id.button_teststart);
        btnTestStart.setOnClickListener((view) -> {
            game.startNewRound();
        });

        spnInterval = (Spinner)findViewById(R.id.spinner_intervals);
        ArrayAdapter<Interval> spinnerAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, testIntervals);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnInterval.setAdapter(spinnerAdapter);
        spnInterval.setOnItemSelectedListener(this);

        Button btnGuess = (Button)findViewById(R.id.button_guess);
        btnGuess.setOnClickListener((view) -> {
            game.checkInterval(selectedInterval);

//            Intent intent = new Intent();
//            intent.setClass(EarGameActivity.this, ResultsScreenActivity.class);
//
//            Bundle outBundle = new Bundle();
//            // TODO Add Bundle Data Here
//
//            intent.putExtras(outBundle);
//            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        selectedInterval = (Interval)parent.getItemAtPosition(pos);
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
}
