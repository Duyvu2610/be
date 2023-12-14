package com.gmo.gmo.repository;

import com.gmo.gmo.entities.Student;
import com.gmo.gmo.entities.StudentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StudentInfoRepository extends JpaRepository<StudentInfo, Integer>, JpaSpecificationExecutor<StudentInfo> {
    StudentInfo findByStudent(Student st);
}
