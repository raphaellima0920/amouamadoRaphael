package com.amouamado.amou_amado.repository;

import com.amouamado.amou_amado.model.Evento;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, Long> {
    List<Evento> findByCategoriaIgnoreCase(String categoria);
    List<Evento> findByOrganizadorId(Long organizadorId);
}