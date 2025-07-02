package com.amouamado.amou_amado.controller;

import com.amouamado.amou_amado.dto.OrganizadorDTO;
import com.amouamado.amou_amado.service.OrganizadorService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/organizadores")
public class OrganizadorController {

    @Autowired
    private OrganizadorService organizadorService;

    @GetMapping("/{id}")
    public ResponseEntity<OrganizadorDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(organizadorService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrganizadorDTO> atualizar(@PathVariable Long id, @RequestBody OrganizadorDTO dto) {
        return ResponseEntity.ok(organizadorService.atualizar(id, dto));
    }

    @GetMapping
    public ResponseEntity<List<OrganizadorDTO>> listarOrganizadores() {
    return ResponseEntity.ok(organizadorService.listarTodos());
}
}