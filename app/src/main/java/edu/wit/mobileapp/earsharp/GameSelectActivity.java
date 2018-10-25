package edu.wit.mobileapp.earsharp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class GameSelectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_select);

        Bundle inBundle = this.getIntent().getExtras();
        // TODO Process bundle data

        setTitle(getString(R.string.game_select));

        Button btnEarGame = (Button)findViewById(R.id.button_eargame);
        btnEarGame.setOnClickListener((view) -> {
            Intent intent = new Intent();
            intent.setClass(GameSelectActivity.this, GameSetupActivity.class);

            Bundle outBundle = new Bundle();
            // TODO Add Bundle Data Here

            intent.putExtras(outBundle);
            startActivity(intent);
        });

        Button btnBack = (Button)findViewById(R.id.back_button);
        btnBack.setOnClickListener((view) -> {
            Intent intent = new Intent();
            intent.setClass(GameSelectActivity.this, MainActivity.class);

            Bundle outBundle = new Bundle();
            // TODO Add Bundle Data Here

            intent.putExtras(outBundle);
            startActivity(intent);
        });
    }
}
