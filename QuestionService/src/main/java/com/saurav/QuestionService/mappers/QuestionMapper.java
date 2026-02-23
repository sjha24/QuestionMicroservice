package com.saurav.QuestionService.mappers;


import com.saurav.QuestionService.dto.QuestionDto;
import com.saurav.QuestionService.dto.QuestionResponseDto;
import com.saurav.QuestionService.entity.Question;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;



@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface QuestionMapper {

    QuestionMapper INSTANCE = Mappers.getMapper(QuestionMapper.class);

    Question toEntity(final QuestionDto dto);
    QuestionResponseDto toDto(final Question question);
}
