package com.nitesh.springbatchdemo.configuration;

import com.nitesh.springbatchdemo.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class StudentRowMapper implements RowMapper<Student> {

    @Override
    public Student mapRow(ResultSet resultSet, int i) throws SQLException {


        Student student = new Student();
        student.setId(resultSet.getLong("id"));
        student.setFirstName(resultSet.getString("firstName"));
        student.setLastName(resultSet.getString("lastName"));
        student.setAge(resultSet.getInt("age"));
        student.setHeight(resultSet.getInt("height"));
        student.setNationality(resultSet.getString("nationality"));

        log.info("Student id :::" +student.getId());
        return student;
    }
}
