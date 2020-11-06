package com.example.studentdemo.controller;

import com.example.studentdemo.exception.StudentNotFoundException;
import com.example.studentdemo.model.Student;
import com.example.studentdemo.model.Teacher;
import com.example.studentdemo.repository.StudentRepository;
import com.example.studentdemo.service.StudentService;
import com.example.studentdemo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("students")
public class StudentController {

    @Autowired
    StudentService studentService;


    @RequestMapping(value= "all", method= RequestMethod.GET)
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @RequestMapping(value= "{id}", method= RequestMethod.GET)
    public Student getStudentById(@PathVariable int id) throws Exception {

        Optional<Student> student =  studentService.getStudentById(id);
        if(!student.isPresent())
            throw new Exception("Could not find student with id- " + id);

        return student.get();
    }

    @RequestMapping(value= "add", method= RequestMethod.POST)
    public Student createStudent(@RequestBody Student newStudent) {
        return studentService.addNewStudent(newStudent);
    }

    @RequestMapping(value= "update/{id}", method= RequestMethod.PUT)
    public Student updateStudent(@RequestBody Student updStudent, @PathVariable int id) throws Exception {

        Optional<Student> student =  studentService.getStudentById(id);
        if (!student.isPresent())
            throw new Exception("Could not find student with id- " + id);

        if(updStudent.getFirstName() == null || updStudent.getFirstName().isEmpty())
            updStudent.setFirstName(student.get().getFirstName());

        if(updStudent.getLastName() == null || updStudent.getLastName().isEmpty())
            updStudent.setLastName(student.get().getLastName());

        if(updStudent.getYear() == null || updStudent.getYear().isEmpty())
            updStudent.setYear(student.get().getYear());

        // Required for the "where" clause in the sql query template.
        updStudent.setId(id);
        return studentService.updateStudent(updStudent);
    }

    @RequestMapping(value= "delete/{id}", method= RequestMethod.DELETE)
    public void deleteTeacherById(@PathVariable int id) throws Exception {
        System.out.println(this.getClass().getSimpleName() + " - Delete teacher by id is invoked.");

        Optional<Student> student =  studentService.getStudentById(id);
        if(!student.isPresent())
            throw new Exception("Could not find student with id- " + id);

        studentService.deleteStudentById(id);
    }

    @RequestMapping(value= "deleteall", method= RequestMethod.DELETE)
    public void deleteAll() {
        studentService.deleteAllStudents();
    }
}
