package via.pro3.station_server_3.Model;

import java.util.Set;

public class PackageRegistrationDto {

    private String productName;
    private Set<Integer> trayIds;
    private Integer partTypeId;
    private Integer amount;

    // Constructors
    public PackageRegistrationDto() {
    }

    public PackageRegistrationDto(String productName, Set<Integer> trayIds, Integer partTypeId, Integer amount) {
        this.productName = productName;
        this.trayIds = trayIds;
        this.partTypeId = partTypeId;
        this.amount = amount;
    }

    // Getters and Setters

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

    public Integer getPartTypeId() {
        return partTypeId;
    }

    public void setPartTypeId(Integer partTypeId) {
        this.partTypeId = partTypeId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}