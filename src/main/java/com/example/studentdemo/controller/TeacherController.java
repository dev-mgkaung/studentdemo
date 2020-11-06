package com.example.studentdemo.controller;

import com.example.studentdemo.model.Teacher;
import com.example.studentdemo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/teachers")
public class TeacherController {

    @Autowired
    TeacherService teacherService;


    @RequestMapping(value= "all", method= RequestMethod.GET)
    public List<Teacher> getTeachers() {
        System.out.println(this.getClass().getSimpleName() + " - Get all Teachers service is invoked.");
        return teacherService.getTeachers();
    }

    @RequestMapping(value= "{id}", method= RequestMethod.GET)
    public Teacher getTeacherById(@PathVariable int id) throws Exception {
        System.out.println(this.getClass().getSimpleName() + " - Get Teachers details by id is invoked.");

        Optional<Teacher> teacher =  teacherService.getTeacherById(id);
        if(!teacher.isPresent())
            throw new Exception("Could not find Teacher with id- " + id);

        return teacher.get();
    }

    @RequestMapping(value= "add", method= RequestMethod.POST)
    public Teacher createTeacher(@RequestBody Teacher newTeacher) {
        System.out.println(this.getClass().getSimpleName() + " - Create new teacher method is invoked.");
        return teacherService.addNewTeacher(newTeacher);
    }

    @RequestMapping(value= "update/{id}", method= RequestMethod.PUT)
    public Teacher updateTeacher(@RequestBody Teacher updteacher, @PathVariable int id) throws Exception {
        System.out.println(this.getClass().getSimpleName() + " - Update teacher details by id is invoked.");

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
        System.out.println(this.getClass().getSimpleName() + " - Delete teacher by id is invoked.");

        Optional<Teacher> teacher =  teacherService.getTeacherById(id);
        if(!teacher.isPresent())
            throw new Exception("Could not find teacher with id- " + id);

        teacherService.deleteTeacherById(id);
    }

    @RequestMapping(value= "deleteall", method= RequestMethod.DELETE)
    public void deleteAll() {
        System.out.println(this.getClass().getSimpleName() + " - Delete all teacher is invoked.");
        teacherService.deleteAllTeachers();
    }
}
