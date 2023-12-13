package com.gmo.gmo.dto;

import com.gmo.gmo.entities.Student;
import com.gmo.gmo.entities.StudentInfo;

public class TransferDTO {
    public static StudentDTO toStDTO(StudentInfo stInfo) {
        StudentDTO studentDTO = new StudentDTO();

        studentDTO.setStudentId(stInfo.getStudent().getStudentId());
        studentDTO.setStudentName(stInfo.getStudent().getStudentName());
        studentDTO.setStudentCode(stInfo.getStudent().getStudentCode());
        studentDTO.setAddress(stInfo.getAddress());
        studentDTO.setAverageScore(stInfo.getAverageScore());
        studentDTO.setDateOfBirth(stInfo.getDateOfBirth());
        return studentDTO;
    }

    public static Student toStudent(StudentDTO studentDTO) {
        return new Student(studentDTO.getStudentName(), studentDTO.getStudentCode());
    }

    public static StudentInfo toStudentInfo(StudentDTO studentDTO) {
        Student st = toStudent(studentDTO);
        return new StudentInfo(st, studentDTO.getAddress(), studentDTO.getAverageScore(), studentDTO.getDateOfBirth());
    }
}
