package matteofurgani.u5w3d5.controllers;

import matteofurgani.u5w3d5.entities.Evento;
import matteofurgani.u5w3d5.exceptions.BadRequestException;
import matteofurgani.u5w3d5.payloads.evento.NewEventoDTO;
import matteofurgani.u5w3d5.payloads.evento.NewEventoRespDTO;
import matteofurgani.u5w3d5.services.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("eventi")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    // POST http://localhost:3001/eventi (+ req.body)

    @PostMapping
    @PreAuthorize("hasAuthority('ORGANIZZATORE')")
    @ResponseStatus(HttpStatus.CREATED)
    public NewEventoRespDTO save(@RequestBody @Validated NewEventoDTO body, BindingResult validation) throws Exception{
        if (validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        }
        Evento evento = eventoService.save(body);
        return new NewEventoRespDTO(evento.getId());
    }

    // GET http://localhost:3001/eventi

    @GetMapping
    public Page<Evento> getDispositivi(@RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int size,
                                            @RequestParam(defaultValue = "id") String sort){
        return eventoService.getEvento(page, size, sort);
    }

    // GET http://localhost:3001/dispositivi/{id}

    @GetMapping("/{eventoId}")
    public Evento findById(@PathVariable int eventoId) {

        return eventoService.findById(eventoId);
    }

    // PUT http://localhost:3001/eventi/{id}

    @PutMapping("/{eventoId}")
    @PreAuthorize("hasAuthority('ORGANIZZATORE')")
    public Evento findByIdAndUpdate(@PathVariable int eventoID, @RequestBody Evento body) {
        return eventoService.findByIdAndUpdate(eventoID, body);
    }

    // DELETE http://localhost:3001/eventi/{id}

    @DeleteMapping("/{eventoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ORGANIZZATORE')")
    public void findByIdAndDelete(@PathVariable int eventoId) {
        eventoService.findByIdAndDelete(eventoId);
    }

    // GET http://localhost:3001/eventi/{eventoid}/{utenteid}


    @PostMapping("/{eventoId}/{utenteId}")
    public Evento findByIdAndUtente(@PathVariable int eventoId, @PathVariable int utenteId) throws IOException {
        Evento evento = eventoService.findById(eventoId);
        return eventoService.findByIdAndAddUtente(eventoId, utenteId);
    }


}
