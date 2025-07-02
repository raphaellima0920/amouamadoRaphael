package com.amouamado.amou_amado.service;
import java.util.List;

import com.amouamado.amou_amado.dto.OrganizadorDTO;

public interface OrganizadorService {
    OrganizadorDTO buscarPorId(Long id);
    OrganizadorDTO atualizar(Long id, OrganizadorDTO dto);
    List<OrganizadorDTO> listarTodos();
}
