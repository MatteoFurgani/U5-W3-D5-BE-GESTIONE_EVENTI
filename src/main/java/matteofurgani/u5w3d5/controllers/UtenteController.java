package matteofurgani.u5w3d5.controllers;

import matteofurgani.u5w3d5.entities.Utente;
import matteofurgani.u5w3d5.exceptions.BadRequestException;
import matteofurgani.u5w3d5.payloads.utente.NewUtenteDTO;
import matteofurgani.u5w3d5.payloads.utente.NewUtenteRespDTO;
import matteofurgani.u5w3d5.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/utenti")
public class UtenteController {

    @Autowired
    private UtenteService utenteService;

    // POST http://localhost:3001/dipendenti (+ req.body)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NewUtenteRespDTO saveUtente(@RequestBody @Validated NewUtenteDTO body, BindingResult validation) throws Exception{
        if (validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        }

        Utente utente = utenteService.save(body);
        return new NewUtenteRespDTO(utente.getId());
    }
}
