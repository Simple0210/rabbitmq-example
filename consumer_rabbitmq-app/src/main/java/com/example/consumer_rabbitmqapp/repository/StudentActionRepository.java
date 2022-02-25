package com.example.consumer_rabbitmqapp.repository;

import com.example.consumer_rabbitmqapp.entity.StudentAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentActionRepository extends JpaRepository<StudentAction, Integer> {
}
