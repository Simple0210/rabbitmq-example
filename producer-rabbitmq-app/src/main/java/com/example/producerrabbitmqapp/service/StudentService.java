package com.example.producerrabbitmqapp.service;

import com.example.producerrabbitmqapp.payload.StudentDTO;
import com.example.producerrabbitmqapp.responses.ApiResponse;

public interface StudentService {

    ApiResponse saveNewStudent(StudentDTO studentDTO);





}
