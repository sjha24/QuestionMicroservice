package com.saurav.QuestionService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionSubmitResponseDto {
    private Integer totalMarks;
    private List<QuestionResultDto> results;
}
