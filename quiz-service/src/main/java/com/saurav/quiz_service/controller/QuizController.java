package com.saurav.quiz_service.controller;

import com.saurav.quiz_service.dto.QuizDto;
import com.saurav.quiz_service.dto.QuizSubmitDto;
import com.saurav.quiz_service.dto.Response;
import com.saurav.quiz_service.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@RestController
@RequiredArgsConstructor
@RequestMapping("quiz")
public class QuizController {

    private final QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<?> createQuiz(@RequestBody QuizDto dto){
        return quizService.createQuiz(dto);
    }

    @GetMapping("/fetch")
    public ResponseEntity<?> fetchQuiz(@RequestParam Integer id){
        return quizService.fetchQuiz(id);
    }

    @PostMapping("/submit")
    public ResponseEntity<?> submitQuestion(@RequestBody List<Response> responseList){
        return quizService.submitQuestion(responseList);
    }

}
