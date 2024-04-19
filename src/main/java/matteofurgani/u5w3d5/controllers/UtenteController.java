package matteofurgani.u5w3d5.controllers;

import matteofurgani.u5w3d5.entities.Utente;
import matteofurgani.u5w3d5.exceptions.BadRequestException;
import matteofurgani.u5w3d5.payloads.utente.NewUtenteDTO;
import matteofurgani.u5w3d5.payloads.utente.NewUtenteRespDTO;
import matteofurgani.u5w3d5.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/utenti")
public class UtenteController {

    @Autowired
    private UtenteService utenteService;


    // GET http://localhost:3001/utenti
    @GetMapping
    @PreAuthorize("hasAuthority('ORGANIZZATORE')")
    public Page<Utente> getUtenti(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size,
                                          @RequestParam(defaultValue = "id") String sort) {
        return utenteService.getUtente(page, size, sort);
    }

    @GetMapping("/me")
    public Utente getProfile(@AuthenticationPrincipal Utente currentAuthenticatedUtente) {
        return currentAuthenticatedUtente;
    }

    @DeleteMapping("/me")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProfile(@AuthenticationPrincipal Utente currentAuthenticatedUtente){
        this.utenteService.findByIDAndDelete(currentAuthenticatedUtente.getId());
    }

    // GET http://localhost:3001/utenti/{id}

    @GetMapping("/{utentiId}")
    @PreAuthorize("hasAuthority('ORGANIZZATORE')")
    public Utente findById(@PathVariable int utentiId) {

        return utenteService.findById(utentiId);
    }

    // PUT http://localhost:3001/utenti/{id} (+ req.body)

    @PutMapping("/{utentiId}")
    public Utente findAndUpdate(@PathVariable int utentiId, @RequestBody Utente body){
        return utenteService.findByIdAndUpdate(utentiId, body);
    }

    // DELETE http://localhost:3001/utenti/{id}

    @DeleteMapping("/{utentiId}")
    @PreAuthorize("hasAuthority('ORGANIZZATORE')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<String> findByIdAndDelete(@PathVariable int utenteId) {

        utenteService.findByIDAndDelete(utenteId);
        return ResponseEntity.ok("L'utente è stato cancellato con successo");
    }
}
