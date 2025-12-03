package via.pro3.station_server_3.Model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartRepository extends JpaRepository<Part, Integer> {

    Part findDistinctById(Integer id);

    @Query("SELECT p FROM Part p WHERE " +
            "p.id = COALESCE(:id, p.id) AND " +
            "p.animalId = COALESCE(:animalId, p.animalId) AND " +
            "p.tray.id = COALESCE(:trayId, p.tray.id) AND " +
            "p.partType.id = COALESCE(:partTypeId, p.partType.id)")
    List<Part> findByOptionalParameters(
            @Param("id") Integer id,
            @Param("animalId") Integer animalId,
            @Param("trayId") Integer trayId,
            @Param("partTypeId") Integer partTypeId);
}