package com.kerembalkac.lessonservice.model;

import lombok.Data;

import java.util.List;

@Data
public class Lesson {

    private Long id;
    private String name;
    private List<Student> studentList;

}
