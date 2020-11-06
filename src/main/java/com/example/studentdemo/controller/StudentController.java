package com.example.studentdemo.controller;

import com.example.studentdemo.model.Student;
import com.example.studentdemo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/v1/students")
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


    @RequestMapping(value= "paging", method= RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getAllStudentsByPaging(
            @RequestParam(required = false) String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "id,desc") String[] sort) {
        try {
            List<Student> students = new ArrayList<Student>();

            Pageable paging = PageRequest.of(page, size, Sort.by(sort));

            Page<Student> pageStus;

            pageStus = studentService.getAllStudentsByPaging( paging);

            students = pageStus.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("data", students);
            response.put("currentPage", pageStus.getNumber());
            response.put("totalItems", pageStus.getTotalElements());
            response.put("totalPages", pageStus.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
