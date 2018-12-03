package edu.wit.mobileapp.earsharp.activities;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import edu.wit.mobileapp.earsharp.R;
import edu.wit.mobileapp.earsharp.music.IntervalChord;
import edu.wit.mobileapp.earsharp.music.Lesson;

public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.LessonViewHolder> {

    private List<Lesson> lessonList;

    private int selected = RecyclerView.NO_POSITION;

    public LessonAdapter(List<Lesson> lessonList) {
        this.lessonList = lessonList;
    }

    @NonNull
    @Override
    public LessonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.lesson_card, viewGroup, false);
        return new LessonViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LessonViewHolder viewHolder, int i) {
        Lesson lesson = lessonList.get(i);
        viewHolder.lesson = lesson;
        viewHolder.vTitle.setText(lesson.getName());
        viewHolder.vDescription.setText("Interval Chords:");

        StringBuilder builder = new StringBuilder();
        String delimiter = "";
        for (IntervalChord intervalChord : lesson.getIntervalChords()) {
            builder.append(delimiter);
            builder.append(intervalChord.toString());
            delimiter = "\n";
        }
        viewHolder.vIntervalChord.setText(builder.toString());

        View itemView = viewHolder.itemView;
        itemView.setBackgroundColor(itemView.getResources().getColor(
                selected == i ? R.color.cardview_lesson_selected : R.color.cardview_lesson));
    }

    @Override
    public int getItemCount() {
        return lessonList.size();
    }

    public Lesson getSelectedLesson() {
        if (selected == RecyclerView.NO_POSITION) {
            return null;
        }
        return lessonList.get(selected);
    }

    public class LessonViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        Lesson lesson;

        TextView vTitle;
        TextView vDescription;
        TextView vIntervalChord;

        public LessonViewHolder(@NonNull View v) {
            super(v);
            vTitle = (TextView)v.findViewById(R.id.title);
            vDescription = (TextView)v.findViewById(R.id.description);
            vIntervalChord = (TextView)v.findViewById(R.id.lesson_chords);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (getAdapterPosition() == RecyclerView.NO_POSITION) {
                return;
            }
            notifyItemChanged(selected);
            selected = getLayoutPosition();
            notifyItemChanged(selected);
        }
    }
}
