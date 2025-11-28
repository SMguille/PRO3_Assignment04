package via.pro3.station_server.Model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository public interface AnimalRepository
    extends JpaRepository<Animal, Integer>
{
  Animal findDistinctById(Integer id);
  @Query("SELECT a FROM Animal a WHERE " +
      "a.id = COALESCE(:id, a.id) AND " +
      "a.origin = COALESCE(:origin, a.origin) AND " +
      "a.arrivalAt = COALESCE(:arrivalAt, a.arrivalAt)")
  List<Animal> findByOptionalParameters(
      @Param("id") Integer id,
      @Param("origin") Integer origin,
      @Param("arrivalAt") LocalDate arrivalAt);
}
