package edu.wit.mobileapp.earsharp.music;

import java.util.List;

public interface LessonDAO {
    Lesson getLesson(int id);
    List<Lesson> getAllLessons();
}
