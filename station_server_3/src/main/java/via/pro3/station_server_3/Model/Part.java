package via.pro3.station_server_3.Model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity @Table(name = "part") public class Part
{

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Integer id;

  @Column(name = "weight_kg", nullable = false, precision = 10, scale = 3) private BigDecimal weightKg;

  @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "parts_type", referencedColumnName = "id") private PartType partType;

  @Column(name = "animal_id") private Integer animalId;

  @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "tray_id", nullable = false) private Tray tray;

  public Integer getId()
  {
    return id;
  }

  public void setId(Integer id)
  {
    this.id = id;
  }

  public BigDecimal getWeightKg()
  {
    return weightKg;
  }

  public void setWeightKg(BigDecimal weightKg)
  {
    this.weightKg = weightKg;
  }

  public PartType getPartType()
  {
    return partType;
  }

  public void setPartType(PartType partType)
  {
    this.partType = partType;
  }

  public Integer getAnimalId()
  {
    return animalId;
  }

  public void setAnimalId(Integer animalId)
  {
    this.animalId = animalId;
  }

  public Tray getTray()
  {
    return tray;
  }

  public void setTray(Tray tray)
  {
    this.tray = tray;
  }

  public Integer getTrayId()
  {
    return tray.getId();
  }

  public Integer getPartTypeId()
  {
    return partType.getId();
  }
}
