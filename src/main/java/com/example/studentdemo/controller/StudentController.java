package com.example.studentdemo.controller;

import com.example.studentdemo.exception.StudentNotFoundException;
import com.example.studentdemo.model.Student;
import com.example.studentdemo.repository.StudentRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("students")
public class StudentController {

    private final StudentRepository repository;

    public StudentController(StudentRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Iterable<Student> getStudents() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    public Student getStudent(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(StudentNotFoundException::new);
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return repository.save(student);
    }

    @PutMapping("{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student student) {
        Student studentToUpdate = repository.findById(id).orElseThrow(StudentNotFoundException::new);

        studentToUpdate.setFirstName(student.getFirstName());
        studentToUpdate.setLastName(student.getLastName());
        studentToUpdate.setYear(student.getYear());

        return repository.save(studentToUpdate);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        repository.findById(id).orElseThrow(StudentNotFoundException::new);
        repository.deleteById(id);
    }
}
