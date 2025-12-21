package via.pro3.station_server_3.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import via.pro3.station_server_3.Model.*;
import via.pro3.station_server_3.Model.Package; // Explicit import to avoid conflicts
import via.pro3.station_server_3.Utils.QueueHandler;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@RestController
public class RegistrationController {

    private final static String PACKAGE_QUEUE = "register_package";
    private final static String HALF_ANIMAL_QUEUE = "register_half_animal";

    private final PackageRepository packageRepository;
    private final ProductRepository productRepository;
    private final HalfAnimalRepository halfAnimalRepository;
    private final QueueHandler queueHandler;

    public RegistrationController(PackageRepository packageRepository,
            ProductRepository productRepository,
            HalfAnimalRepository halfAnimalRepository,
            QueueHandler queueHandler) {
        this.packageRepository = packageRepository;
        this.productRepository = productRepository;
        this.halfAnimalRepository = halfAnimalRepository;
        this.queueHandler = queueHandler;
    }

    @PostMapping("/packages")
    public ResponseEntity<Object> registerPackage(@RequestBody PackageRegistrationDto dto) {
        try {
            Package newPackage = new Package();
            newPackage.setProductName(dto.getProductName());
            newPackage.setTrayIds(dto.getTrayIds());
            newPackage.setAmount(dto.getAmount());
            newPackage.setPartTypeId(dto.getPartTypeId());

            Package savedPackage = packageRepository.save(newPackage);

            try {
                queueHandler.addToQueue(PACKAGE_QUEUE, savedPackage);
            } catch (IOException | TimeoutException e) {
                System.err.println("Error publishing Package to queue: " + e.getMessage());
            }

            return ResponseEntity.ok(savedPackage);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/halfanimals")
    @ResponseStatus(HttpStatus.CREATED)
    public HalfAnimal registerHalfAnimal(@RequestBody HalfAnimalRegistrationDto dto) {
        HalfAnimal halfAnimal = new HalfAnimal();
        halfAnimal.setProductName(dto.getProductName());

        halfAnimal.setTrayIds(dto.getTrayIds());

        HalfAnimal savedHalfAnimal = halfAnimalRepository.save(halfAnimal);

        try {
            queueHandler.addToQueue(HALF_ANIMAL_QUEUE, savedHalfAnimal);
        } catch (IOException | TimeoutException e) {
            System.err.println("Error publishing HalfAnimal to queue: " + e.getMessage());
        }

        return savedHalfAnimal;
    }
}