package matteofurgani.u5w3d5.controllers;

import matteofurgani.u5w3d5.exceptions.BadRequestException;
import matteofurgani.u5w3d5.payloads.utente.NewUtenteDTO;
import matteofurgani.u5w3d5.payloads.utente.NewUtenteRespDTO;
import matteofurgani.u5w3d5.payloads.utente.UtenteLoginDTO;
import matteofurgani.u5w3d5.payloads.utente.UtenteLoginRespDTO;
import matteofurgani.u5w3d5.services.AuthService;
import matteofurgani.u5w3d5.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UtenteService utenteService;

    @PostMapping("/login")
    public UtenteLoginRespDTO login(@RequestBody UtenteLoginDTO payload){

        return new UtenteLoginRespDTO(this.authService.authenticateDipendenteAndGenerateToken(payload));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public NewUtenteRespDTO saveUser(@RequestBody @Validated NewUtenteDTO body, BindingResult validation) throws IOException {

        if(validation.hasErrors()){
            throw new BadRequestException(validation.getAllErrors());
        }
        return new NewUtenteRespDTO(this.utenteService.save(body).getId());
    }
}
