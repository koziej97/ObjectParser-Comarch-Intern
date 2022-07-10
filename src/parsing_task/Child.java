package parsing_task;

public class Child {
    private String attributeName;
    private ChildType type;
    private String name;

    public Child(ChildBuilder childBuilder) {
        this.attributeName = childBuilder.attributeName;
        this.type = childBuilder.type;
        this.name = childBuilder.name;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public ChildType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public static ChildBuilder builder(String name){
        return new ChildBuilder(name);
    }

    public static class ChildBuilder{
        private String attributeName;
        private ChildType type;
        private String name;

        public ChildBuilder(String name) {
            this.name = name;
        }

        public ChildBuilder attributeName(String attributeName) {
            this.attributeName = attributeName;
            return this;
        }

        public ChildBuilder type(ChildType type) {
            this.type = type;
            return this;
        }

        public Child build() {
            return new Child(this);
        }
    }
}
