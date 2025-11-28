package via.pro3.station_server_2.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import via.pro3.station_server_2.Model.*;
import via.pro3.station_server_2.Utils.QueueHandler;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@RestController public class RegistrationController
{
  private final static String PART_TYPE_QUEUE = "register_part_type";
  private final static String TRAY_QUEUE = "register_tray";
  private final static String PART_QUEUE = "register_part";

  private final PartRepository partRepository;
  private final PartTypeRepository partTypeRepository;
  private final TrayRepository trayRepository;
  private final QueueHandler queueHandler;

  private final TrayRepository trayLookupRepository;
  private final PartTypeRepository partTypeLookupRepository;

  public RegistrationController(PartRepository partRepository,
      PartTypeRepository partTypeRepository,
      PartTypeRepository partTypeLookupRepository,
      TrayRepository trayRepository, TrayRepository trayLookupRepository,
      QueueHandler queueHandler)
  {
    this.partRepository = partRepository;
    this.partTypeRepository = partTypeRepository;
    this.trayRepository = trayRepository;
    this.trayLookupRepository = trayLookupRepository;
    this.partTypeLookupRepository = partTypeLookupRepository;
    this.queueHandler = queueHandler;
  }

  @PostMapping("/parttypes") @ResponseStatus(HttpStatus.CREATED) public PartType addPartType(
      @RequestBody PartType partType)
  {
    try
    {
      PartType addedPartType = partTypeRepository.save(partType);
      queueHandler.addToQueue(PART_TYPE_QUEUE, addedPartType);
      return addedPartType;
    }
    catch (IOException | TimeoutException e)
    {
      throw new RuntimeException(
          "Error publishing PartType to queue: " + e.getMessage(), e);
    }
  }

  @PostMapping("/trays") @ResponseStatus(HttpStatus.CREATED) public Tray addTray(
      @RequestBody TrayRegistrationDto dto)
  {

    PartType partType = partTypeRepository.findById(dto.getPartsTypeId())
        .orElseThrow(() -> new IllegalArgumentException(
            "Part Type with ID " + dto.getPartsTypeId() + " not found."));

    Tray tray = new Tray();
    tray.setMaxWeightCapacityKg(dto.getMaxWeightCapacityKg());
    tray.setPartType(partType);

    try
    {
      Tray addedTray = trayRepository.save(tray);
      queueHandler.addToQueue(TRAY_QUEUE, addedTray);
      return addedTray;
    }
    catch (IOException | TimeoutException e)
    {
      System.err.println(
          "Warning: Tray successfully saved but failed to publish to queue: "
              + e.getMessage());
      return tray;
    }
  }

  @PostMapping("/parts") @ResponseStatus(HttpStatus.CREATED) public Part addPart(
      @RequestBody PartRegistrationDto dto)
  {
    PartType partType = partTypeLookupRepository.findById(dto.getPartTypeId())
        .orElseThrow(
            () -> new IllegalArgumentException("Invalid Part Type ID."));

    Tray tray = trayLookupRepository.findById(dto.getTrayId())
        .orElseThrow(() -> new IllegalArgumentException("Invalid Tray ID."));

    Part part = new Part();
    part.setWeightKg(dto.getWeightKg());

    part.setPartType(partType);
    part.setTray(tray);

    part.setAnimalId(dto.getAnimalId());

    Part addedPart = partRepository.save(part);

    try
    {
      queueHandler.addToQueue(PART_QUEUE, addedPart);
      return addedPart;
    }
    catch (IOException | TimeoutException e)
    {
      System.err.println(
          "Warning: Part successfully saved but failed to publish to queue: "
              + e.getMessage());
      return addedPart;
    }
  }
}