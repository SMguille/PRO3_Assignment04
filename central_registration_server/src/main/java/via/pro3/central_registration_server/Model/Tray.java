package via.pro3.central_registration_server.Model;

import java.math.BigDecimal;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity @Table(name = "tray") public class Tray
{

  @Id private Integer id;

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
