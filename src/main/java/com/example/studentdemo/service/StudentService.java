package com.example.studentdemo.service;


import com.example.studentdemo.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    public List<Student> getStudents();

    public Optional<Student> getStudentById(int studentId);

    public Student addNewStudent(Student student);

    public Student updateStudent(Student student);

    public void deleteStudentById(int studentId);

    public void deleteAllStudents();

    public Page<Student> getAllStudentsByPaging( Pageable pageable);

    public List<Student> findByStudentWithYear(String year);
}
