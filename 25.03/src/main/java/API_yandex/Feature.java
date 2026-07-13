package API_yandex;

public class Feature {
    private Geometry geometry;
    private FeaturesProperties properties;

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public FeaturesProperties getProperties() {
        return properties;
    }

    public void setProperties(FeaturesProperties properties) {
        this.properties = properties;
    }
}

