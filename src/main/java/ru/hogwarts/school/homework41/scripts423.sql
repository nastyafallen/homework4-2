SELECT student.name, student.age, faculty.name
FROM student LEFT JOIN faculty ON faculty.id = student.faculty_id;

SELECT s.name, s.age FROM student s INNER JOIN avatar a on s.id = a.student_id;