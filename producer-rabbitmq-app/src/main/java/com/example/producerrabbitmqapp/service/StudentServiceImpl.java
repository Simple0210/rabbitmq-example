package com.example.producerrabbitmqapp.service;

import com.example.producerrabbitmqapp.entity.Student;
import com.example.producerrabbitmqapp.payload.StudentDTO;
import com.example.producerrabbitmqapp.repository.StudentRepository;
import com.example.producerrabbitmqapp.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;

    @Override
    public ApiResponse saveNewStudent(StudentDTO studentDTO) {
        if (studentDTO==null)
            return new ApiResponse("Student null qiymatda jo`natildi",false);
        Student student=new Student(
                studentDTO.getFullName(),
                studentDTO.getPhoneNumber()
        );
        studentRepository.save(student);
        return new ApiResponse("Student ma`lumotlari muvaffaqiyatli saqlandi",true);
    }
}
