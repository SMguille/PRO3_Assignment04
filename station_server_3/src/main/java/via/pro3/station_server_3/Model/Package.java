package via.pro3.station_server_3.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "package_product") // "package" is a reserved keyword in SQL, so we use a different name
public class Package extends Product{

    @Column(name = "part_type_id", nullable = false)
    private Integer partTypeId; // Renamed to ID to avoid confusion with Object mapping, or map as @ManyToOne if it refers to PartType entity

    @Column(name = "amount", nullable = false)
    private Integer amount;

    // Getters and Setters
    public Integer getPartTypeId() { return partTypeId; }
    public void setPartTypeId(Integer partTypeId) { this.partTypeId = partTypeId; }

    public Integer getAmount() { return amount; }
    public void setAmount(Integer amount) { this.amount = amount; }
}