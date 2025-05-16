package pl.edu.pw.mini.ingreedio.api.product.dto;

import lombok.Builder;

@Builder
public record AskAIDto(String query, int kNeighbours) {
}
