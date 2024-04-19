package matteofurgani.u5w3d5.services;

import matteofurgani.u5w3d5.entities.Utente;
import matteofurgani.u5w3d5.exceptions.UnauthorizedException;
import matteofurgani.u5w3d5.payloads.utente.UtenteLoginDTO;
import matteofurgani.u5w3d5.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private PasswordEncoder bcrypt;

    public String authenticateDipendenteAndGenerateToken(UtenteLoginDTO payload){

        Utente utente = this.utenteService.findByEmail(payload.email());

        if(bcrypt.matches(payload.password(), utente.getPassword())) {
            return jwtTools.createToken(utente);
        } else {
            throw new UnauthorizedException("Credenziali non valide");
        }
    }
}
