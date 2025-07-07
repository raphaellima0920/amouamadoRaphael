package com.amouamado.amou_amado.controller;

import com.amouamado.amou_amado.dto.LoginRequest;
import com.amouamado.amou_amado.model.Usuario;
import com.amouamado.amou_amado.repository.UsuarioRepository;
import com.amouamado.amou_amado.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Usuario usuario = usuarioRepository.findByEmail(loginRequest.getEmail());

        if (usuario != null && usuario.getSenha().equals(loginRequest.getSenha())) {
            String token = jwtUtil.gerarToken(usuario.getEmail(), usuario.getId());

            // Criar resposta personalizada com token e ID
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("id", usuario.getId());
            response.put("email", usuario.getEmail());
            response.put("nome", usuario.getNomeCompleto()); // opcional

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).body("E-mail ou senha incorretos");
        }
    }
}
