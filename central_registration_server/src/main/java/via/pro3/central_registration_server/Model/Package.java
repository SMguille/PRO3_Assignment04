package via.pro3.central_registration_server.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "package_product")
public class Package extends Product{

    @Column(name = "part_type_id", nullable = false)
    private Integer partTypeId;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    // Getters and Setters
    public Integer getPartTypeId() { return partTypeId; }
    public void setPartTypeId(Integer partTypeId) { this.partTypeId = partTypeId; }

    public Integer getAmount() { return amount; }
    public void setAmount(Integer amount) { this.amount = amount; }
}