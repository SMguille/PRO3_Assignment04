package via.pro3.station_server_3.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import via.pro3.station_server_3.Model.*;
import via.pro3.station_server_3.Model.Package;
import via.pro3.station_server_3.Utils.QueueHandler;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeoutException;

@RestController
public class RegistrationController {

    private final static String PACKAGE_QUEUE = "register_package";
    private final static String HALF_ANIMAL_QUEUE = "register_half_animal";

    private final PackageRepository packageRepository;
    private final HalfAnimalRepository halfAnimalRepository;
    private final TrayRepository trayRepository;
    private final QueueHandler queueHandler;

    public RegistrationController(PackageRepository packageRepository,
                                  HalfAnimalRepository halfAnimalRepository,
                                  TrayRepository trayRepository,
                                  QueueHandler queueHandler) {
        this.packageRepository = packageRepository;
        this.halfAnimalRepository = halfAnimalRepository;
        this.trayRepository = trayRepository;
        this.queueHandler = queueHandler;
    }

    @PostMapping("/packages")
    @ResponseStatus(HttpStatus.CREATED)
    public Package registerPackage(@RequestBody PackageRegistrationDto dto) {
        // 1. Find Trays
        List<Tray> traysList = trayRepository.findAllById(dto.getTrayIds());
        if (traysList.size() != dto.getTrayIds().size()) {
            throw new IllegalArgumentException("One or more Tray IDs not found.");
        }
        Set<Tray> trays = new HashSet<>(traysList);

        // 2. Create Package Entity
        Package pkg = new Package();
        pkg.setProductName(dto.getProductName());
        pkg.setAmount(dto.getAmount());
        pkg.setPartTypeId(dto.getPartTypeId());
        pkg.setTrays(trays);

        // 3. Save to DB
        Package savedPackage = packageRepository.save(pkg);

        // 4. Send to Queue
        try {
            queueHandler.addToQueue(PACKAGE_QUEUE, savedPackage);
        } catch (IOException | TimeoutException e) {
            System.err.println("Error publishing Package to queue: " + e.getMessage());
        }

        return savedPackage;
    }

    @PostMapping("/halfanimals")
    @ResponseStatus(HttpStatus.CREATED)
    public HalfAnimal registerHalfAnimal(@RequestBody HalfAnimalRegistrationDto dto) {
        // 1. Find Trays
        List<Tray> traysList = trayRepository.findAllById(dto.getTrayIds());
        if (traysList.size() != dto.getTrayIds().size()) {
            throw new IllegalArgumentException("One or more Tray IDs not found.");
        }
        Set<Tray> trays = new HashSet<>(traysList);

        // 2. Create HalfAnimal Entity
        HalfAnimal halfAnimal = new HalfAnimal();
        halfAnimal.setProductName(dto.getProductName());
        halfAnimal.setTrays(trays);

        // 3. Save to DB
        HalfAnimal savedHalfAnimal = halfAnimalRepository.save(halfAnimal);

        // 4. Send to Queue
        try {
            queueHandler.addToQueue(HALF_ANIMAL_QUEUE, savedHalfAnimal);
        } catch (IOException | TimeoutException e) {
            System.err.println("Error publishing HalfAnimal to queue: " + e.getMessage());
        }

        return savedHalfAnimal;
    }
}