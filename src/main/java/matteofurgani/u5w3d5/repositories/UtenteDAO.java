package matteofurgani.u5w3d5.repositories;

import matteofurgani.u5w3d5.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtenteDAO extends JpaRepository<Utente, Integer> {

    boolean existsByEmail(String email);
    Optional<Utente> findByEmail(String email);
}
