package fatec.grupodois.endurance.repository;

import fatec.grupodois.endurance.entity.Evento;
import fatec.grupodois.endurance.entity.StatusEvento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

    @Query("SELECT s from Evento s WHERE s.eventoStatus = ?1")
    Optional<List<Evento>> findEventoByStatus(StatusEvento status);

    @Query("SELECT s from Evento s WHERE s.eventoInicio = ?1")
    Optional<List<Evento>> findEventoByDateTime(LocalDateTime date);

    @Query("SELECT s from Evento s WHERE trunc(s.eventoInicio) = ?1")
    Optional<List<Evento>> findEventoByDate(LocalDate date);
}
