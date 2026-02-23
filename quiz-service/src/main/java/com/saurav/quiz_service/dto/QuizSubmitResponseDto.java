package com.saurav.quiz_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizSubmitResponseDto {
    private Integer totalMarks;
    private List<QuestionResultDto> results;
}
