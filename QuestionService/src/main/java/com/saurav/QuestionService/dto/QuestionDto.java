package com.saurav.QuestionService.dto;

import lombok.Data;


@Data
public class QuestionDto {
    private String title;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String difficultyLevel;
    private String ans;
    private String category;
}
