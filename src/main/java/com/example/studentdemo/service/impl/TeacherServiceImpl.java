package com.example.studentdemo.service.impl;

import com.example.studentdemo.model.Teacher;
import com.example.studentdemo.repository.TeacherRepository;
import com.example.studentdemo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    TeacherRepository  teacherRepository;

    @Override
    public List<Teacher> getTeachers() {
        return  teacherRepository.findAll();
    }

    @Override
    public Optional<Teacher> getTeacherById(int teacherId) {
        return teacherRepository.findById(teacherId);
    }

    @Override
    public Teacher addNewTeacher(Teacher teacher) {
         return  teacherRepository.save(teacher);
    }

    @Override
    public Teacher updateTeacher(Teacher teacher) {
        return  teacherRepository.save(teacher);
    }

    @Override
    public void deleteTeacherById(int teacherId) {
       teacherRepository.deleteById(teacherId);
    }

    @Override
    public void deleteAllTeachers() {
      teacherRepository.deleteAll();
    }

    @Override
    public Page<Teacher> getAllTeacherByPaging(Pageable pageable) {
        return  teacherRepository.findAll(pageable);
    }
}
