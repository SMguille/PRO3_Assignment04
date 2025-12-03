package via.pro3.station_server_3.Model;

import jakarta.persistence.Column;

import java.math.BigDecimal;

public class Package extends Product{

    @Column(name = "part_type", nullable = false, precision = 10, scale = 3) private PartType partType;

    @Column(name = "amount", nullable = false) private Integer amount;

}
