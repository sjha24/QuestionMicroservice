package com.saurav.quiz_service.service;

import com.saurav.quiz_service.dto.QuizDto;
import com.saurav.quiz_service.dto.QuizSubmitDto;
import com.saurav.quiz_service.dto.QuizSubmitResponseDto;
import com.saurav.quiz_service.dto.Response;
import com.saurav.quiz_service.entity.Quiz;
import com.saurav.quiz_service.feign.QuizInterface;
import com.saurav.quiz_service.mappers.QuizMapper;
import com.saurav.quiz_service.projection.QuizAnsKey;
import com.saurav.quiz_service.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;




@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepository quizRepository;
    private final QuizInterface quizInterface;

    public ResponseEntity<?> createQuiz(QuizDto dto) {
        List<Integer> questionIds = quizInterface.getQuestionForQuiz(dto.getCategory(), dto.getNumOfQuestion()).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(dto.getTitle());
        quiz.setQuestionsIdList(questionIds);
        quizRepository.save(quiz);
        return new ResponseEntity<>("Quiz Created", HttpStatus.CREATED);
    }

    public ResponseEntity<?> fetchQuiz(Integer id) {
        List<Integer> questionIdList = findById(id).getQuestionsIdList();
        var questions = quizInterface.fetchQuestionFromId(questionIdList).getBody();
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    private static QuizDto getQuizDto(Quiz quiz) {
        return QuizMapper.INSTANCE.toDto(quiz);
    }

    private Quiz findById(Integer id){
        return quizRepository.findById(id).orElseThrow(()-> new RuntimeException("Invalid Quiz id "+ id));
    }

    public ResponseEntity<?> submitQuestion(List<Response> responseList) {
        var result = quizInterface.getScore(responseList).getBody();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
