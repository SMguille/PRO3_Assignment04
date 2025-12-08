package via.pro3.station_server_3.Model;

import java.util.List;

public class HalfAnimalRegistrationDto {
    private String productName;
    private List<Integer> trayIds; // The trays the parts for this half-animal come from

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public List<Integer> getTrayIds() {
        return trayIds;
    }

    public void setTrayIds(List<Integer> trayIds) {
        this.trayIds = trayIds;
    }
}