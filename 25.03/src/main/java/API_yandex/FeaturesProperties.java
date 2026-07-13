package API_yandex;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class FeaturesProperties {
    private String type;
    @JsonProperty("TrajectorySegmentMetaData")
    private Map<String, Long> trajectorySegmentMetaData;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, Long> getTrajectorySegmentMetaData() {
        return trajectorySegmentMetaData;
    }

    public void setTrajectorySegmentMetaData(Map<String, Long> trajectorySegmentMetaData) {
        this.trajectorySegmentMetaData = trajectorySegmentMetaData;
    }
}
