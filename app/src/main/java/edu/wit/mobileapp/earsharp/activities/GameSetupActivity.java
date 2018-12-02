
package edu.wit.mobileapp.earsharp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

        setTitle(getString(R.string.game_setup));

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.lesson_cards);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        lessonDAO = LessonDAOImpl.getInstance(getApplicationContext());
        List<Lesson> lessons = lessonDAO.getAllLessons();
        LessonAdapter adapter = new LessonAdapter(lessons);
        recyclerView.setAdapter(adapter);

        Button btnStartGame = (Button)findViewById(R.id.button_startgame);
        btnStartGame.setOnClickListener((view) -> {
            Lesson lesson = adapter.getSelectedLesson();
            if (lesson == null) {
                return;
            }

            Intent intent = new Intent();
            intent.setClass(GameSetupActivity.this, EarGameActivity.class);

            Bundle outBundle = new Bundle();
            outBundle.putInt("lesson_id", lesson.getId());

            intent.putExtras(outBundle);
            startActivity(intent);
        });
    }
}
