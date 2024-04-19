package matteofurgani.u5w3d5.repositories;

import matteofurgani.u5w3d5.entities.Evento;
import matteofurgani.u5w3d5.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventoDAO extends JpaRepository<Evento, Integer> {
    List<Evento> findByTitolo(String titolo);

}
