package via.pro3.station_server_2.Model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository public interface TrayRepository extends JpaRepository<Tray, Integer>
 {
}
