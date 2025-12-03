package via.pro3.station_server_3.Model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface TrayRepository extends JpaRepository<Tray, Integer> {

    Tray findDistinctById(Integer id);

    @Query("SELECT t FROM Tray t WHERE " +
            "t.id = COALESCE(:id, t.id) AND " +
            "t.maxWeightCapacityKg = COALESCE(:maxWeightCapacityKg, t.maxWeightCapacityKg) AND " +
            "t.partType.id = COALESCE(:partTypeId, t.partType.id)")
    List<Tray> findByOptionalParameters(
            @Param("id") Integer id,
            @Param("maxWeightCapacityKg") BigDecimal maxWeightCapacityKg,
            @Param("partTypeId") Integer partTypeId);
}