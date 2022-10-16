package parsing_task.model;

public enum ChildType {

    LINK("LinkChild"),
    PATH("PathChild"),
    ETHERNET("EthernetChild");

    private final String displayType;
    ChildType(String displayType) {
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