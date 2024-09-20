package controller;

import Entities.Prenotazione;
import Entities.Utente;
import services.PrenotazioneService;
import services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping("/api/prenotazioni")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;

    @Autowired
    private UtenteService utenteService;

    @PostMapping("/book/{eventoId}")
    public ResponseEntity<Prenotazione> bookEvent(@PathVariable UUID eventoId, Principal principal) {
        Utente utente = utenteService.findByUsername(principal.getName()).orElseThrow();
        return ResponseEntity.ok(prenotazioneService.bookEvent(eventoId, utente));
    }
}
