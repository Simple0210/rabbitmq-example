package com.example.consumer_rabbitmqapp.repository;

import com.example.consumer_rabbitmqapp.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {

    Optional<Student> findByFullNameAndPhoneNumber(String fullName, String phoneNumber);
}
