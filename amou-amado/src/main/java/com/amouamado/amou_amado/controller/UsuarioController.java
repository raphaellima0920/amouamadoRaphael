package com.amouamado.amou_amado.controller;

import java.util.Map;
import com.amouamado.amou_amado.model.Usuario;
import com.amouamado.amou_amado.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<?> cadastrarUsuario(@Valid @RequestBody Usuario usuario) {
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            return ResponseEntity.badRequest().body("Email já cadastrado");
        }

        usuario.setMembroDesde(LocalDate.now());
        Usuario salvo = usuarioRepository.save(usuario);
        return ResponseEntity.ok(salvo);
    }

    @PostMapping("/login")
public ResponseEntity<?> login(@RequestBody Usuario loginData) {
    // 1. Buscar usuário pelo e-mail
    Usuario usuario = usuarioRepository.findByEmail(loginData.getEmail());
    if (usuario == null) {
        return ResponseEntity.status(401).body("Usuário não encontrado.");
    }

    // 2. Comparar senha digitada com a salva (sem criptografia)
    if (!usuario.getSenha().equals(loginData.getSenha())) {
        return ResponseEntity.status(401).body("Senha incorreta.");
    }

    // 3. Retornar algo útil (pode ser um "token" fake para MVP ou o nome do usuário)
    return ResponseEntity.ok(Map.of(
    "token", "fake-token-123456", // token fake, por enquanto
    "nome", usuario.getNomeCompleto(),
    "id", usuario.getId(),
    "email", usuario.getEmail()
));
}

    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }
}