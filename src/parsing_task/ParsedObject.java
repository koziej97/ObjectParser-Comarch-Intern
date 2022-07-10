package parsing_task;

import java.util.List;

public class ParsedObject {
    private String attributeName;
    private String id;
    private Type type;
    private String ipAddress;
    private int ipAddressMask;
    private List<ChildParsed> childParsedList;

    private ParsedObject(ParsedObjectBuilder builder) {
        this.attributeName = builder.attributeName;
        this.id = builder.id;
        this.type = builder.type;
        this.ipAddress = builder.ipAddress;
        this.ipAddressMask = builder.ipAddressMask;
        this.childParsedList = builder.childParsedList;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public String getId() { return id; }

    public Type getType() {
        return type;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public int getIpAddressMask() {
        return ipAddressMask;
    }

    public List<ChildParsed> getParsedChildList() {
        return childParsedList;
    }

    public String displayInfo() {
        return "ParsedObject:\n" + this.attributeName + "\n" + this.id + "\n" + this.type + "\n" +
                this.ipAddress + "\n" + this.ipAddressMask + "\n" + this.childParsedList + "\n";
    }

    public static ParsedObjectBuilder builder(){
        return new ParsedObjectBuilder();
    }

    public static class ParsedObjectBuilder {
        private String attributeName;
        private String id;
        private Type type;
        private String ipAddress;
        private int ipAddressMask;
        private List<ChildParsed> childParsedList;

        public ParsedObjectBuilder() {
        }

        public ParsedObjectBuilder attributeName(String attributeName) {
            this.attributeName = attributeName;
            return this;
        }

        public ParsedObjectBuilder id(String id) {
            this.id = id;
            return this;
        }

        public ParsedObjectBuilder type(Type type) {
            this.type = type;
            return this;
        }

        public ParsedObjectBuilder ipAddress(String ipAddress) {
            this.ipAddress = ipAddress;
            return this;
        }

        public ParsedObjectBuilder ipAddressMask(int ipAddressMask) {
            this.ipAddressMask = ipAddressMask;
            return this;
        }

        public ParsedObjectBuilder parsedChildList(List<ChildParsed> childParsedList) {
            this.childParsedList = childParsedList;
            return this;
        }

        public ParsedObject build() {
            return new ParsedObject(this);
        }
    }
}
