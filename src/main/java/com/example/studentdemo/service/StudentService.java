package com.example.studentdemo.service;


import com.example.studentdemo.model.Student;
import java.util.Optional;
import java.util.List;

public interface StudentService {

    public List<Student> getStudents();

    public Optional<Student> getStudentById(int studentId);

    public Student addNewStudent(Student student);

    public Student updateStudent(Student student);

    public void deleteStudentById(int studentId);

    public void deleteAllStudents();

}
