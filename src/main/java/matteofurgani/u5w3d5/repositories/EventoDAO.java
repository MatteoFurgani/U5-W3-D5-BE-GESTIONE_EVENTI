package matteofurgani.u5w3d5.repositories;

import matteofurgani.u5w3d5.entities.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventoDAO extends JpaRepository<Evento, Integer> {
    List<Evento> findByTitolo(String titolo);
}
