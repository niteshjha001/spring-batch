package com.nitesh.springbatchdemo.service;

import com.nitesh.springbatchdemo.model.Student;

import java.util.List;

public interface StudentService {
    public List<Student> getStudents();

    public List<Student> getStudentByAge(int age);
}
