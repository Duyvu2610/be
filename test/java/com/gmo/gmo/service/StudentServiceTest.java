package com.gmo.gmo.service;

import com.gmo.gmo.services.StudentService;
import com.gmo.gmo.services.StudentServiceImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {
    @Mock
    private StudentService studentService;
    @InjectMocks
    private StudentServiceImpl studentServiceIml;

    public void getAll_ReturnList(){

    }
}
