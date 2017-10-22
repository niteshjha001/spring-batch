/*
package com.nitesh.springbatchdemo.configuration;

import com.nitesh.springbatchdemo.model.Student;
import com.nitesh.springbatchdemo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    public StudentService studentService;

    @GetMapping(name = "/student")
    public List<Student> getStudent() {
        List<Student> students = this.studentService.getStudentByAge(5);
        students.forEach(System.out::println);
        return students;

    }
}
*/
