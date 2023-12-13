package com.gmo.gmo.repository;

import com.gmo.gmo.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Student findByStudentName(String name);
    Student findByStudentCode(String code);
}
