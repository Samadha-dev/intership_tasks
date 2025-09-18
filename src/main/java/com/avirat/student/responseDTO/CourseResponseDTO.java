package com.avirat.student.responseDTO;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseResponseDTO {

    private Long id;

    private String courseName;

    private String description;

    private String instructor;

}
