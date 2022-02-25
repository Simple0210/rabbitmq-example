package com.example.producerrabbitmqapp.entity;

import com.example.producerrabbitmqapp.enums.ActionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class StudentAction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @Enumerated(value = EnumType.STRING)
    private ActionEnum action;

    @Column(updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @ManyToOne
    private Student student;

    public StudentAction(ActionEnum action, Student student) {
        this.action = action;
        this.student = student;
    }

    public StudentAction(ActionEnum action, Timestamp createdAt, Student student) {
        this.action = action;
        this.createdAt = createdAt;
        this.student = student;
    }
}
