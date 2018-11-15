package edu.wit.mobileapp.earsharp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.wit.mobileapp.earsharp.R;
import edu.wit.mobileapp.earsharp.game.Difficulty;
import edu.wit.mobileapp.earsharp.game.EarGame;
import edu.wit.mobileapp.earsharp.game.PlayNoteListener;
import edu.wit.mobileapp.earsharp.music.Chord;
import edu.wit.mobileapp.earsharp.music.Extension;
import edu.wit.mobileapp.earsharp.music.Note_Enum;

public class EarGameActivity extends AppCompatActivity implements PlayNoteListener {

    EarGame game;
    List<Chord> cmaj = Arrays.asList(
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ear_game);

        Bundle inBundle = this.getIntent().getExtras();
        // TODO Process bundle data

        setTitle(getString(R.string.ear_game));

        game = new EarGame(this, cmaj, Difficulty.Easy);

        Button btnEarGame = (Button)findViewById(R.id.button_results);
        btnEarGame.setOnClickListener((view) -> {
            Intent intent = new Intent();
            intent.setClass(EarGameActivity.this, ResultsScreenActivity.class);

            Bundle outBundle = new Bundle();
            // TODO Add Bundle Data Here

            intent.putExtras(outBundle);
            startActivity(intent);
        });

        Button btnBack = (Button)findViewById(R.id.back_button);
        btnBack.setOnClickListener((view) -> {
            Intent intent = new Intent();
            intent.setClass(EarGameActivity.this, GameSetupActivity.class);

            Bundle outBundle = new Bundle();
            // TODO Add Bundle Data Here

            intent.putExtras(outBundle);
            startActivity(intent);
        });
    }

    @Override
    public void highlightNotes(List<Integer> intervalList) {

    }

    @Override
    public void unhighlightNotes() {

    }

    @Override
    public void donePlayingNotes() {

    }
}
