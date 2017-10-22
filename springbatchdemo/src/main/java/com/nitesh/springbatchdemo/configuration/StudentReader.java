package com.nitesh.springbatchdemo.configuration;

import com.nitesh.springbatchdemo.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class StudentReader implements ItemReader<Student> {


    private List<Student> students = new ArrayList<>();
    int studentIndex = 0;

    public StudentReader() {
        initialize();
    }


    private void initialize() {
        Student student1 = getStudent(1l, 23, "Nitesh", "Jha", 8, "Indian");
        Student student2 = getStudent(2l, 23, "Nitesh", "Jha", 8, "Indian");
        Student student3 = getStudent(3l, 23, "Nitesh", "Jha", 8, "Indian");
        Student student4 = getStudent(4l, 23, "Nitesh", "Jha", 8, "Indian");
        Student student5 = getStudent(5l, 23, "Nitesh", "Jha", 8, "Indian");
        Student student6 = getStudent(6l, 23, "Nitesh", "Jha", 8, "Indian");

        students.add(student1);
        students.add(student2);
        students.add(student3);
        students.add(student4);
        students.add(student5);
        students.add(student6);
    }

    private Student getStudent(Long id, int age, String fn, String ln, int h, String na) {
        Student student = new Student();
        student.setId(id);
        student.setAge(age);
        student.setLastName(ln);
        student.setFirstName(fn);
        student.setHeight(h);
        student.setNationality(na);
        return student;
    }

    @Override
    public Student read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

        List<Student> studentsByAge = students;

        log.info("Student Reader called :::::");
        if (studentIndex < studentsByAge.size()) {
            Student student = studentsByAge.get(studentIndex);
            studentIndex++;
            log.info("Returning Student with Id :::::" + student.getId());
            return student;
        } else
            return null;
    }
}
