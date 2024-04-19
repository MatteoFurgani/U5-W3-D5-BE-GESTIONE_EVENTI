package matteofurgani.u5w3d5.repositories;

import matteofurgani.u5w3d5.entities.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventoDAO extends JpaRepository<Evento, Integer> {
    List<Evento> findByTitolo(String titolo);
}
