package services;

import Entities.Prenotazione;
import Entities.Evento;
import Entities.Utente;
import repositories.PrenotazioneRepository;
import repositories.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private EventoRepository eventoRepository;

    public Prenotazione bookEvent(UUID eventId, Utente utente) {
        Evento evento = eventoRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Evento non trovato"));

        if (evento.getPostiDisponibili() > 0) {
            evento.setPostiDisponibili(evento.getPostiDisponibili() - 1);
            Prenotazione prenotazione = new Prenotazione();
            prenotazione.setEvento(evento);
            prenotazione.setUtente(utente);
            prenotazione.setDataPrenotazione(LocalDateTime.now());

            return prenotazioneRepository.save(prenotazione);
        } else {
            throw new RuntimeException("Non ci sono posti disponibili");
        }
    }
}
