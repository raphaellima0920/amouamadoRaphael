package com.amouamado.amou_amado.service.impl;

import com.amouamado.amou_amado.dto.EventoDto;
import com.amouamado.amou_amado.model.Evento;
import com.amouamado.amou_amado.repository.EventoRepository;
import com.amouamado.amou_amado.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;


@Service
public class EventoServiceImpl implements EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<EventoDto> listarTodos() {
        List<Evento> eventos = eventoRepository.findAll();
        return eventos.stream()
                      .map(evento -> modelMapper.map(evento, EventoDto.class))
                      .collect(Collectors.toList());
    }

    @Override
    public EventoDto buscarPorId(Long id) {
        Optional<Evento> eventoOpt = eventoRepository.findById(id);
        if (eventoOpt.isPresent()) {
            return modelMapper.map(eventoOpt.get(), EventoDto.class);
        } else {
            throw new RuntimeException("Evento não encontrado com ID: " + id);
        }
    }

    @Override
    public EventoDto salvar(EventoDto eventoDto) {
        Evento evento = modelMapper.map(eventoDto, Evento.class);
        Evento salvo = eventoRepository.save(evento);
        return modelMapper.map(salvo, EventoDto.class);
    }

    @Override
    public EventoDto atualizar(Long id, EventoDto eventoDto) {
        Optional<Evento> eventoOpt = eventoRepository.findById(id);
        if (eventoOpt.isEmpty()) {
            throw new RuntimeException("Evento não encontrado com ID: " + id);
        }

        Evento eventoAtualizado = modelMapper.map(eventoDto, Evento.class);
        eventoAtualizado.setId(id);
        Evento salvo = eventoRepository.save(eventoAtualizado);
        return modelMapper.map(salvo, EventoDto.class);
    }

    @Override
    public void deletar(Long id) {
        if (!eventoRepository.existsById(id)) {
            throw new RuntimeException("Evento não encontrado com ID: " + id);
        }
        eventoRepository.deleteById(id);
    }

    @Override
public EventoDto criarEvento(EventoDto eventoDTO) {
    Evento evento = modelMapper.map(eventoDTO, Evento.class);
    Evento salvo = eventoRepository.save(evento);
    return modelMapper.map(salvo, EventoDto.class);
}

@Override
public List<EventoDto> buscarPorCategoria(String categoria) {
    List<Evento> eventos = eventoRepository.findByCategoriaIgnoreCase(categoria);
    return eventos.stream()
                  .map(evento -> modelMapper.map(evento, EventoDto.class))
                  .collect(Collectors.toList());
}


@Override
public List<EventoDto> listarPorOrganizador(Long idOrganizador) {
    List<Evento> eventos = eventoRepository.findByOrganizadorId(idOrganizador);
    return eventos.stream()
                  .map(evento -> modelMapper.map(evento, EventoDto.class))
                  .collect(Collectors.toList());
}

}