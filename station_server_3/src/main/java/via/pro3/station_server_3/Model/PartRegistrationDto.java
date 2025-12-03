package via.pro3.station_server_3.Model;

import java.math.BigDecimal;

public class PartRegistrationDto
{
  private BigDecimal weightKg;
  private Integer partTypeId; // ID for PartType lookup
  private Integer animalId;   // Decoupled animal ID (as discussed)
  private Integer trayId;     // ID for Tray lookup

  public BigDecimal getWeightKg()
  {
    return weightKg;
  }

  public void setWeightKg(BigDecimal weightKg)
  {
    this.weightKg = weightKg;
  }

  public Integer getPartTypeId()
  {
    return partTypeId;
  }

  public void setPartTypeId(Integer partTypeId)
  {
    this.partTypeId = partTypeId;
  }

  public Integer getAnimalId()
  {
    return animalId;
  }

  public void setAnimalId(Integer animalId)
  {
    this.animalId = animalId;
  }

  public Integer getTrayId()
  {
    return trayId;
  }

  public void setTrayId(Integer trayId)
  {
    this.trayId = trayId;
  }
}
