package via.pro3.central_registration_server.Model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Set;

@Entity @Table(name = "tray") public class Tray
{

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Integer id;

  @Column(name = "max_weight_capacity_kg", nullable = false, precision = 10, scale = 3) private BigDecimal maxWeightCapacityKg;

  @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "parts_type", referencedColumnName = "id") private PartType partType;

  @OneToMany(mappedBy = "tray", cascade = CascadeType.ALL, orphanRemoval = true) private transient Set<Part> parts;

  public Integer getId()
  {
    return id;
  }

  public void setId(Integer id)
  {
    this.id = id;
  }

  public BigDecimal getMaxWeightCapacityKg()
  {
    return maxWeightCapacityKg;
  }

  public void setMaxWeightCapacityKg(BigDecimal maxWeightCapacityKg)
  {
    this.maxWeightCapacityKg = maxWeightCapacityKg;
  }

  public PartType getPartType()
  {
    return partType;
  }

  public void setPartType(PartType partType)
  {
    this.partType = partType;
  }

  public Set<Part> getParts()
  {
    return parts;
  }

  public void setParts(Set<Part> parts)
  {
    this.parts = parts;
  }
}
