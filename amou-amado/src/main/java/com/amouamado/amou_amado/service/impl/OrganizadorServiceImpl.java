package com.amouamado.amou_amado.service.impl;

import com.amouamado.amou_amado.dto.OrganizadorDTO;
import com.amouamado.amou_amado.model.Organizador;
import com.amouamado.amou_amado.repository.OrganizadorRepository;
import com.amouamado.amou_amado.service.OrganizadorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrganizadorServiceImpl implements OrganizadorService {

    @Autowired
    private OrganizadorRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public OrganizadorDTO buscarPorId(Long id) {
        Organizador organizador = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Organizador não encontrado"));
        return modelMapper.map(organizador, OrganizadorDTO.class);
    }

    @Override
    public OrganizadorDTO atualizar(Long id, OrganizadorDTO dto) {
        Organizador organizador = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Organizador não encontrado"));

        modelMapper.map(dto, organizador);
        repository.save(organizador);
        return modelMapper.map(organizador, OrganizadorDTO.class);
    }

    @Override
    public List<OrganizadorDTO> listarTodos() {
        return repository.findAll()
                         .stream()
                         .map(o -> modelMapper.map(o, OrganizadorDTO.class))
                         .collect(Collectors.toList());
    }
}
