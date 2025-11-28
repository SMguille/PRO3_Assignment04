package via.pro3.central_registration_server.Model;

import java.math.BigDecimal;

public class TrayRegistrationDto {
  private BigDecimal maxWeightCapacityKg;
  private Integer partsTypeId;

  public BigDecimal getMaxWeightCapacityKg() {
    return maxWeightCapacityKg;
  }

  public void setMaxWeightCapacityKg(BigDecimal maxWeightCapacityKg) {
    this.maxWeightCapacityKg = maxWeightCapacityKg;
  }

  public Integer getPartsTypeId() {
    return partsTypeId;
  }

  public void setPartsTypeId(Integer partsTypeId) {
    this.partsTypeId = partsTypeId;
  }
}
