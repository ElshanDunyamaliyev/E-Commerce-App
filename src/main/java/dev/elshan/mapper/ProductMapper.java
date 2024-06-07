package dev.elshan.mapper;

import dev.elshan.dto.ProductDto;
import dev.elshan.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDto mapToDto(ProductEntity productEntity);
    ProductEntity mapToEntity(ProductDto productDto);
}
