package parsing_task;

public class ChildParsed {
    private String attributeName;
    private ChildType type;
    private String parentName;
    private String name;

    public ChildParsed(ChildParsedBuilder childParsedBuilder) {
        this.attributeName = childParsedBuilder.attributeName;
        this.type = childParsedBuilder.type;
        this.parentName = childParsedBuilder.parentName;
        this.name = childParsedBuilder.name;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public ChildType getType() {
        return type;
    }

    public String getParentName() {
        return parentName;
    }

    public String getName() {
        return name;
    }

    public static ChildParsedBuilder builder(){
        return new ChildParsedBuilder();
    }

    public static class ChildParsedBuilder {
        private String attributeName;
        private ChildType type;
        private String parentName;
        private String name;

        public ChildParsedBuilder attributeName(String attributeName) {
            this.attributeName = attributeName;
            return this;
        }

        public ChildParsedBuilder type(ChildType type) {
            this.type = type;
            return this;
        }

        public ChildParsedBuilder parentName(String parentName) {
            this.parentName = parentName;
            return this;
        }

        public ChildParsedBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ChildParsed build() {
            return new ChildParsed(this);
        }
    }
}
