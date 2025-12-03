package via.pro3.station_server_3.Model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Set;

@Entity @Table(name = "product") public class Product
{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Integer id;

    @Column(name = "name") String productName;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "product_type", referencedColumnName = "id") private ProductType productType;

    @OneToMany(mappedBy = "part", cascade = CascadeType.ALL, orphanRemoval = true) private transient Set<Part> parts;

    @ManyToMany(mappedBy = "tray", cascade = CascadeType.ALL) private transient Set<Tray> trays;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getProductName()
    {
        return productName;
    }

    public void setProductName(String productName)
    {
        this.productName = productName;
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
