package parsing_task.model;

public enum Type {

    LINK("Link"),
    PATH("Path"),
    ETHERNET("Ethernet");

    private final String displayType;
    Type(String displayType) {
        this.displayType = displayType;
    }

    public String getDisplayType() {
        return displayType;
    }

    @Override
    public String toString(){
        return displayType;
    }
}