package via.pro3.central_registration_server;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import via.pro3.central_registration_server.Model.*; // Your copied JPA entities

@Service public class CentralConsumer
{

  private final PartRepository partRepository;
  private final TrayRepository trayRepository;
  private final PartTypeRepository partTypeRepository;

  public CentralConsumer(PartRepository partRepository,
      TrayRepository trayRepository, PartTypeRepository partTypeRepository)
  {
    this.partRepository = partRepository;
    this.trayRepository = trayRepository;
    this.partTypeRepository = partTypeRepository;
  }

  @RabbitListener(queues = "register_part_type") public void receivePartType(
      PartType partType)
  {
    try
    {
      partTypeRepository.save(partType);
      System.out.println("Central DB: Synced PartType " + partType.getId());
    }
    catch (Exception e)
    {
      System.err.println("Error saving PartType: " + e.getMessage());
    }
  }

  @RabbitListener(queues = "register_tray") public void receiveTray(Tray tray)
  {
    try
    {
      trayRepository.save(tray);
      System.out.println("Central DB: Synced Tray " + tray.getId());
    }
    catch (DataIntegrityViolationException e)
    {
      System.err.println(
          "Missing dependency for Tray " + tray.getId() + ". Requeueing...");
      throw new RuntimeException("Requeue Tray", e);
    }
  }

  @RabbitListener(queues = "register_part") @Transactional public void receivePart(
      Part part)
  {
    try
    {
      partRepository.save(part);
      System.out.println("Central DB: Synced Part " + part.getId());
    }
    catch (DataIntegrityViolationException e)
    {
      System.err.println("Foreign Key missing for Part " + part.getId()
          + " (Tray/Animal not found). Requeueing...");
      throw new RuntimeException("Requeue Part", e);
    }
    catch (Exception e)
    {
      System.err.println("Fatal error saving Part: " + e.getMessage());
    }
  }
}