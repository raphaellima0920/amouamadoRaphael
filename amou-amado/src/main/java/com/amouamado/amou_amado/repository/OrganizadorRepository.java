package com.amouamado.amou_amado.repository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.amouamado.amou_amado.model.Organizador;

@Repository
public interface OrganizadorRepository extends JpaRepository<Organizador, Long> {

       Optional<Organizador> findByEmail(String email);

}
