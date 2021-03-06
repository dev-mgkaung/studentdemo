package com.example.studentdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "teacher")
// To increase speed and save sql statement execution time.
@DynamicInsert
@DynamicUpdate
public class Teacher {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "teacher_id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private int age;
    @Column(name = "department")
    private String department;

    @OneToMany(targetEntity = Courses.class , cascade = CascadeType.ALL)
    @JoinColumn(name = "courseid_pk", referencedColumnName = "teacher_id")
    private List<Courses> coursesList;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<Courses> getCoursesList() {
        return coursesList;
    }

    public void setCoursesList(List<Courses> coursesList) {
        this.coursesList = coursesList;
    }
}
