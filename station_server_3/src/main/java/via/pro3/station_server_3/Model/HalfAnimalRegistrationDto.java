package via.pro3.station_server_3.Model;

import java.util.Set;

public class HalfAnimalRegistrationDto {
    private String productName;
    private Set<Integer> trayIds; // Changed from List to Set to match Entity

    // Constructors
    public HalfAnimalRegistrationDto() {
    }

    public HalfAnimalRegistrationDto(String productName, Set<Integer> trayIds) {
        this.productName = productName;
        this.trayIds = trayIds;
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
}