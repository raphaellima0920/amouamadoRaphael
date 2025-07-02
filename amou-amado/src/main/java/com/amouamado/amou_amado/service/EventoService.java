package com.amouamado.amou_amado.service;

import com.amouamado.amou_amado.dto.EventoDto;
import com.amouamado.amou_amado.model.Evento;

import java.util.List;

@SuppressWarnings("unused")
public interface EventoService {
    List<EventoDto> listarTodos();
    EventoDto salvar(EventoDto eventoDto);
    EventoDto buscarPorId(Long id);
    EventoDto criarEvento(EventoDto eventoDTO);
    EventoDto atualizar(Long id,EventoDto eventoDto);
    void deletar(Long id);
    List<EventoDto> buscarPorCategoria(String categoria);
    List<EventoDto> listarPorOrganizador(Long idOrganizador);
}