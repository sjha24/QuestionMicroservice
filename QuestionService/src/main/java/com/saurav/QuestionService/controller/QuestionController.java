package com.saurav.QuestionService.controller;

import com.saurav.QuestionService.dto.QuestionDto;
import com.saurav.QuestionService.dto.Response;
import com.saurav.QuestionService.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/question")
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllQuestion(){
        return questionService.getAllQuestion();
    }

    @PostMapping("/add")
    public ResponseEntity<?> addQuestions(@RequestBody List<QuestionDto> dtos){
        return questionService.addQuestions(dtos);
    }

    @GetMapping("category/{category}")
    public ResponseEntity<?> getQuestionByCategory(@PathVariable String category){
        return questionService.fetchQuestionByType(category);
    }

    @GetMapping("/generate")
    public ResponseEntity<List<Integer>> getQuestionForQuiz(@RequestParam String category, @RequestParam Integer numQ ){
        return questionService.getQuestionForQuiz(category, numQ);
    }

    @PostMapping("/getQuestions")
    public ResponseEntity<?> fetchQuestionFromId(@RequestBody List<Integer> qIds) {
        return questionService.fetchQuestionByIds(qIds);
    }

    @PostMapping("/getScore")
    public ResponseEntity<?> getScore(@RequestBody List<Response> responses){
        return questionService.getScore(responses);
    }

}
