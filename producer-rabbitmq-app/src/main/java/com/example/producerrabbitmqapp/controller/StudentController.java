package com.example.producerrabbitmqapp.controller;

import com.example.producerrabbitmqapp.payload.StudentDTO;
import com.example.producerrabbitmqapp.responses.ApiResponse;
import com.example.producerrabbitmqapp.service.StudentServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentServiceImpl studentService;

    @PostMapping("/save")
    @ApiOperation(value = "Yangi studentni saqlash uchun")
    public ApiResponse saveNewStudent(@RequestBody StudentDTO studentDTO){
        return studentService.saveNewStudent(studentDTO);
    }



}
