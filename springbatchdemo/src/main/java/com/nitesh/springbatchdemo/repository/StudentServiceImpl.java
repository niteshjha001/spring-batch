package com.nitesh.springbatchdemo.repository;

import com.nitesh.springbatchdemo.model.Student;
import com.nitesh.springbatchdemo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentDao studentDao;

    @Autowired
    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public List<Student> getStudents() {

        List<Student> studentList = new ArrayList<>();
        studentDao.findAll().iterator().forEachRemaining(studentList::add);
        return studentList;
    }

    @Override
    public List<Student> getStudentByAge(int age) {
        List<Student> studentByAgeIsGreaterThanEqual = studentDao.getStudentByAgeIsGreaterThanEqual(5);

        return studentByAgeIsGreaterThanEqual;
    }

}
