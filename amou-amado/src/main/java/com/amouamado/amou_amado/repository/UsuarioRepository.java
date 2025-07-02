package com.amouamado.amou_amado.repository;

import com.amouamado.amou_amado.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByEmail(String email);
    Usuario findByEmail(String email); // <-- Adicione esta linha
}