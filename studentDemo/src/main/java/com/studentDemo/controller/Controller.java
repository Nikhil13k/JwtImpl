package com.studentDemo.controller;

import com.studentDemo.model.Admin;
import com.studentDemo.model.Student;
import com.studentDemo.repo.AdminRepository;
import com.studentDemo.service.AdminService;
import com.studentDemo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {

    @Autowired
    private StudentService studentService;

    @Autowired
    private AdminService adminService;

    @GetMapping("/students")
    public List<Student> getList(){
        return studentService.getAll();
    }

    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student){
        studentService.addStudent(student);
        return student;
    }

    @PostMapping("/login")
    public String login(@RequestBody Admin admin){
        return adminService.verify(admin);
    }





}
