package com.dnth_underdog_241.online_fashion_shopping.mapper;

import com.dnth_underdog_241.online_fashion_shopping.dto.response.CategoryGetDto;
import com.dnth_underdog_241.online_fashion_shopping.model.Category;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper
{
    Category toEntity(CategoryGetDto categoryGetDto);

    CategoryGetDto toDto(Category category);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Category partialUpdate(CategoryGetDto categoryGetDto, @MappingTarget Category category);
}