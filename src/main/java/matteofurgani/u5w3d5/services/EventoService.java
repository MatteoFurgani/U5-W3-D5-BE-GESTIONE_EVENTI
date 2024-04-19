package matteofurgani.u5w3d5.services;

import matteofurgani.u5w3d5.entities.Evento;
import matteofurgani.u5w3d5.entities.Utente;
import matteofurgani.u5w3d5.exceptions.BadRequestException;
import matteofurgani.u5w3d5.exceptions.NotFoundException;
import matteofurgani.u5w3d5.payloads.evento.NewEventoDTO;
import matteofurgani.u5w3d5.repositories.EventoDAO;
import matteofurgani.u5w3d5.repositories.UtenteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
public class EventoService {

    @Autowired
    private EventoDAO eventoDAO;
    @Autowired
    private UtenteDAO utenteDAO;

    @Autowired
    private UtenteService utenteService;

    public Evento save(NewEventoDTO body) throws IOException {
        Evento evento = new Evento(body.titolo(), body.descrizione(), body.data(), body.luogo(), body.maxPosti());
        evento.setTitolo(body.titolo());
        evento.setDescrizione(body.descrizione());
        evento.setData(body.data());
        evento.setLuogo(body.luogo());
        evento.setMaxPosti(body.maxPosti());
        return eventoDAO.save(evento);
    }

    public Page<Evento> getEvento(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return eventoDAO.findAll(pageable);
    }

    public Evento findById(int id) {
        return eventoDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Evento findByIdAndUpdate(int id, Evento body) {
        Evento found = this.findById(id);
        found.setTitolo(body.getTitolo());
        found.setDescrizione(body.getDescrizione());
        found.setData(body.getData());
        found.setLuogo(body.getLuogo());
        found.setMaxPosti(body.getMaxPosti());
        return eventoDAO.save(found);
    }

    public void findByIdAndDelete(int id) {
        Evento found = this.findById(id);
        eventoDAO.delete(found);
    }

    public Evento findByIdAndAddUtente(int eventoId, int utenteId) throws IOException {
        Utente utente = utenteService.findById(utenteId);
        Evento evento = this.findById(eventoId);
        if (Objects.isNull(utente)) {
            throw new NotFoundException(utenteId);
        }
        if (Objects.isNull(evento)) {
            throw new NotFoundException(eventoId);
        }
        if (evento.getUtenti().contains(utente)) {
            throw new BadRequestException("Utente già presente nel evento");
        }
        evento.getUtenti().add(utente);
        utente.getEventi().add(evento);
        utenteDAO.save(utente);

        return eventoDAO.save(evento);
    }



}
