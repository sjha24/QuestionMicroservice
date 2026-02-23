package com.saurav.QuestionService.service;

import com.saurav.QuestionService.dto.*;
import com.saurav.QuestionService.entity.Question;
import com.saurav.QuestionService.mappers.QuestionMapper;
import com.saurav.QuestionService.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public ResponseEntity<?> getAllQuestion() {
        var questionsList = questionRepository.findAll();
        return ResponseEntity.ok(toDto(questionsList));
    }

    private List<QuestionResponseDto> toDto(List<Question> questionsList) {
        return questionsList.stream().map(QuestionMapper.INSTANCE::toDto).toList();
    }

    private List<Question> toEntity(List<QuestionDto> dtos) {
        return dtos.stream().map(QuestionMapper.INSTANCE::toEntity).toList();
    }


    public ResponseEntity<?> addQuestions(List<QuestionDto> dtos) {
        if(Objects.nonNull(dtos)){
            questionRepository.saveAll(toEntity(dtos));
        }

        return ResponseEntity.ok("All Question Saved");
    }

    public ResponseEntity<?> fetchQuestionByType(String category) {
        var questionList = questionRepository.findByCategory(category);
        return ResponseEntity.ok(toDto(questionList));
    }

    public ResponseEntity<List<Integer>> getQuestionForQuiz(String category, Integer numQ) {
        var questionList = questionRepository.findRandomQuestionsByCategory(category, numQ);
        return new ResponseEntity<>(questionList, HttpStatus.OK);
    }

    public ResponseEntity<?> fetchQuestionByIds(List<Integer> qIds) {
        List<QuestionResponseDto> questionResponseList = new ArrayList<>();
        for(var id : qIds){
            var question = fetchQuestionById(id);
            var dto = QuestionMapper.INSTANCE.toDto(question);
            questionResponseList.add(dto);
        }
        return new ResponseEntity<>(questionResponseList, HttpStatus.OK);
    }

    private Question fetchQuestionById(Integer id){
        return questionRepository.findById(id).orElseThrow(() -> new RuntimeException("Invalid Question id "+ id));
    }

    public ResponseEntity<?> getScore(List<Response> responses) {
        int totalMarks = 0;
        List<QuestionResultDto> resultList = new ArrayList<>(responses.size());

        for (var response : responses) {
            var correctAnswer = fetchQuestionById(response.getId()).getAns();
            boolean isCorrect = correctAnswer != null &&
                    correctAnswer.equalsIgnoreCase(response.getResponse());

            if (isCorrect) {
                totalMarks++;
            }

            resultList.add(
                    new QuestionResultDto(response.getId(), isCorrect ? 1 : 0)
            );
        }
        var response = new QuestionSubmitResponseDto(totalMarks, resultList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
