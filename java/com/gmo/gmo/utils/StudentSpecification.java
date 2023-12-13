package com.gmo.gmo.utils;

import com.gmo.gmo.dto.StudentDTO;
import com.gmo.gmo.entities.StudentInfo;
import org.springframework.data.jpa.domain.Specification;

import java.util.Date;

public class StudentSpecification {
    public static Specification<StudentInfo> withCode(String stCode){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("student").get("studentCode"), stCode);
    }
    public static Specification<StudentInfo> withName(String stName){
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("student").get("studentName"), "%" + stName + "%");
    }
    public static Specification<StudentInfo> withDob(Date dob){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("dateOfBirth"), dob);
    }
    public static Specification<StudentInfo> withAll(String code, String stName, Date dob){
        return Specification.where(withCode(code)).and(withName(stName).and(withDob(dob)));
    }
    public static Specification<StudentInfo> withCodeAndName(String code, String stName){
        return Specification.where(withCode(code)).and(withName(stName));
    }
    public static Specification<StudentInfo> withCodeAndDob(String code, Date dob){
        return Specification.where(withCode(code)).and(withDob(dob));
    }
    public static Specification<StudentInfo> withNameAndDob( String stName, Date dob){
        return Specification.where(withName(stName).and(withDob(dob)));
    }

}
