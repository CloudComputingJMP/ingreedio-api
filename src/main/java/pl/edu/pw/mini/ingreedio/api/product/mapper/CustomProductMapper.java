package pl.edu.pw.mini.ingreedio.api.product.mapper;

import org.springframework.stereotype.Component;
import pl.edu.pw.mini.ingreedio.api.product.dto.ProductRequestDto;
import pl.edu.pw.mini.ingreedio.api.product.model.*;

import java.util.stream.Collectors;

@Component
public class CustomProductMapper {
    public CustomProductMapper() {}

    public ProductDocument map(ProductRequestDto productRequestDto) {
        ProductDocument productDocument = new ProductDocument();
        productDocument.setBrand(BrandDocument.builder().id(productRequestDto.brand()).build());
        productDocument.setName(productRequestDto.name());
        productDocument.setLargeImageUrl(productRequestDto.largeImageUrl());
        productDocument.setSmallImageUrl(productRequestDto.smallImageUrl());
        productDocument.setProvider(ProviderDocument.builder().id(productRequestDto.provider()).build());
        productDocument.setVolume(productRequestDto.volume());
        productDocument.setIngredients(productRequestDto.ingredients().stream()
                .map(id->IngredientDocument.builder().id(id).build()).collect(Collectors.toSet()));
        productDocument.setCategories(productRequestDto.categories().stream().map(
                id-> CategoryDocument.builder().id(id).build()).collect(Collectors.toSet()));
        productDocument.setLongDescription(productRequestDto.longDescription());
        productDocument.setShortDescription(productRequestDto.shortDescription());
        return productDocument;
    }
}
