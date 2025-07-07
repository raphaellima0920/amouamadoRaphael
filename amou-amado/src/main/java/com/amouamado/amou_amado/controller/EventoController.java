package com.amouamado.amou_amado.controller;


import com.amouamado.amou_amado.dto.EventoDto;
import com.amouamado.amou_amado.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@RestController
@RequestMapping("/api/eventos")
@CrossOrigin(origins = "*")
public class EventoController {

    @Autowired
    private EventoService eventoService;

@PostMapping
public ResponseEntity<EventoDto> criarEvento(@RequestBody EventoDto eventoDto) {
    EventoDto salvo = eventoService.criarEvento(eventoDto);
    return ResponseEntity.ok(salvo);
}




    @GetMapping("/{id}")
    public ResponseEntity<EventoDto> buscarPorId(@PathVariable Long id) {
        EventoDto dto = eventoService.buscarPorId(id);
        return ResponseEntity.ok(dto);
    }

 @GetMapping("/usuario/{idUsuario}")
public ResponseEntity<List<EventoDto>> listarEventosPorUsuario(@PathVariable Long idUsuario) {
    List<EventoDto> eventos = eventoService.listarPorUsuario(idUsuario);
    return ResponseEntity.ok(eventos);
}



    @GetMapping
    public List<EventoDto> listarEventos() {
        return eventoService.listarTodos();
    }

    @GetMapping("/categoria/{categoria}")
public List<EventoDto> listarPorCategoria(@PathVariable String categoria) {
    return eventoService.buscarPorCategoria(categoria);
}

@GetMapping("/proximos")
public ResponseEntity<List<EventoDto>> listarEventosFuturos() {
    List<EventoDto> eventos = eventoService.listarEventosFuturos();
    return ResponseEntity.ok(eventos);
}



}