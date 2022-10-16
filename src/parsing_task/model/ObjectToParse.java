package parsing_task.model;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.UUID;

public class ObjectToParse {
    private String attributeName;
    private Optional<String> id;
    private Optional<String> uuid;
    private Type type;
    private Optional<String> ipAddressV4;
    private OptionalInt ipAddressV4Mask;
    private Optional<String> ipAddressV6;
    private OptionalInt ipAddressV6Mask;
    private List<Child> childTypes;
    private String name;    // CANNOT BE EMPTY

    public ObjectToParse(ObjectToParseBuilder builder) {
        this.attributeName = builder.attributeName;
        this.id = builder.id;
        this.uuid = builder.uuid;
        this.type = builder.type;
        this.ipAddressV4 = builder.ipAddressV4;
        this.ipAddressV4Mask = builder.ipAddressV4Mask;
        this.ipAddressV6 = builder.ipAddressV6;
        this.ipAddressV6Mask = builder.ipAddressV6Mask;
        this.childTypes = builder.childTypes;
        this.name = builder.name;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public Optional<String> getId() {
        return id;
    }

    public Optional<String> getUuid() {
        return uuid;
    }

    public Type getType() {
        return type;
    }

    public Optional<String> getIpAddressV4() {
        return ipAddressV4;
    }

    public OptionalInt getIpAddressV4Mask() {
        return ipAddressV4Mask;
    }

    public Optional<String> getIpAddressV6() {
        return ipAddressV6;
    }

    public OptionalInt getIpAddressV6Mask() {
        return ipAddressV6Mask;
    }

    public List<Child> getChildTypes() {
        return childTypes;
    }

    public String getName() {
        return name;
    }

    public static ObjectToParseBuilder builder(String name){
        return new ObjectToParseBuilder(name);
    }

    public static class ObjectToParseBuilder {
        private String attributeName;
        private Optional<String> id;
        private Optional<String> uuid;
        private Type type;
        private Optional<String> ipAddressV4;
        private OptionalInt ipAddressV4Mask;
        private Optional<String> ipAddressV6;
        private OptionalInt ipAddressV6Mask;
        private List<Child> childTypes;
        private String name;

        public ObjectToParseBuilder(String name) {
            this.name = name;
            setUp();
        }

        public void setUp() {
            this.id = Optional.empty();
            this.uuid = Optional.empty();
            this.ipAddressV4 = Optional.empty();
            this.ipAddressV4Mask = OptionalInt.empty();
            this.ipAddressV6 = Optional.empty();
            this.ipAddressV6Mask = OptionalInt.empty();
        }

        public ObjectToParse build() {
            if (id.isEmpty()){
                uuid = Optional.of(UUID.randomUUID().toString());
            }
            ObjectToParse objectToParse = new ObjectToParse(this);
            checkIfValid(objectToParse);
            return objectToParse;
        }

        public ObjectToParseBuilder attributeName(String attributeName) {
            this.attributeName = attributeName;
            return this;
        }

        public ObjectToParseBuilder id(Optional<String> id) {
            this.id = id;
            return this;
        }

        public ObjectToParseBuilder uuid(Optional<String> uuid) {
            this.uuid = uuid;
            return this;
        }

        public ObjectToParseBuilder type(Type type) {
            this.type = type;
            return this;
        }

        public ObjectToParseBuilder ipAddressV4(Optional<String> ipAddressV4) {
            this.ipAddressV4 = ipAddressV4;
            return this;
        }

        public ObjectToParseBuilder ipAddressV4Mask(OptionalInt ipAddressV4Mask) {
            this.ipAddressV4Mask = ipAddressV4Mask;
            return this;
        }

        public ObjectToParseBuilder ipAddressV6(Optional<String> ipAddressV6) {
            this.ipAddressV6 = ipAddressV6;
            return this;
        }

        public ObjectToParseBuilder ipAddressV6Mask(OptionalInt ipAddressV6Mask) {
            this.ipAddressV6Mask = ipAddressV6Mask;
            return this;
        }

        public ObjectToParseBuilder childTypes(List<Child> childTypes) {
            this.childTypes = childTypes;
            return this;
        }

        public ObjectToParseBuilder name(String name) {
            this.name = name;
            return this;
        }

        private void checkIfValid(ObjectToParse objectToParse){
            if (doesNotHaveIpAddress(objectToParse)){
                throw new IllegalArgumentException("parsing_task.model.ObjectToParse must have at least one ipAddress: V4 or V6.");
            }
            if (notCorrectIpAddressMask(objectToParse)){
                throw new IllegalArgumentException("parsing_task.model.ObjectToParse must have at least one ipAddressMask: " +
                        "for V4 it must be integer between 1-32, for V6 it must be integer between 33-64.");
            }
        }

        private boolean doesNotHaveIpAddress(ObjectToParse objectToParse){
            return objectToParse.getIpAddressV4().isEmpty() && objectToParse.getIpAddressV6().isEmpty();
        }
        private boolean notCorrectIpAddressMask(ObjectToParse objectToParse) {
            if (objectToParse.getIpAddressV4Mask().isPresent()){
                return objectToParse.getIpAddressV4Mask().getAsInt() < 1
                        || objectToParse.getIpAddressV4Mask().getAsInt() > 32;
            }
            if (objectToParse.getIpAddressV6Mask().isPresent()) {
                return objectToParse.getIpAddressV6Mask().getAsInt() < 33
                        || objectToParse.getIpAddressV6Mask().getAsInt() > 64;
            }
            return true;
        }
    }
}
