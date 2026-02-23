package com.saurav.QuestionService.dto;

import lombok.Data;


@Data
public class QuestionResponseDto {
    private Integer id;
    private String title;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
}
