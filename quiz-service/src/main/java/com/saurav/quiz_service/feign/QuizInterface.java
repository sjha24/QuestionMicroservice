package com.saurav.quiz_service.feign;

import com.saurav.quiz_service.dto.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient("QUESTIONSERVICE")
public interface QuizInterface {
    @GetMapping("question/generate")
    ResponseEntity<List<Integer>> getQuestionForQuiz(@RequestParam String category, @RequestParam Integer numQ);

    @PostMapping("question/getQuestions")
    ResponseEntity<?> fetchQuestionFromId(@RequestBody List<Integer> qIds);

    @PostMapping("question/getScore")
    ResponseEntity<?> getScore(@RequestBody List<Response> responses);
}
