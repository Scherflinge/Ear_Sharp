
package edu.wit.mobileapp.earsharp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import edu.wit.mobileapp.earsharp.R;

public class GameSetupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_setup);

        Bundle inBundle = this.getIntent().getExtras();
        // TODO Process bundle data

        setTitle(getString(R.string.game_setup));

        Button btnEarGame = (Button)findViewById(R.id.button_startgame);
        btnEarGame.setOnClickListener((view) -> {
            Intent intent = new Intent();
            intent.setClass(GameSetupActivity.this, EarGameActivity.class);

            Bundle outBundle = new Bundle();
            // TODO Add Bundle Data Here

            intent.putExtras(outBundle);
            startActivity(intent);
        });

        Button btnBack = (Button)findViewById(R.id.back_button);
        btnBack.setOnClickListener((view) -> {
            Intent intent = new Intent();
            intent.setClass(GameSetupActivity.this, GameSelectActivity.class);

            Bundle outBundle = new Bundle();
            // TODO Add Bundle Data Here

            intent.putExtras(outBundle);
            startActivity(intent);
        });
    }
}
