package com.amouamado.amou_amado.controller;

import com.amouamado.amou_amado.model.Usuario;
import com.amouamado.amou_amado.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Cadastro com validação de grupo
    @PostMapping
    public ResponseEntity<?> cadastrarUsuario(@Validated(ValidationGroups.OnCreate.class) @RequestBody Usuario usuario) {
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            return ResponseEntity.badRequest().body("Email já cadastrado");
        }

        usuario.setMembroDesde(LocalDate.now());
        Usuario salvo = usuarioRepository.save(usuario);
        return ResponseEntity.ok(salvo);
    }
    // Login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario loginData) {
        Usuario usuario = usuarioRepository.findByEmail(loginData.getEmail());
        if (usuario == null) {
            return ResponseEntity.status(401).body("Usuário não encontrado.");
        }

        if (!usuario.getSenha().equals(loginData.getSenha())) {
            return ResponseEntity.status(401).body("Senha incorreta.");
        }

        return ResponseEntity.ok(Map.of(
            "token", "fake-token-123456",  // Substituir por JWT depois
            "nome", usuario.getNomeCompleto(),
            "id", usuario.getId(),
            "email", usuario.getEmail()
        ));
    }

    // Listagem geral
    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Atualização de perfil
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, @RequestBody Usuario atualizado) {
        return usuarioRepository.findById(id).map(usuario -> {
            usuario.setNomeCompleto(atualizado.getNomeCompleto());
            usuario.setNomeSocial(atualizado.getNomeSocial());
            usuario.setPronome(atualizado.getPronome());
            usuario.setIdentidadeGenero(atualizado.getIdentidadeGenero());
            usuario.setDdd(atualizado.getDdd());
            usuario.setTelefone(atualizado.getTelefone());
            usuario.setEmail(atualizado.getEmail());
            usuario.setSenha(atualizado.getSenha());
            usuario.setAreaArtistica(atualizado.getAreaArtistica());
            usuario.setBio(atualizado.getBio());
            usuario.setAvatarUrl(atualizado.getAvatarUrl());
            usuario.setCoverUrl(atualizado.getCoverUrl());
            usuario.setLocalizacao(atualizado.getLocalizacao());
            usuario.setMembroDesde(atualizado.getMembroDesde());
            usuario.setEventosRealizados(atualizado.getEventosRealizados());
            usuario.setWebsite(atualizado.getWebsite());
            usuario.setInstagram(atualizado.getInstagram());
            usuario.setFacebook(atualizado.getFacebook());
            usuario.setTwitter(atualizado.getTwitter());
            usuario.setLinkedin(atualizado.getLinkedin());

            Usuario salvo = usuarioRepository.save(usuario);
            return ResponseEntity.ok(salvo);
        }).orElse(ResponseEntity.notFound().build());
    }
}
