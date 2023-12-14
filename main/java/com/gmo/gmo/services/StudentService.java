package com.gmo.gmo.services;

import com.gmo.gmo.dto.StudentDTO;
import com.gmo.gmo.entities.Student;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

public interface StudentService {
    Student save(Student user);
    Student findByName(String userName);
    Student update(Student user);
    StudentDTO update(int id, StudentDTO user);
    String generateCode();
    Page<StudentDTO> find(String stCode, String stName, Date dob, int page, int size, String sortBy, String sortOrder);

    StudentDTO save(StudentDTO studentDTO);
    StudentDTO findById(Integer id);

    StudentDTO delete(Integer id);
}
