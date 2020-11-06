package com.example.studentdemo.service.impl;

import com.example.studentdemo.model.Student;
import com.example.studentdemo.repository.StudentRepository;
import com.example.studentdemo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public List<Student> getStudents() {
        return  studentRepository.findAll();
    }

    @Override
    public Optional<Student> getStudentById(int studentId) {
        return studentRepository.findById(studentId);
    }

    @Override
    public Student addNewStudent(Student student) {
        return  studentRepository.save(student);
    }

    @Override
    public Student updateStudent(Student student) {
        return  studentRepository.save(student);
    }

    @Override
    public void deleteStudentById(int studentId) {
        studentRepository.deleteById(studentId);
    }

    @Override
    public void deleteAllStudents() {
        studentRepository.deleteAll();
    }
}
