package dev.elshan.mapper;

import dev.elshan.dto.ProductDto;
import dev.elshan.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public abstract class ProductMapper {
    public abstract ProductDto mapToDto(ProductEntity productEntity);
    public abstract ProductEntity mapToEntity(ProductDto productDto);
}
