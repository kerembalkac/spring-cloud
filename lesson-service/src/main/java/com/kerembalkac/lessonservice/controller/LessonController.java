package com.kerembalkac.lessonservice.controller;

import com.kerembalkac.lessonservice.client.StudentClient;
import com.kerembalkac.lessonservice.model.Lesson;
import com.kerembalkac.lessonservice.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lesson")
@RequiredArgsConstructor
public class LessonController {
    private final LessonRepository lessonRepository;
    private final StudentClient studentClient;

    @PostMapping
    public Lesson add(@RequestBody Lesson lesson) {
        return lessonRepository.addLesson(lesson);
    }

    @GetMapping("/{id}")
    public Lesson findById(@PathVariable("id") Long id) {
        return lessonRepository.findById(id);
    }

    @GetMapping
    public List<Lesson> findAll() {
        return lessonRepository.findAll();
    }


    @GetMapping("/with-students")
    public List<Lesson> findAllWithStudents(){

        List<Lesson> lessonList = lessonRepository.findAll();

        lessonList.forEach(l -> l.setStudentList(studentClient.findByLessonId(l.getId())));

        return lessonList;
    }
}
