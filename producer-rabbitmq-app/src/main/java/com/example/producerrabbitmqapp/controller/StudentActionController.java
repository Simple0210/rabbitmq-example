package com.example.producerrabbitmqapp.controller;

import com.example.producerrabbitmqapp.payload.StudentActionDTO;
import com.example.producerrabbitmqapp.responses.ApiResponse;
import com.example.producerrabbitmqapp.service.StudentActionServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/studentAction")
@RequiredArgsConstructor
public class StudentActionController {

    private final StudentActionServiceImpl studentActionService;

    @PostMapping("/save")
    @ApiOperation(value = "Student actionni saqlash uchun")
    public ApiResponse saveNewStudentAction(@RequestBody StudentActionDTO studentActionDTO){
        return studentActionService.saveNewStudentAction(studentActionDTO);
    }
}
