package API_yandex;

import java.util.List;

public class Data {
    private List<Vehicle> vehicles;
    private List<Object> seoVehicles;
    private RegionInfo regionInfo;

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public List<Object> getSeoVehicles() {
        return seoVehicles;
    }

    public void setSeoVehicles(List<Object> seoVehicles) {
        this.seoVehicles = seoVehicles;
    }

    public RegionInfo getRegionInfo() {
        return regionInfo;
    }

    public void setRegionInfo(RegionInfo regionInfo) {
        this.regionInfo = regionInfo;
    }
}