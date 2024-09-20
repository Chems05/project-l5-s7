package controller;

import Entities.Evento;
import Entities.Utente;
import services.EventoService;
import services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/eventi")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @Autowired
    private UtenteService utenteService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ORGANIZER')")
    public ResponseEntity<Evento> createEvent(@RequestBody Evento evento, Principal principal) {
        Utente organizzatore = utenteService.findByUsername(principal.getName()).orElseThrow();
        return ResponseEntity.ok(eventoService.createEvent(evento, organizzatore));
    }

    @GetMapping("/available")
    public ResponseEntity<List<Evento>> getAvailableEvents() {
        return ResponseEntity.ok(eventoService.getAvailableEvents());
    }
}
