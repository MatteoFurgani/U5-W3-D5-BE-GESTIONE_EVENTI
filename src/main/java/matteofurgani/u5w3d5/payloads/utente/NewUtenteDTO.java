package matteofurgani.u5w3d5.payloads.utente;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record NewUtenteDTO(
                           @NotEmpty(message = "Il nome non può essere vuoto")
                           @Size(min = 3, max = 15, message = "Il nome deve avere minimo 3 caratteri ed un massimo di 15")
                           String nome,
                           @NotEmpty(message = "Il cognome non può essere vuoto")
                           @Size(min = 3, max = 15, message = "Il nome deve avere minimo 3 caratteri ed un massimo di 15")
                           String cognome,
                           @NotEmpty(message = "L'email non può essere vuoto")
                           String email,
                           @NotEmpty(message = "La password non può essere vuota")
                           @Size(min = 3, max = 15, message = "La password deve avere minimo 3 caratteri ed un massimo di 15")
                           String password) {
}
