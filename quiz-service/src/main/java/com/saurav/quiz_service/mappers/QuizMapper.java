package com.saurav.quiz_service.mappers;

import com.saurav.quiz_service.dto.QuizDto;
import com.saurav.quiz_service.entity.Quiz;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface QuizMapper {

    QuizMapper INSTANCE = Mappers.getMapper(QuizMapper.class);

    QuizDto toDto(final Quiz quiz);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "questionsIdList", ignore = true)
    Quiz toEntity (final QuizDto quizDto);

    default Quiz toEntityWithQuestions(QuizDto dto, List<Integer> questionIds) {
        Quiz quiz = toEntity(dto);
        quiz.setQuestionsIdList(questionIds);
        return quiz;
    }

}
