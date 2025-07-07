package com.amouamado.amou_amado.service.impl;

import com.amouamado.amou_amado.dto.EventoDto;
import com.amouamado.amou_amado.model.Categoria;
import com.amouamado.amou_amado.model.Evento;
import com.amouamado.amou_amado.model.Organizador;
import com.amouamado.amou_amado.repository.OrganizadorRepository;
import com.amouamado.amou_amado.repository.EventoRepository;
import com.amouamado.amou_amado.repository.CategoriaRepository;
import com.amouamado.amou_amado.service.EventoService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventoServiceImpl implements EventoService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private OrganizadorRepository organizadorRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<EventoDto> listarTodos() {
        List<Evento> eventos = eventoRepository.findAll();
        return eventos.stream()
                      .map(this::toDto)
                      .collect(Collectors.toList());
    }

    @Override
    public EventoDto buscarPorId(Long id) {
        Optional<Evento> eventoOpt = eventoRepository.findById(id);
        if (eventoOpt.isPresent()) {
            return toDto(eventoOpt.get());
        } else {
            throw new RuntimeException("Evento não encontrado com ID: " + id);
        }
    }

    @Override
    public EventoDto salvar(EventoDto eventoDto) {
        Evento evento = modelMapper.map(eventoDto, Evento.class);
        Evento salvo = eventoRepository.save(evento);
        return toDto(salvo);
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
        return toDto(salvo);
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

        if (eventoDTO.getOrganizador() != null && eventoDTO.getOrganizador().getId() != null) {
            Optional<Organizador> organizadorOpt = organizadorRepository.findById(eventoDTO.getOrganizador().getId());
            evento.setOrganizador(organizadorOpt.orElse(null));
        }

        if (eventoDTO.getCategoria() != null && !eventoDTO.getCategoria().isEmpty()) {
            categoriaRepository.findByNomeIgnoreCase(eventoDTO.getCategoria())
                .ifPresent(evento::setCategoria);
        }

        Evento salvo = eventoRepository.save(evento);
        return toDto(salvo);
    }

    @Override
public List<EventoDto> listarPorUsuario(Long usuarioId) {
    List<Evento> eventos = eventoRepository.findByOrganizador_Id(usuarioId);
    return eventos.stream()
                  .map(this::toDto)
                  .collect(Collectors.toList());
}


    @Override
    public List<EventoDto> buscarPorCategoria(String categoriaNome) {
        Optional<Categoria> categoriaOpt = categoriaRepository.findByNomeIgnoreCase(categoriaNome);
        if (categoriaOpt.isEmpty()) {
            return List.of();
        }

        Categoria categoria = categoriaOpt.get();
        List<Evento> eventos = eventoRepository.findByCategoria(categoria);
        return eventos.stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<EventoDto> listarPorOrganizador(Long idOrganizador) {
        List<Evento> eventos = eventoRepository.findByOrganizador_Id(idOrganizador);
        return eventos.stream()
                      .map(this::toDto)
                      .collect(Collectors.toList());
    }

@Override
public List<EventoDto> listarEventosFuturos() {
    List<Evento> todosEventos = eventoRepository.findAll();
    LocalDate hoje = LocalDate.now();

    List<Evento> futuros = todosEventos.stream()
        .filter(e -> {
            try {
                LocalDate dataEvento = LocalDate.parse(e.getDataEvento());
                return dataEvento.isAfter(hoje) || dataEvento.isEqual(hoje);
            } catch (Exception ex) {
                return false;
            }
        })
        .collect(Collectors.toList());

    return futuros.stream().map(this::toDto).collect(Collectors.toList());
}


    private EventoDto toDto(Evento evento) {
        EventoDto dto = new EventoDto();
        dto.setId(evento.getId());
        dto.setTitulo(evento.getTitulo());

        if (evento.getCategoria() != null) {
            dto.setCategoria(evento.getCategoria().getNome());
        } else {
            dto.setCategoria(null);
        }

        dto.setLocal(evento.getLocal());
        dto.setImagem(evento.getImagem());
        dto.setDuracao(evento.getDuracao());
        dto.setClassificacaoEtaria(evento.getClassificacaoEtaria());
        dto.setData(evento.getDataEvento());
        dto.setHorario(evento.getHorario());
        dto.setPreco(evento.getPreco());
        dto.setDescricao(evento.getDescricao());
        dto.setPoliticas(evento.getPoliticas());
        dto.setDestaques(evento.getDestaques());
        dto.setArtistas(evento.getArtistas());
        dto.setBairro(evento.getBairro());
        dto.setCidade(evento.getCidade());
        dto.setEstado(evento.getEstado());
        dto.setCep(evento.getCep());
        dto.setTelefoneContato(evento.getTelefoneContato());
        dto.setEmailContato(evento.getEmailContato());
        dto.setWebsiteContato(evento.getWebsiteContato());
        dto.setTwitter(evento.getTwitter());
        dto.setLinkedin(evento.getLinkedin());
        dto.setOrganizador(evento.getOrganizador());
        return dto;
    }
}
