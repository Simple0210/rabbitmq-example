package com.example.consumer_rabbitmqapp.service;

import com.example.consumer_rabbitmqapp.entity.Student;
import com.example.consumer_rabbitmqapp.entity.StudentAction;
import com.example.consumer_rabbitmqapp.repository.StudentActionRepository;
import com.example.consumer_rabbitmqapp.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentActionService {

    private final StudentRepository studentRepository;
    private final StudentActionRepository studentActionRepository;

    public void saveNewAction(StudentAction studentAction) {
        /*
        STUDENT ACTIONNING ICHIDA KELGAN STUDENT OBJECTINI OLDIN DB DAN IZLAB KO`RADI, BOR BO`LSA O`SHA STUDENT BILAN ACTIONNI BOG`LAB SAQLAYDI,
        AKS HOLDA YANGI STUDENT OBJECTINI YASAB UNI DBGA SAQLAB KEYIN ACTIONNI SAQLAYDI
         */
        Optional<Student> optionalStudent = studentRepository.findByFullNameAndPhoneNumber(studentAction.getStudent().getFullName(), studentAction.getStudent().getPhoneNumber());
        if (optionalStudent.isPresent()) {
            StudentAction studentAction1 = new StudentAction(
                    studentAction.getAction(),
                    studentAction.getCreatedAt(),
                    optionalStudent.get()
            );
            studentActionRepository.save(studentAction1);
            System.out.println("Student bor ekan, DB dagi Studentga fk qilib saqlandi.");
        } else {
            Student student = new Student(studentAction.getStudent().getFullName(), studentAction.getStudent().getPhoneNumber());
            Student savedStudent = studentRepository.save(student);
            StudentAction studentAction1 = new StudentAction(studentAction.getAction(), studentAction.getCreatedAt(), savedStudent);
            studentActionRepository.save(studentAction1);
            System.out.println("Student yo`q ekan yangi Student yaratib, uni saqlab o`sha Student bilan fk qilib saqlandi.");
        }

    }
}
