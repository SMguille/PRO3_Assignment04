package via.pro3.central_registration_server.Model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository public interface HalfAnimalRepository extends JpaRepository<HalfAnimal, Integer>
{
}
