package ru.hogwarts.school.homework41.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.homework41.service.StudentService;
import ru.hogwarts.school.homework41.model.Faculty;
import ru.hogwarts.school.homework41.model.Student;

import java.util.List;
import java.util.Optional;

@RequestMapping("/student")
@RestController
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student newStudent = studentService.createStudent(student);
        return ResponseEntity.ok(newStudent);
    }

    @GetMapping("/find")
    public ResponseEntity<Student> getStudent(@RequestParam("id") Long id) {
        Optional<Student> optional = studentService.getStudent(id);
        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optional.get());
    }

    @PutMapping
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        Student updatedStudent = studentService.updateStudent(student.getId(), student);
        return  ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Student>> getStudentsByAge(@RequestParam("age") int age) {
        if (age < 0) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(studentService.getStudentsByAge(age));
    }

    @GetMapping
    public ResponseEntity<List<Student>> findByAgeBetween(@RequestParam ("age1")int min, @RequestParam ("age2")int max) {
        if (min < 0 || max < 0) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(studentService.findByAgeBetween(min, max));
    }

    @GetMapping("/{id}/faculty")
    public Faculty getFacultyById(@PathVariable Long id) {
        return studentService.getFacultyById(id);
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> countStudents() {
        return ResponseEntity.ok(studentService.countStudents());
    }

    @GetMapping("/averageAge")
    public ResponseEntity<Double> averageAge() {
        return ResponseEntity.ok(studentService.averageAge());
    }

    @GetMapping("/lastStudents")
    public ResponseEntity<List<Student>> getLastStudents(@RequestParam("number") int number) {
        return ResponseEntity.ok(studentService.getLastStudents(number));
    }
}
