package com.luscious.foodweb.student;


import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StudentService {


    public List<Student> getStudent(){
        return List.of(new Student(1, "San San", 21));
    }


}
