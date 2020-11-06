package com.example.studentdemo.repository;

import com.example.studentdemo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Query("select * from student where year = ?1")
    List<Student> findByStudentWithYear(String year);

}
