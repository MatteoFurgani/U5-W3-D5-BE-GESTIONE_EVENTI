package matteofurgani.u5w3d5.payloads.evento;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record NewEventoDTO(
                           @NotEmpty(message = "Il titolo è obbligatorio")
                           String titolo,
                           @NotEmpty(message = "Il titolo è obbligatorio")
                           @Size(min = 15, max = 250, message = "Il nome deve avere minimo 50 caratteri ed un massimo di 250")
                           String descrizione,
                           @NotEmpty(message = "Il luogo è obbligatorio")
                           String luogo,
                           @NotNull(message = "La data è obbligatoria")
                           LocalDate data,
                           @NotNull(message = "Il numero massimo di posti è obbligatorio")
                           int maxPosti) {
}
