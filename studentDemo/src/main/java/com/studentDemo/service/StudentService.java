package com.studentDemo.service;

import com.studentDemo.model.Student;
import com.studentDemo.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAll(){
        return studentRepository.findAll();
    }

    public Student addStudent(Student student){
        studentRepository.save(student);
        return student;
    }

}
