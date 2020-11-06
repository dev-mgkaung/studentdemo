package com.example.studentdemo.repository;

import com.example.studentdemo.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TeacherRepository  extends JpaRepository<Teacher, Integer> {

}
