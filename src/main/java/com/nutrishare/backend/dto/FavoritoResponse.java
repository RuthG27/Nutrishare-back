package com.nutrishare.backend.dto;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.nutrishare.backend.model.Favorito;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FavoritoResponse {
    private String id;
    private String userId;
    private String recetaId;
    private LocalDateTime fechaAgregado;

    public static FavoritoResponse fromFavorito(Favorito favorito) {
        return FavoritoResponse.builder()
                .id(favorito.getId())
                .userId(favorito.getUserId())
                .recetaId(favorito.getRecetaId())
                .fechaAgregado(favorito.getFechaAgregado())
                .build();
    }
}
