package com.example.studentdemo.controller;

import com.example.studentdemo.model.Student;
import com.example.studentdemo.model.Teacher;
import com.example.studentdemo.service.TeacherService;
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
@RequestMapping("/api/v1/teachers")
public class TeacherController {

    @Autowired
    TeacherService teacherService;


    @RequestMapping(value= "all", method= RequestMethod.GET)
    public List<Teacher> getTeachers() {

        return teacherService.getTeachers();
    }

    @RequestMapping(value= "{id}", method= RequestMethod.GET)
    public Teacher getTeacherById(@PathVariable int id) throws Exception {

        Optional<Teacher> teacher =  teacherService.getTeacherById(id);
        if(!teacher.isPresent())
            throw new Exception("Could not find Teacher with id- " + id);

        return teacher.get();
    }

    @RequestMapping(value= "add", method= RequestMethod.POST)
    public Teacher createTeacher(@RequestBody Teacher newTeacher) {
        return teacherService.addNewTeacher(newTeacher);
    }

    @RequestMapping(value= "update/{id}", method= RequestMethod.PUT)
    public Teacher updateTeacher(@RequestBody Teacher updteacher, @PathVariable int id) throws Exception {

        Optional<Teacher> teacher =  teacherService.getTeacherById(id);
        if (!teacher.isPresent())
            throw new Exception("Could not find teacher with id- " + id);

        /* IMPORTANT - To prevent the overiding of the existing value of the variables in the database,
         * if that variable is not coming in the @RequestBody annotation object. */
        if(updteacher.getName() == null || updteacher.getName().isEmpty())
            updteacher.setName(teacher.get().getName());
        if(updteacher.getDepartment() == null || updteacher.getDepartment().isEmpty())
            updteacher.setDepartment(teacher.get().getDepartment());
        if(updteacher.getAge() == 0)
            updteacher.setAge(teacher.get().getAge());

        // Required for the "where" clause in the sql query template.
        updteacher.setId(id);
        return teacherService.updateTeacher(updteacher);
    }

    @RequestMapping(value= "delete/{id}", method= RequestMethod.DELETE)
    public void deleteTeacherById(@PathVariable int id) throws Exception {

        Optional<Teacher> teacher =  teacherService.getTeacherById(id);
        if(!teacher.isPresent())
            throw new Exception("Could not find teacher with id- " + id);

        teacherService.deleteTeacherById(id);
    }

    @RequestMapping(value= "deleteall", method= RequestMethod.DELETE)
    public void deleteAll() {
        teacherService.deleteAllTeachers();
    }

    @RequestMapping(value= "paging", method= RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getAllTeachersByPaging(
            @RequestParam(required = false) String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "id,desc") String[] sort) {
        try {
            List<Teacher> teachers = new ArrayList<Teacher>();

            Pageable paging = PageRequest.of(page, size);

            Page<Teacher> pageStus;

            pageStus = teacherService.getAllTeacherByPaging( paging);

            teachers = pageStus.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("data", teachers);
            response.put("currentPage", pageStus.getNumber());
            response.put("totalItems", pageStus.getTotalElements());
            response.put("totalPages", pageStus.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
