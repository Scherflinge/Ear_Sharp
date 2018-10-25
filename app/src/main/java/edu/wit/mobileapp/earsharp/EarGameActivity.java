package edu.wit.mobileapp.earsharp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class EarGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ear_game);

        Bundle inBundle = this.getIntent().getExtras();
        // TODO Process bundle data

        setTitle(getString(R.string.ear_game));

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
}
