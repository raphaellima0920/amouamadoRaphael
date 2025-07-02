package com.amouamado.amou_amado.service;

import com.amouamado.amou_amado.dto.UsuarioDTO;
import com.amouamado.amou_amado.model.Usuario;
import com.amouamado.amou_amado.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

   public Usuario salvar(UsuarioDTO dto) {
        if (usuarioRepository.existsByEmail(dto.email)) {
            throw new RuntimeException("Email já cadastrado!");
        }

        Usuario usuario = new Usuario();
        usuario.setNomeCompleto(dto.nomeCompleto);
        usuario.setNomeSocial(dto.nomeSocial);
        usuario.setPronome(dto.pronome);
        usuario.setIdentidadeGenero(dto.identidadeGenero);
        usuario.setDdd(dto.ddd);
        usuario.setTelefone(dto.telefone);
        usuario.setEmail(dto.email);
        usuario.setSenha(dto.senha); // Criptografar depois!
        usuario.setAreaArtistica(dto.areaArtistica);
        usuario.setBio(dto.bio);

        return usuarioRepository.save(usuario);
    }

    public boolean emailExiste(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }
}