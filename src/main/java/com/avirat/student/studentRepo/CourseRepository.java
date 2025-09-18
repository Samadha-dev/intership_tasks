package com.avirat.student.studentRepo;

import com.avirat.student.studentEntity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<CourseEntity, Long> {

}
