CREATE TABLE IF NOT EXISTS lessons (id int PRIMARY KEY, name varchar(64), description varchar(255));
CREATE TABLE IF NOT EXISTS lesson_chords (lesson_id int, root varchar(8), extension varchar(8), PRIMARY KEY(lesson_id, root, extension), FOREIGN KEY(lesson_id) REFERENCES lessons(id));
INSERT OR IGNORE INTO lessons (id, name, description) VALUES (1, 'Easy Lesson', ''), (2, 'Medium Lesson', ''), (3, 'Hard Lesson', '');
INSERT OR IGNORE INTO lesson_chords (lesson_id, root, extension) VALUES (1, 'IV', 'Maj'), (1, 'V', 'Maj'), (1, 'VI', 'Min'), (2, 'III', 'Min'), (2, 'II', 'Min'), (2, 'VII', 'Dim'), (2, 'V', 'Seven'), (3, 'bVII', 'Maj'), (3, 'bVI', 'Maj'), (3, 'III', 'Seven');
