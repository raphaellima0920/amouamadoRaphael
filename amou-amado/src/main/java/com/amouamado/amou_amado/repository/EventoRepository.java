package com.amouamado.amou_amado.repository;

import com.amouamado.amou_amado.model.Categoria;
import com.amouamado.amou_amado.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Long> {
    List<Evento> findByCategoria(Categoria categoria);
    List<Evento> findByOrganizador_Id(Long organizadorId); 
}
