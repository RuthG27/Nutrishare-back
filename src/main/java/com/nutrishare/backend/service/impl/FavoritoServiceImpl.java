package com.nutrishare.backend.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nutrishare.backend.model.Favorito;
import com.nutrishare.backend.repository.FavoritoRepository;
import com.nutrishare.backend.service.FavoritoService;

@Service
public class FavoritoServiceImpl implements FavoritoService {
    private static final Logger logger = LoggerFactory.getLogger(FavoritoServiceImpl.class);

    @Autowired
    FavoritoRepository favoritoRepository;

    @Override
    public List<Favorito> obtenerFavoritosPorUsuario(String userId) {
        return favoritoRepository.findByUserId(userId);
    }

    @Override
    public Favorito agregarFavorito(String userId, String recetaId) {
        if (favoritoRepository.findByUserIdAndRecetaId(userId, recetaId).isPresent()) {
            logger.error("La receta ya está en favoritos del usuario");
            throw new RuntimeException("La receta ya está en favoritos");
        }

        Favorito favorito = Favorito.builder()
                .userId(userId)
                .recetaId(recetaId)
                .fechaAgregado(LocalDateTime.now())
                .build();

        return favoritoRepository.save(favorito);
    }

    @Override
    public void eliminarFavorito(String userId, String recetaId) {
        logger.info("Intentando eliminar favorito - userId: {}, recetaId: {}", userId, recetaId);

        if (favoritoRepository.findByUserIdAndRecetaId(userId, recetaId).isEmpty()) {
            logger.error("El favorito no existe para userId: {} y recetaId: {}", userId, recetaId);
            throw new RuntimeException("El favorito no existe");
        }

        favoritoRepository.deleteByUserIdAndRecetaId(userId, recetaId);
        logger.info("Favorito eliminado exitosamente");
    }
}
