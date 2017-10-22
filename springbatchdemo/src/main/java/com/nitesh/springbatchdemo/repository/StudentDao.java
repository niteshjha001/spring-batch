package com.nitesh.springbatchdemo.repository;

import com.nitesh.springbatchdemo.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentDao extends CrudRepository<Student, Long> {

    public List<Student> getStudentByAgeIsGreaterThanEqual(int age);

}
