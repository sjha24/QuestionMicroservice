package com.saurav.quiz_service.dto;

import lombok.Data;

import java.util.List;


@Data
public class QuizDto {
    private String title;
    private Integer numOfQuestion;
    private String category;
}
