public class MapElement {
    private String name;
    private String mapDisplay;

    public MapElement (String name, String mapDisplay)
    {
        this.name = name;
        this.mapDisplay = mapDisplay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMapDisplay() {
        return mapDisplay;
    }
}
