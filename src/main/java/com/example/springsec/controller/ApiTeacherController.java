package com.example.springsec.controller;

import com.example.springsec.student.Student;
import com.example.springsec.student.StudentManager;
import com.example.springsec.teacher.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/teacher")
public class ApiTeacherController {

    @Autowired
    StudentManager studentManager;

    @PreAuthorize("hasAuthority('ROLE_TEACHER')")
    @GetMapping("/students")
    public List<Student> studentList(@AuthenticationPrincipal Teacher teacher){
        return studentManager.myStudentList(teacher.getId());


    }
}
