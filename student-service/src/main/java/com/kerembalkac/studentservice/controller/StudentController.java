package com.kerembalkac.studentservice.controller;

import com.kerembalkac.studentservice.model.Student;
import com.kerembalkac.studentservice.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {


    private final StudentRepository studentRepository;

    @PostMapping
    public Student add(@RequestBody Student student) {
        return studentRepository.addStudent(student);
    }

    @GetMapping("/{id}")
    public Student findById(@PathVariable("id") Long id) {
        return studentRepository.findById(id);
    }

    @GetMapping
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @GetMapping("/lesson/{lessonId}")
    public List<Student> findByLessonId(@PathVariable("lessonId") Long lessonId){
        return studentRepository.findByLessonId(lessonId);
    }
}
