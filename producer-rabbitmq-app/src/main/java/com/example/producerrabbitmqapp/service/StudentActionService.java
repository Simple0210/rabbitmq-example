package com.example.producerrabbitmqapp.service;

import com.example.producerrabbitmqapp.payload.StudentActionDTO;
import com.example.producerrabbitmqapp.responses.ApiResponse;

public interface StudentActionService {
    ApiResponse saveNewStudentAction(StudentActionDTO studentActionDTO);
}
