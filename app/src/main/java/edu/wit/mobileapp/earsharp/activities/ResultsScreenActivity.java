package edu.wit.mobileapp.earsharp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import edu.wit.mobileapp.earsharp.R;

public class ResultsScreenActivity extends AppCompatActivity {

    TextView resultBox;
    TextView commentBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_screen);

        Bundle inBundle = this.getIntent().getExtras();
        // TODO Process bundle data

        setTitle(getString(R.string.results));

        resultBox = findViewById(R.id.results_box);
        commentBox = findViewById(R.id.comments_box);


        String comments = "it went wrong", results = "it went wrong";

        if(inBundle.getString("results") != null){

            results = inBundle.getString("results");
        }
        if(inBundle.getString("comments")!=null){
            comments = inBundle.getString("comments");
        }

        if(results != null){
            resultBox.setText(results);
        }
        if(comments != null){
            commentBox.setText(comments);
        }
    }
}
