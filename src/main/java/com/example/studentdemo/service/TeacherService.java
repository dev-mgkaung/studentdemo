package com.example.studentdemo.service;

import com.example.studentdemo.model.Student;
import com.example.studentdemo.model.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.List;

public interface TeacherService {

        public List<Teacher> getTeachers();
        public Optional<Teacher> getTeacherById(int teacherId);
        public Teacher addNewTeacher(Teacher teacher);
        public Teacher updateTeacher(Teacher teacher);
        public void deleteTeacherById(int teacherId);
        public void deleteAllTeachers();
        public Page<Teacher> getAllTeacherByPaging(Pageable pageable);
}
