package com.example.consumer_rabbitmqapp.entity;

import com.example.consumer_rabbitmqapp.enums.ActionEnum;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
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
