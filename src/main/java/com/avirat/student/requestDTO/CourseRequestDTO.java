package com.avirat.student.requestDTO;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseRequestDTO {

    private String courseName;

    private String description;

    private String instructor;
}
