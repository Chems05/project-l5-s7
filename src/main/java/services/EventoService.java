package services;

import Entities.Evento;
import Entities.Utente;
import repositories.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    public Evento createEvent(Evento evento, Utente organizzatore) {
        evento.setOrganizzatore(organizzatore);
        return eventoRepository.save(evento);
    }

    public List<Evento> getAvailableEvents() {
        return eventoRepository.findByPostiDisponibiliGreaterThan(0);
    }
}
