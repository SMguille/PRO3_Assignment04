package via.pro3.station_server_3.Model;

import java.util.List;

public class PackageRegistrationDto {
    private String productName;
    private Integer partTypeId;
    private Integer amount;
    private List<Integer> trayIds; // The trays these parts are taken from

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public List<Integer> getTrayIds() {
        return trayIds;
    }

    public void setTrayIds(List<Integer> trayIds) {
        this.trayIds = trayIds;
    }
}