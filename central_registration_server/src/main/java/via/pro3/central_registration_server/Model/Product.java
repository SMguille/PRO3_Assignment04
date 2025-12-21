package via.pro3.central_registration_server.Model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "product")
@Inheritance(strategy = InheritanceType.JOINED)
public class Product {

    @Id
    private Integer id;

    @Column(name = "product_name")
    private String productName;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "product_tray_ids", 
            joinColumns = @JoinColumn(name = "product_id")
    )
    @Column(name = "tray_id")
    private Set<Integer> trayIds = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Set<Integer> getTrayIds() {
        return trayIds;
    }

    public void setTrayIds(Set<Integer> trayIds) {
        this.trayIds = trayIds;
    }
}