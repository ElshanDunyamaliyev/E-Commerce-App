package dev.elshan.mapper;

import dev.elshan.dto.CategoryDto;
import dev.elshan.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public abstract class CategoryMapper {

    public abstract CategoryDto mapToDto(CategoryEntity categoryEntity);
    public abstract CategoryEntity mapToEntity(CategoryDto categoryDto);
}
