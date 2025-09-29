package com.avirat.student.studentEntity;

import jakarta.persistence.*;
import lombok.*;
import jakarta.persistence.Id;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Course_INFO")
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String courseName;

    private String description;

    private String instructor;

    @ManyToMany(mappedBy = "courses")
    private List<StudentEntity> students;

}
