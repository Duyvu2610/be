package com.gmo.gmo.dto;

import java.util.Date;

public class StudentDTO {
    private int studentId;
    private String studentName;
    private String studentCode;
    private String address;
    private Double averageScore;
    private Date dateOfBirth;

    public StudentDTO(int studentId, String studentName, String studentCode, String address, Double averageScore, Date dateOfBirth) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentCode = studentCode;
        this.address = address;
        this.averageScore = averageScore;
        this.dateOfBirth = dateOfBirth;
    }
    public StudentDTO(String studentName, String studentCode, String address, Double averageScore, Date dateOfBirth) {
        this.studentName = studentName;
        this.studentCode = studentCode;
        this.address = address;
        this.averageScore = averageScore;
        this.dateOfBirth = dateOfBirth;
    }
    public StudentDTO() {

    }
    // Getters and setters

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(Double averageScore) {
        this.averageScore = averageScore;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
