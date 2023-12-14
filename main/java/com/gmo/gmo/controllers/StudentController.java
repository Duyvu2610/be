package com.gmo.gmo.controllers;

import com.gmo.gmo.common.MyResponse;
import com.gmo.gmo.dto.StudentDTO;
import com.gmo.gmo.entities.Student;
import com.gmo.gmo.services.StudentService;
import com.gmo.gmo.services.StudentServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }

        @PostMapping("")
    public ResponseEntity<MyResponse> addStudent(@RequestBody StudentDTO student){
        //init info
        MyResponse response = new MyResponse();
        HttpStatus status = HttpStatus.CREATED;
        StudentDTO currentUser = studentService.save(student);

        if (currentUser == null){
            status = HttpStatus.BAD_REQUEST;
            response.setMessage("Loi");
            response.setStatus(400);
        }else {
            response.setStatus(status.value());
            response.setData(currentUser);
        }

        return new ResponseEntity<>(response, status);
    }
    @GetMapping("")
    public ResponseEntity<MyResponse> findStudent(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "") String sortBy,
            @RequestParam(defaultValue = "") String sortOrder,
            @RequestParam(defaultValue = "") String stCode,
            @RequestParam(defaultValue = "") String name,
            @RequestParam(defaultValue = "") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dob) {
        MyResponse response = new MyResponse();
        Page<StudentDTO> entities = studentService.find(stCode, name, dob, page, size, sortBy, sortOrder);

        if (entities != null){
            response.setData(entities);
        }else {
            response.setStatus(400);
            response.setMessage("Error");
        }
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatus()));
    }
    @GetMapping("/{id}")
    public ResponseEntity<MyResponse> findStudentById(@PathVariable int id){
        MyResponse response = new MyResponse();
        StudentDTO data = studentService.findById(id);
        if (data ==  null){
            response.setStatus(400);
            response.setMessage("Loi");
        }else {
            response.setData(data);
        }
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatus()));
    }
    @GetMapping("/code")
    public ResponseEntity<MyResponse> generateCode(){
        MyResponse response = new MyResponse();
        HttpStatus status = HttpStatus.OK;
        String data = studentService.generateCode();
        if (data.isEmpty()){
            status = HttpStatus.BAD_REQUEST;
            response.setStatus(400);
            response.setMessage("Loi");
        }else {
            response.setData(data);
        }

        return new ResponseEntity<>(response, status);
    }
    @PutMapping("/{id}")
    public ResponseEntity<MyResponse> update(@PathVariable int id,@RequestBody StudentDTO studentDTO){
        MyResponse response = new MyResponse();
        HttpStatus status ;
        StudentDTO st = studentService.update(id, studentDTO);
        if (st == null){
            status = HttpStatus.BAD_REQUEST;
            response.setStatus(status.value());
            response.setMessage("Loi");
        }else {
            status = HttpStatus.OK;
            response.setData(st);
        }

        return new ResponseEntity<>(response, status);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<MyResponse> deleteStudent(@PathVariable int id){
        MyResponse response = new MyResponse();
        StudentDTO st = studentService.delete(id);
        if (st != null){
            response.setData(st);
        }else{
            response.setStatus(400);
            response.setMessage("Err");
        }
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatus()));
    }




}
