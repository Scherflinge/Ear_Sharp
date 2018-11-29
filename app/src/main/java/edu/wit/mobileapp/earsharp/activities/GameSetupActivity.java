
package edu.wit.mobileapp.earsharp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import java.util.List;

import edu.wit.mobileapp.earsharp.R;
import edu.wit.mobileapp.earsharp.music.Lesson;
import edu.wit.mobileapp.earsharp.music.LessonDAO;
import edu.wit.mobileapp.earsharp.music.LessonDAOImpl;

public class GameSetupActivity extends AppCompatActivity {

    private LessonDAO lessonDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_setup);

        lessonDAO = LessonDAOImpl.getInstance(getApplicationContext());

        List<Lesson> lessons = lessonDAO.getAllLessons();

        Bundle inBundle = this.getIntent().getExtras();
        // TODO Process bundle data

        setTitle(getString(R.string.game_setup));

        Button btnStartGame = (Button)findViewById(R.id.button_startgame);
        btnStartGame.setOnClickListener((view) -> {
            Intent intent = new Intent();
            intent.setClass(GameSetupActivity.this, EarGameActivity.class);

            Bundle outBundle = new Bundle();
            // TODO Add Bundle Data Here

            intent.putExtras(outBundle);
            startActivity(intent);
        });
    }
}
