package com.example.producerrabbitmqapp.repository;

import com.example.producerrabbitmqapp.entity.StudentAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentActionRepository extends JpaRepository<StudentAction, Integer> {
}
