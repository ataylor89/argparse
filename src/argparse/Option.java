package argparse;

public class Option {

    public static class Builder {

        private String shortName;
        private String longName;
        private boolean hasArg;
        private String delimiter;
        private Class type;
        private Class subType;
        private String description;

        private Builder() {
            this.type = String.class;
            this.subType = String.class;
        }

        public Builder shortName(String shortName) {
            this.shortName = shortName;
            return this;
        }

        public Builder longName(String longName) {
            this.longName = longName;
            return this;
        }

        public Builder hasArg(boolean hasArg) {
            this.hasArg = hasArg;
            return this;
        }

        public Builder delimiter(String delimiter) {
            this.delimiter = delimiter;
            return this;
        }

        public Builder type(Class type) {
            this.type = type;
            return this;
        }

        public Builder subType(Class subType) {
            this.subType = subType;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Option build() {
            return new Option(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private String shortName;
    private String longName;
    private boolean hasArg;
    private String delimiter;
    private Class type;
    private Class subType;
    private String description;
    private String value;

    private Option(Builder builder) {
        this.shortName = builder.shortName;
        this.longName = builder.longName;
        this.hasArg = builder.hasArg;
        this.delimiter = builder.delimiter;
        this.type = builder.type;
        this.subType = builder.subType;
        this.description = builder.description;
    }

    public Option(String shortName, String longName, boolean hasArg, String delimiter, Class type, Class subType, String description) {
        this.shortName = shortName;
        this.longName = longName;
        this.hasArg = hasArg;
        this.delimiter = delimiter;
        this.type = type;
        this.subType = subType;
        this.description = description;
    }

    public Option(String shortName, String longName, boolean hasArg, String delimiter, Class type, String description) {
        this(shortName, longName, hasArg, delimiter, type, String.class, description);
    }

    public Option(String shortName, String longName, boolean hasArg, Class type, String description) {
        this(shortName, longName, hasArg, null, type, null, description);
    }

    public Option(String shortName, String longName, boolean hasArg, String description) {
        this(shortName, longName, hasArg, null, String.class, null, description);
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public String getLongName() {
        return longName;
    }

    public void hasArg(boolean hasArg) {
        this.hasArg = hasArg;
    }

    public boolean hasArg() {
        return hasArg;
    }

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    public String getDelimiter() {
        return delimiter;
    }

    public void setType(Class type) {
        this.type = type;
    }

    public Class getType() {
        return type;
    }

    public void setSubType(Class subType) {
        this.subType = subType;
    }

    public Class getSubType() {
        return subType;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
