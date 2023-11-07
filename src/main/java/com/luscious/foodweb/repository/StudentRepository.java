package com.luscious.foodweb.repository;

import com.luscious.foodweb.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StudentRepository extends JpaRepository<Student, Integer>{
}
