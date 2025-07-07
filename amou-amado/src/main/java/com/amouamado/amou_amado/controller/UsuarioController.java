package com.amouamado.amou_amado.controller;

import com.amouamado.amou_amado.model.Usuario;
import com.amouamado.amou_amado.repository.UsuarioRepository;
//import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.amouamado.amou_amado.validation.ValidationGroups;
import org.springframework.validation.annotation.Validated;

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
@PutMapping("/{id}")
public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, @RequestBody Usuario atualizado) {
    return usuarioRepository.findById(id).map(usuario -> {

        if (atualizado.getNomeCompleto() != null) usuario.setNomeCompleto(atualizado.getNomeCompleto());
        if (atualizado.getNomeSocial() != null) usuario.setNomeSocial(atualizado.getNomeSocial());
        if (atualizado.getPronome() != null) usuario.setPronome(atualizado.getPronome());
        if (atualizado.getIdentidadeGenero() != null) usuario.setIdentidadeGenero(atualizado.getIdentidadeGenero());
        if (atualizado.getDdd() != null) usuario.setDdd(atualizado.getDdd());
        if (atualizado.getTelefone() != null) usuario.setTelefone(atualizado.getTelefone());
        if (atualizado.getEmail() != null) usuario.setEmail(atualizado.getEmail());
        if (atualizado.getSenha() != null) usuario.setSenha(atualizado.getSenha());
        if (atualizado.getAreaArtistica() != null) usuario.setAreaArtistica(atualizado.getAreaArtistica());
        if (atualizado.getBio() != null) usuario.setBio(atualizado.getBio());
        if (atualizado.getAvatarUrl() != null) usuario.setAvatarUrl(atualizado.getAvatarUrl());
        if (atualizado.getCoverUrl() != null) usuario.setCoverUrl(atualizado.getCoverUrl());
        if (atualizado.getLocalizacao() != null) usuario.setLocalizacao(atualizado.getLocalizacao());
        if (atualizado.getMembroDesde() != null) usuario.setMembroDesde(atualizado.getMembroDesde());
        if (atualizado.getEventosRealizados() != null) usuario.setEventosRealizados(atualizado.getEventosRealizados());
        if (atualizado.getWebsite() != null) usuario.setWebsite(atualizado.getWebsite());
        if (atualizado.getInstagram() != null) usuario.setInstagram(atualizado.getInstagram());
        if (atualizado.getFacebook() != null) usuario.setFacebook(atualizado.getFacebook());
        if (atualizado.getTwitter() != null) usuario.setTwitter(atualizado.getTwitter());
        if (atualizado.getLinkedin() != null) usuario.setLinkedin(atualizado.getLinkedin());

        Usuario salvo = usuarioRepository.save(usuario);
        return ResponseEntity.ok(salvo);

    }).orElse(ResponseEntity.notFound().build());
}

}
