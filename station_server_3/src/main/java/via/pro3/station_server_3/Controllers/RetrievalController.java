package via.pro3.station_server_3.Controllers;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import via.pro3.station_server_3.Model.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
public class RetrievalController {
    private final AnimalRepository animalRepository;
    private final TrayRepository trayRepository;
    private final PartRepository partRepository;

    public RetrievalController(AnimalRepository animalRepository,
                               TrayRepository trayRepository,
                               PartRepository partRepository) {
        this.animalRepository = animalRepository;
        this.trayRepository = trayRepository;
        this.partRepository = partRepository;
    }

    @GetMapping("/animals")
    public List<Animal> getAnimals(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) Integer origin,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate arrivalAt) {
        return animalRepository.findByOptionalParameters(id, origin, arrivalAt);
    }

    @GetMapping("/animals/{id}")
    public Animal getAnimalById(@PathVariable int id) {
        return animalRepository.findDistinctById(id);
    }

    @GetMapping("/trays")
    public List<Tray> getTrays(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) BigDecimal maxWeightCapacityKg,
            @RequestParam(required = false) Integer partTypeId) {
        return trayRepository.findByOptionalParameters(id, maxWeightCapacityKg, partTypeId);
    }

    @GetMapping("/trays/{id}")
    public Tray getTrayById(@PathVariable int id) {
        return trayRepository.findDistinctById(id);
    }

    @GetMapping("/parts")
    public List<Part> getParts(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) Integer animalId,
            @RequestParam(required = false) Integer trayId,
            @RequestParam(required = false) Integer partTypeId) {
        return partRepository.findByOptionalParameters(id, animalId, trayId, partTypeId);
    }

    @GetMapping("/parts/{id}")
    public Part getPartById(@PathVariable int id) {
        return partRepository.findDistinctById(id);
    }
}