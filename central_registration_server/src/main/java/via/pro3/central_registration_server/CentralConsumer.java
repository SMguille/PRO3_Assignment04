package via.pro3.central_registration_server;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager; // Your copied JPA entities
import jakarta.persistence.PersistenceContext;
import via.pro3.central_registration_server.Model.Animal;
import via.pro3.central_registration_server.Model.AnimalRepository;
import via.pro3.central_registration_server.Model.HalfAnimal;
import via.pro3.central_registration_server.Model.HalfAnimalRepository;
import via.pro3.central_registration_server.Model.Package;
import via.pro3.central_registration_server.Model.PackageRepository;
import via.pro3.central_registration_server.Model.Part;
import via.pro3.central_registration_server.Model.PartRepository;
import via.pro3.central_registration_server.Model.PartType;
import via.pro3.central_registration_server.Model.PartTypeRepository;
import via.pro3.central_registration_server.Model.Product;
import via.pro3.central_registration_server.Model.ProductRepository;
import via.pro3.central_registration_server.Model.Tray;
import via.pro3.central_registration_server.Model.TrayRepository;

@Service
public class CentralConsumer {

  @PersistenceContext
  private EntityManager entityManager;

  private final PartRepository partRepository;
  private final TrayRepository trayRepository;
  private final PartTypeRepository partTypeRepository;
  private final PackageRepository packageRepository;
  private final HalfAnimalRepository halfAnimalRepository;
  private final AnimalRepository animalRepository;
  private final ProductRepository productRepository;

  public CentralConsumer(PartRepository partRepository,
      TrayRepository trayRepository, PartTypeRepository partTypeRepository, PackageRepository packageRepository,
      HalfAnimalRepository halfAnimalRepository, AnimalRepository animalRepository, ProductRepository productRepository) {
    System.out.println("CentralConsumer: Initializing...");
    this.partRepository = partRepository;
    this.trayRepository = trayRepository;
    this.partTypeRepository = partTypeRepository;
    this.packageRepository = packageRepository;
    this.halfAnimalRepository = halfAnimalRepository;
    this.animalRepository = animalRepository;
    this.productRepository = productRepository;
    System.out.println("CentralConsumer: Initialized with all repositories.");
  }

  @RabbitListener(queues = "register_animal")
  @Transactional
  public void receiveAnimal(Animal animal) {
    System.out.println("CentralConsumer: Received Animal message: " + animal);
    try {
      if (animal.getId() != null && animalRepository.existsById(animal.getId())) {
        System.out.println("Central DB: Animal " + animal.getId() + " already exists. Skipping duplicate.");
        return;
      }
      animalRepository.save(animal);
      System.out.println("Central DB: Synced Animal " + animal.getId());
    } catch (Exception e) {
      System.err.println("Error saving Animal: " + e.getMessage());
      e.printStackTrace();
    }
  }

  @RabbitListener(queues = "register_part_type")
  @Transactional
  public void receivePartType(
      PartType partType) {
    System.out.println("CentralConsumer: Received PartType message: " + partType);
    try {
      if (partType.getId() != null && partTypeRepository.existsById(partType.getId())) {
        System.out.println("Central DB: PartType " + partType.getId() + " already exists. Skipping duplicate.");
        return;
      }
      partTypeRepository.save(partType);
      System.out.println("Central DB: Synced PartType " + partType.getId());
    } catch (Exception e) {
      System.err.println("Error saving PartType: " + e.getMessage());
      e.printStackTrace();
    }
  }

  @RabbitListener(queues = "register_tray")
  @Transactional
  public void receiveTray(Tray tray) {
    System.out.println("CentralConsumer: Received Tray message: " + tray);
    try {
      if (tray.getId() != null && trayRepository.existsById(tray.getId())) {
        System.out.println("Central DB: Tray " + tray.getId() + " already exists. Skipping duplicate.");
        return;
      }
      trayRepository.save(tray);
      System.out.println("Central DB: Synced Tray " + tray.getId());
    } catch (DataIntegrityViolationException e) {
      System.err.println(
          "Missing dependency for Tray " + tray.getId() + ". Requeueing...");
      throw new RuntimeException("Requeue Tray", e);
    } catch (Exception e) {
      System.err.println("Error saving Tray: " + e.getMessage());
      e.printStackTrace();
    }
  }

  @RabbitListener(queues = "register_part")
  @Transactional
  public void receivePart(
      Part part) {
    System.out.println("CentralConsumer: Received Part message: " + part);
    try {
      if (part.getId() != null && partRepository.existsById(part.getId())) {
        System.out.println("Central DB: Part " + part.getId() + " already exists. Skipping duplicate.");
        return;
      }
      partRepository.save(part);
      System.out.println("Central DB: Synced Part " + part.getId());
    } catch (DataIntegrityViolationException e) {
      System.err.println("Foreign Key missing for Part " + part.getId()
          + " (Tray/Animal not found). Requeueing...");
      throw new RuntimeException("Requeue Part", e);
    } catch (Exception e) {
      System.err.println("Fatal error saving Part: " + e.getMessage());
      e.printStackTrace();
    }
  }

  @RabbitListener(queues = "register_package")
  @Transactional
  public void receivePackage(Package pkg) {
    System.out.println("CentralConsumer: Received Package message: " + pkg);
    try {
      if (pkg.getId() != null && packageRepository.existsById(pkg.getId())) {
        System.out.println("Central DB: Package " + pkg.getId() + " already exists. Skipping duplicate.");
        return;
      }

      packageRepository.save(pkg);
      System.out.println("Central DB: Synced Package " + pkg.getId());

    } catch (Exception e) {
      System.err.println("Error saving Package: " + e.getMessage());
      e.printStackTrace();
    }
  }

  @RabbitListener(queues = "register_half_animal")
  @Transactional
  public void receiveHalfAnimal(HalfAnimal halfAnimal) {
    System.out.println("CentralConsumer: Received HalfAnimal message: " + halfAnimal);
    try {
      if (halfAnimal.getId() != null && halfAnimalRepository.existsById(halfAnimal.getId())) {
        System.out.println("Central DB: HalfAnimal " + halfAnimal.getId() + " already exists. Skipping duplicate.");
        return;
      }

      halfAnimalRepository.save(halfAnimal);
      System.out.println("Central DB: Synced HalfAnimal " + halfAnimal.getId());

    } catch (Exception e) {
      System.err.println("Error saving HalfAnimal: " + e.getMessage());
      e.printStackTrace();
    }
  }

  @RabbitListener(queues = "register_product")
  @Transactional
  public void receiveProduct(Product product) {
    System.out.println("CentralConsumer: Received Product message: " + product);
    try {
      if (product.getId() != null && productRepository.existsById(product.getId())) {
        System.out.println("Central DB: Product " + product.getId() + " already exists. Skipping duplicate.");
        return;
      }
      productRepository.save(product);
      System.out.println("Central DB: Synced Product " + product.getId());
    } catch (Exception e) {
      System.err.println("Error saving Product: " + e.getMessage());
      e.printStackTrace();
    }
  }
}