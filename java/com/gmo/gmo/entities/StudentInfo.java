package com.gmo.gmo.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "student_info")
public class StudentInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "info_id")
    private int infoId;
    @OneToOne
    @JoinColumn(name = "student_id", referencedColumnName = "student_id")
    private Student student;
    @Column(name = "address")
    private String address;
    @Column(name = "average_score")
    private Double averageScore;
    @Column(name = "date_of_birth")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfBirth;

    public StudentInfo(Student student, String address, Double averageScore, Date dateOfBirth) {
        this.student = student;
        this.address = address;
        this.averageScore = averageScore;
        this.dateOfBirth = dateOfBirth;
    }

    public StudentInfo() {

    }

    public int getInfoId() {
        return infoId;
    }

    public void setInfoId(int infoId) {
        this.infoId = infoId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
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
