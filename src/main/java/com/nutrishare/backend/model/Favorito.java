package com.nutrishare.backend.model;

import java.time.LocalDateTime;
import java.util.Objects;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "favoritos")
public class Favorito {
    @Id
    private String id;
    private String userId;
    private String recetaId;
    private LocalDateTime fechaAgregado;
}
