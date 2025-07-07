package com.amouamado.amou_amado.repository;

import com.amouamado.amou_amado.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    Optional<Categoria> findByNomeIgnoreCase(String nome);
}
