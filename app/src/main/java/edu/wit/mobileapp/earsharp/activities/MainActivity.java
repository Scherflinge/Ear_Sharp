package edu.wit.mobileapp.earsharp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import edu.wit.mobileapp.earsharp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle inBundle = this.getIntent().getExtras();
        // TODO Process Bundle Here

        Button btnGameSelect = (Button)findViewById(R.id.button_gameselect);
        btnGameSelect.setOnClickListener((view) -> {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, GameSelectActivity.class);

            Bundle outBundle = new Bundle();
            // TODO Add Bundle Data Here

            intent.putExtras(outBundle);
            startActivity(intent);
        });
    }
}
