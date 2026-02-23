package com.saurav.quiz_service.service;

import com.saurav.quiz_service.dto.QuizDto;
import com.saurav.quiz_service.dto.Response;
import com.saurav.quiz_service.entity.Quiz;
import com.saurav.quiz_service.feign.QuizInterface;
import com.saurav.quiz_service.mappers.QuizMapper;
import com.saurav.quiz_service.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepository quizRepository;
    private final QuizInterface quizInterface;

    public ResponseEntity<?> createQuiz(QuizDto dto) {
        var questionIds = quizInterface.getQuestionForQuiz(dto.getCategory(), dto.getNumOfQuestion()).getBody();
        var quiz = QuizMapper.INSTANCE.toEntityWithQuestions(dto, questionIds);
        quizRepository.save(quiz);
        return new ResponseEntity<>("Quiz Created", HttpStatus.CREATED);
    }

    public ResponseEntity<?> fetchQuiz(Integer id) {
        var questionIdList = findById(id).getQuestionsIdList();
        var questions = quizInterface.fetchQuestionFromId(questionIdList).getBody();
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    private Quiz findById(Integer id){
        return quizRepository.findById(id).orElseThrow(()-> new RuntimeException("Invalid Quiz id "+ id));
    }

    public ResponseEntity<?> submitQuestion(List<Response> responseList) {
        var result = quizInterface.getScore(responseList).getBody();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
